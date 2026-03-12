package com.multishop.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.multishop.entity.Category;
import com.multishop.model.request.CategoryRequest;
import com.multishop.model.response.CategoryResponse;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

  // Ánh xạ từ Request sang Entity (Bỏ qua field image vì xử lý riêng bằng upload)
  @Mapping(target = "image", ignore = true)
  @Mapping(target = "parent", ignore = true)
  Category toEntity(CategoryRequest categoryRequest);

  // Xử lý PATCH: Cập nhật một phần
  @Mapping(target = "image", ignore = true)
  @Mapping(target = "parent", ignore = true)
  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  void updateEntityFromRequest(CategoryRequest request, @MappingTarget Category category);

  // Ánh xạ sang Response
  @Mapping(target = "categoryParentId", source = "parent.id")
  @Mapping(target = "categoryParentName", source = "parent.name")
  CategoryResponse toResponse(Category category);

}
