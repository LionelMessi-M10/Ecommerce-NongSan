package com.multishop.repository.custom;

import org.springframework.data.domain.Page;

import com.multishop.entity.Category;
import com.multishop.model.dto.CategorySearchCriteria;

public interface CategoryRepositoryCustom {
    Page<Category> searchCategories(CategorySearchCriteria criteria);
}
