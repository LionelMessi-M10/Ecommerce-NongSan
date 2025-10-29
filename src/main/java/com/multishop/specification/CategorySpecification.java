package com.multishop.specification;

import org.springframework.data.jpa.domain.Specification;
import com.multishop.entity.Category;
import com.multishop.model.dto.CategorySearchCriteria;

public class CategorySpecification {

	/**
	 * 
	 * root: Đại diện cho entity gốc mà bạn đang query (ở đây là Category). Ví dụ:
	 * root.get("name") tương ứng với cột name trong bảng categories. Nếu quan hệ
	 * ManyToOne/OneToMany thì có thể truy cập như root.get("shop").get("id").
	 * 
	 * query Đại diện cho câu truy vấn JPA đang được build. Thường ít dùng trực
	 * tiếp, trừ khi bạn muốn chỉnh SELECT DISTINCT, JOIN fetch, hoặc ORDER BY.
	 * 
	 * cb (CriteriaBuilder) Là công cụ để tạo ra các biểu thức điều kiện
	 * (Predicate). Ví dụ: cb.equal(root.get("status"), true) → WHERE status = true
	 * cb.like(cb.lower(root.get("name")), "%phone%") → WHERE LOWER(name) LIKE
	 * '%phone%' cb.conjunction() → điều kiện “luôn đúng” (thường dùng khi không có
	 * filter).
	 * 
	 * Tóm lại: root → ánh xạ entity → cột trong bảng. cb → tạo điều kiện filter.
	 * predicates → tập hợp điều kiện WHERE. Repository (nhờ
	 * JpaSpecificationExecutor) → nhận Spec, dịch sang SQL, query DB
	 * 
	 */
	public static Specification<Category> filter(CategorySearchCriteria criteria) {
		Specification<Category> spec = Specification
				.where(hasKeySearch(criteria.getKeySearch()))
				.and(hasStatus(criteria.getStatus()))
				.and(hasParentId(criteria.getParentId()))
				.and(hasLevel(criteria.getLevel()))
				.and(hasPath(criteria.getPath()));

		if (criteria.getParentId() == null) {
			spec = spec.and(parentOrChildren(criteria.getParentOnly(), criteria.getChildrenOnly()));
		}

		return spec;
	}

	public static Specification<Category> hasKeySearch(String keySearch) {
		return (root, query, cb) -> {
			if (keySearch == null || keySearch.isBlank())
				return null;
			return cb.like(cb.lower(root.get("name")), "%" + keySearch.toLowerCase() + "%");
		};
	}

	public static Specification<Category> hasStatus(Byte status) {
		return (root, query, cb) -> status == null ? null : cb.equal(root.get("status"), status);
	}

	public static Specification<Category> hasParentId(Long parentId) {
		return (root, query, cb) -> parentId == null ? null : cb.equal(root.get("parent").get("id"), parentId);
	}

	public static Specification<Category> hasLevel(Integer level) {
		return (root, query, cb) -> level == null ? null : cb.equal(root.get("level"), level);
	}

	public static Specification<Category> hasPath(String path) {
		return (root, query, cb) -> {
			if (path == null || path.isBlank())
				return null;
			return cb.like(root.get("path"), path + "%");
		};
	}

	public static Specification<Category> parentOrChildren(Boolean parentOnly, Boolean childrenOnly) {
		return (root, query, cb) -> {
			if (Boolean.TRUE.equals(parentOnly))
				return cb.isNull(root.get("parent"));
			if (Boolean.TRUE.equals(childrenOnly))
				return cb.isNotNull(root.get("parent"));
			return null; // không filter
		};
	}

}
