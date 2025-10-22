package com.multishop.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.multishop.entity.Category;
import com.multishop.repository.custom.CategoryRepositoryCustom;

@Repository
public interface CategoryRepository
		extends JpaRepository<Category, Long>, CategoryRepositoryCustom, JpaSpecificationExecutor<Category> {

	// đã có sẵn
	boolean existsByName(String name);

	// tìm category theo tên
	Optional<Category> findByName(String name);

	// tìm các category có parent_id null (root category)
	List<Category> findAllByParentIsNull();

	// tìm các category con của một parent
	List<Category> findAllByParent_Id(Long parentId);

}
