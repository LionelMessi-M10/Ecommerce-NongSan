package com.multishop.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class UploadFile {

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Value("${file.base-url}")
    private String baseUrl;

    public String uploadFile(MultipartFile file, String targetFolder) {

        try {
            Path projectPath = Paths.get("").toAbsolutePath(); // thư mục gốc project
            Path rootUploadPath = projectPath.resolve(uploadDir);

            // Nếu có targetFolder → thêm vào path
            Path finalFolderPath = (targetFolder != null && !targetFolder.isBlank())
                    ? rootUploadPath.resolve(targetFolder)
                    : rootUploadPath;

            // tạo thư mục nếu chưa có
            if (!Files.exists(finalFolderPath)) {
                Files.createDirectories(finalFolderPath);
            }

            // tạo tên file duy nhất
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            Path filePath = finalFolderPath.resolve(fileName);

            // lưu file
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            // build URL trả về cho FE
            String fileUrl = baseUrl 
                    + (targetFolder != null && !targetFolder.isBlank() ? targetFolder + "/" : "")
                    + fileName;

            return fileUrl;

        } catch (IOException e) {
            throw new RuntimeException("Lỗi khi upload file: " + e.getMessage());
        }
    }
}
