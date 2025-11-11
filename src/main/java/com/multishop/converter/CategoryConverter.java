package com.multishop.converter;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.multishop.model.request.CategoryRequest;
import com.multishop.model.response.CategoryResponse;
import com.multishop.utils.UploadFile;

import lombok.RequiredArgsConstructor;

import com.multishop.entity.Category;

@RequiredArgsConstructor
@Component
public class CategoryConverter {

    private final ModelMapper modelMapper;
    private final UploadFile uploadFile;

    public Category toEntity(CategoryRequest request, Category parent, MultipartFile imageCategory) {

        if (imageCategory != null) {
            request.setImage(uploadFile.uploadFile(imageCategory, "categories"));
        }

        Category category = new Category();
        
        category.setName(request.getName());
        category.setImage(request.getImage());
        category.setStatus(request.getStatus());

        if (parent != null) {
            category.setParent(parent);
        }
        return category;
    }

    public void updateEntity(Category category, CategoryRequest request, Category parent, MultipartFile imageCategory) {
        if (imageCategory != null) {
            category.setImage(uploadFile.uploadFile(imageCategory, "categories"));
        }
        category.setName(request.getName());
        category.setImage(request.getImage());
        category.setStatus(request.getStatus());

        if (parent != null) {
            category.setParent(parent);
        }
    }

    public CategoryResponse toResponse(Category category) {
        CategoryResponse response = modelMapper.map(category, CategoryResponse.class);

        if (category.getParent() != null) {
            response.setCategoryParentId(category.getParent().getId());
            response.setCategoryParentName(category.getParent().getName());
        }

        if (category.getChildren() != null && !category.getChildren().isEmpty()) {
            response.setChildren(
                category.getChildren().stream()
                        .map(this::toResponse) // đệ quy
                        .collect(Collectors.toList())
            );
        }

        return response;
    }
}
