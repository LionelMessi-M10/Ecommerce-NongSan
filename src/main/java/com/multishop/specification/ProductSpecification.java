package com.multishop.specification;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.multishop.entity.Brand;
import com.multishop.entity.Category;
import com.multishop.entity.Product;
import com.multishop.model.dto.ProductSearchCriteria;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;

public class ProductSpecification {

  public static Specification<Product> filter(ProductSearchCriteria criteria) {
    Specification<Product> spec = Specification.where(null);

    if (criteria == null)
      return spec;

    spec = spec.and(hasKeySearch(criteria.getKeySearch()))
        .and(hasStatus(criteria.getStatus()))
        .and(hasSalePrice(criteria.getSalePriceFrom(), criteria.getSalePriceTo()))
        .and(hasRating(criteria.getRating()))
        .and(inCategories(criteria.getCategoryIds()))
        .and(inBrands(criteria.getBrandIds()));

    return spec;
  }

  // Tìm theo tên hoặc mô tả
  public static Specification<Product> hasKeySearch(String keySearch) {
    return (root, query, cb) -> {
      if (keySearch == null || keySearch.isBlank())
        return null;
      String pattern = "%" + keySearch.toLowerCase().trim() + "%";
      return cb.or(
          cb.like(cb.lower(root.get("name")), pattern),
          cb.like(cb.lower(root.get("description")), pattern));
    };
  }

  // Lọc theo status
  public static Specification<Product> hasStatus(Byte status) {
    return (root, query, cb) -> status == null ? null : cb.equal(root.get("status"), status);
  }

  // Lọc theo giá bán
  public static Specification<Product> hasSalePrice(BigDecimal salePriceFrom, BigDecimal salePriceTo) {
    return (root, query, cb) -> {
      if (salePriceFrom == null && salePriceTo == null)
        return null;
      if (salePriceFrom != null && salePriceTo != null) {
        return cb.between(root.get("salePrice"), salePriceFrom, salePriceTo);
      } else if (salePriceFrom != null) {
        return cb.greaterThanOrEqualTo(root.get("salePrice"), salePriceFrom);
      } else {
        return cb.lessThanOrEqualTo(root.get("salePrice"), salePriceTo);
      }
    };
  }

  // Đánh giá
  public static Specification<Product> hasRating(BigDecimal rating) {
    return (root, query, cb) -> {
      if (rating == null)
        return null;
      return cb.greaterThanOrEqualTo(root.get("averageRating"), rating);
    };
  }

  // Category hoặc SubCategory
  public static Specification<Product> inCategories(List<Long> categoryIds) {
    return (root, query, cb) -> {
      if (categoryIds == null || categoryIds.isEmpty())
        return null;

      Join<Product, Category> categoryJoin = root.join("category", JoinType.LEFT);
      return categoryJoin.get("id").in(categoryIds);
    };
  }

  // Brand
  public static Specification<Product> inBrands(List<Long> brandIds) {
    return (root, query, cb) -> {
      if (brandIds == null || brandIds.isEmpty())
        return null;

      Join<Product, Brand> brandJoin = root.join("brand", JoinType.LEFT);
      return brandJoin.get("id").in(brandIds);
    };
  }

}
