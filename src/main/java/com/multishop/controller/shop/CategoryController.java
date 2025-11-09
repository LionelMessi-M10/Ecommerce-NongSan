package com.multishop.controller.shop;

import com.multishop.model.dto.CategorySearchCriteria;
import com.multishop.model.request.CategoryRequest;
import com.multishop.model.response.CategoryResponse;
import com.multishop.payload.ApiResponse;
import com.multishop.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/shops")
public class CategoryController {

	private final CategoryService categoryService;

	@GetMapping("/categories")
	public ResponseEntity<?> getAllCategories(@RequestBody(required = false) CategorySearchCriteria categorySearchCriteria) {

		Page<CategoryResponse> result = categoryService.getAll(categorySearchCriteria);

		return ResponseEntity.ok(
				ApiResponse.success(
						HttpStatus.OK,
						"Get all categories by custom successfully",
						result));
	}

	@GetMapping("/categories/searchBySpecification")
	public ResponseEntity<?> searchBySpecification(@RequestBody(required = false) CategorySearchCriteria categorySearchCriteria) {

		Page<CategoryResponse> result = categoryService.searchBySpecification(categorySearchCriteria);

		return ResponseEntity.ok(
				ApiResponse.success(
						HttpStatus.OK,
						"Get all categories by specification successfully",
						result
						));
	}

	@GetMapping("/categories/{id}")
	public ResponseEntity<?> getCategoryById(@PathVariable Long id) {
		return ResponseEntity.ok(
				ApiResponse.success(
						HttpStatus.OK, "Found category by id: " + id, categoryService.getById(id)));
	}

	@PostMapping("/categories")
	public ResponseEntity<?> createCategory(@Valid @RequestBody(required = false) CategoryRequest categoryRequest) {
		CategoryResponse newCategory = categoryService.create(categoryRequest);
		return ResponseEntity.ok(ApiResponse.success(HttpStatus.CREATED, "Create category successfully", newCategory));
	}

	@PutMapping("/categories/{id}")
	public ResponseEntity<?> updateCategory(@PathVariable Long id, @Valid @RequestBody(required = false) CategoryRequest categoryRequest) {
		CategoryResponse updateCategory = categoryService.update(id, categoryRequest);
		return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK, "Update category successfully", updateCategory));
	}

	@DeleteMapping("/categories/{id}")
	public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
		CategoryResponse deleteCategory = categoryService.delete(id);
		return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK, "Delete category successfully", deleteCategory));
	}
}
