package com.multishop.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class UploadFile {

    // Đường dẫn thư mục gốc lưu ảnh tĩnh
    private final String uploadDir = "src/main/resources/static/images";

    // Hàm upload trả về đường dẫn tương đối để dùng trong HTML
    public String uploadFile(MultipartFile file, String targetFolder) throws IOException {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("File is empty");
        }

        // Tạo thư mục đích nếu chưa tồn tại
        Path folderPath = Path.of(uploadDir, targetFolder);
        if (!Files.exists(folderPath)) {
            Files.createDirectories(folderPath);
        }

        // Tạo tên file ngẫu nhiên để tránh trùng
        String originalFileName = file.getOriginalFilename();
        String fileExtension = getFileExtension(originalFileName);
        String randomFileName = UUID.randomUUID().toString() + "." + fileExtension;

        // Ghi file
        Path filePath = folderPath.resolve(randomFileName);
        file.transferTo(filePath.toFile());

        // Trả về đường dẫn tương đối dùng cho HTML (bắt đầu từ /images)
        return "/images/" + targetFolder + "/" + randomFileName;
    }

    private String getFileExtension(String filename) {
        int dotIndex = filename.lastIndexOf(".");
        if (dotIndex > 0 && dotIndex < filename.length() - 1) {
            return filename.substring(dotIndex + 1);
        } else {
            return ""; // Không có đuôi file
        }
    }
}
