package com.multishop.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.multishop.converter.CategoryConverter;
import com.multishop.entity.Category;
import com.multishop.model.dto.CategorySearchCriteria;
import com.multishop.model.request.CategoryRequest;
import com.multishop.model.response.CategoryResponse;
import com.multishop.repository.CategoryRepository;
import com.multishop.service.CategoryService;
import com.multishop.specification.CategorySpecification;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryConverter categoryConverter;

    /**
     * Tạo mới category
     * 
     * @param request CategoryRequest từ frontend gửi
     * @return Category mới được tạo
     */
    @Transactional
    @Override
    public CategoryResponse create(CategoryRequest request) {
        Category parent = null;
        if (request.getParentId() != null) {
            parent = categoryRepository.findById(request.getParentId())
                    .orElseThrow(() -> new RuntimeException("Parent category not found"));
        }

        // Tạo entity từ request
        Category category = categoryConverter.toEntity(request, parent);

        // set level
        category.setLevel(parent == null ? 0 : parent.getLevel() + 1);

        // set path tạm thời để save lần 1
        category.setPath(parent == null ? "" : parent.getPath() + "/");
        category = categoryRepository.save(category);

        // update path với ID thực
        category.setPath((parent == null ? "" : parent.getPath() + "/") + category.getId());
        category.setStatus(1);
        
        category = categoryRepository.save(category);

        return categoryConverter.toResponse(category);
    }

    /**
     * Cập nhật category đã có trong DB
     * 
     * @param id Mã id của category trong DB
     * @param request CategoryRequest từ frontend gửi để thay đổi category có sẵn
     * @return Category được cập nhật
     */
    @Transactional
    @Override
    public CategoryResponse update(Long categoryId, CategoryRequest request) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Category parent = null;
        if (request.getParentId() != null) {
            parent = categoryRepository.findById(request.getParentId())
                    .orElseThrow(() -> new RuntimeException("Parent category not found"));
        }

        // Cập nhật entity
        categoryConverter.updateEntity(category, request, parent);

        // update level & path
        category.setLevel(parent == null ? 0 : parent.getLevel() + 1);
        category.setPath((parent == null ? "" : parent.getPath() + "/") + category.getId());

        category = categoryRepository.save(category);

        return categoryConverter.toResponse(category);
    }

    /**
     * Cập nhật trạng thái category đã có trong DB, không xoá trực tiếp
     * 
     * @param id Mã id của category trong DB
     * @return Category được cập nhật
     */
    @Transactional
    @Override
    public CategoryResponse delete(Long id) {
    	Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found: " + id));
    	
    	category.setStatus(0);
        categoryRepository.save(category);
        
        return categoryConverter.toResponse(category);
    }

    @Override
    public CategoryResponse getById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found: " + id));
        return categoryConverter.toResponse(category);
    }

    @Override
    public Page<CategoryResponse> getAll(CategorySearchCriteria categorySearchCriteria) {
        Page<Category> categories = categoryRepository.searchCategories(categorySearchCriteria);
        return categories.map(categoryConverter::toResponse);
    }

    @Override
    public Page<CategoryResponse> searchBySpecification(CategorySearchCriteria criteria) {
        // Nếu pageNo < 0 thì reset về 0
        int pageNo = (criteria.getPageNo() != null && criteria.getPageNo() >= 0) ? criteria.getPageNo() : 0;
        int pageSize = (criteria.getPageSize() != null && criteria.getPageSize() > 0) ? criteria.getPageSize() : 10;

        // Nếu criteria có sortBy + direction thì áp dụng, còn không thì default
        Sort sort = Sort.by("id").descending();
        if (criteria.getSortBy() != null && !criteria.getSortBy().isBlank()) {
            Sort.Direction direction = (criteria.getSortDir() != null && criteria.getSortDir().equalsIgnoreCase("asc"))
                    ? Sort.Direction.ASC
                    : Sort.Direction.DESC;
            sort = Sort.by(direction, criteria.getSortBy());
        }

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Specification<Category> spec = CategorySpecification.filter(criteria);

        return categoryRepository.findAll(spec, pageable)
                .map(categoryConverter::toResponse);
    }

}
