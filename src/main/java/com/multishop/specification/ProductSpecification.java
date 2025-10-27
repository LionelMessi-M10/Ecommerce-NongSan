package com.multishop.specification;

import org.springframework.data.jpa.domain.Specification;

import com.multishop.entity.Category;
import com.multishop.entity.Product;
import com.multishop.model.dto.ProductSearchCriteria;

public class ProductSpecification {

  public static Specification<Product> filter(ProductSearchCriteria criteria) {
    Specification<Product> spec = Specification
        .where(null);

    return spec;
  }

  public static Specification<Product> hasKeySearch(String keySearch) {
    return (root, query, cb) -> {
      if (keySearch == null || keySearch.isBlank())
        return null;
      return cb.like(cb.lower(root.get("name")), "%" + keySearch.toLowerCase() + "%");
    };
  }

  public static Specification<Category> hasStatus(Byte status) {
    return (root, query, cb) -> status == null ? null : cb.equal(root.get("status"), status);
  }

}
