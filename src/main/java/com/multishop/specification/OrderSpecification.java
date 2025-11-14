package com.multishop.specification;

import java.time.LocalDateTime;

import org.springframework.data.jpa.domain.Specification;

import com.multishop.entity.Order;
import com.multishop.enums.OrderStatus;
import com.multishop.model.dto.OrderSearchCriteria;

public class OrderSpecification {

  public static Specification<Order> filter(OrderSearchCriteria orderSearchCriteria) {
    Specification<Order> spec = Specification.where(null);

    if (orderSearchCriteria == null) {
      return spec;
    }

    if (orderSearchCriteria.getKeySearch() != null && !orderSearchCriteria.getKeySearch().isBlank()) {
      spec = spec.and(hasKeySearch(orderSearchCriteria.getKeySearch()));
    }

    if (orderSearchCriteria.getOrderStatus() != null) {
      spec = spec.and(hasOrderStatus(orderSearchCriteria.getOrderStatus()));
    }

    if (orderSearchCriteria.getOrderDateFrom() != null || orderSearchCriteria.getOrderDateTo() != null) {
      spec = spec.and(hasOrderDate(orderSearchCriteria.getOrderDateFrom(), orderSearchCriteria.getOrderDateTo()));
    }

    return spec;
  }

  public static Specification<Order> hasKeySearch(String keySearch) {
    return (root, query, cb) -> {
      if (keySearch == null || keySearch.isBlank())
        return null;
      String pattern = "%" + keySearch.toLowerCase().trim() + "%";

      return cb.or(
          cb.like(cb.lower(root.get("cancelOrderReason")), pattern),
          cb.like(cb.lower(root.get("user").get("userName")), pattern), // Buyer name
          cb.like(cb.lower(root.get("address").get("addressDetail")), pattern) // Address
      );
    };
  }

  public static Specification<Order> hasOrderStatus(OrderStatus orderStatus) {
    return (root, query, cb) -> {
      if (orderStatus == null)
        return null;
      return cb.equal(root.get("orderStatus"), orderStatus);
    };
  }

  public static Specification<Order> hasOrderDate(LocalDateTime orderDateFrom, LocalDateTime orderDateTo) {
    return (root, query, cb) -> {
      if (orderDateFrom == null && orderDateTo == null)
        return null;

      if (orderDateFrom != null && orderDateTo != null) {
        return cb.between(root.get("orderDate"), orderDateFrom, orderDateTo);
      } else if (orderDateFrom != null) {
        return cb.greaterThanOrEqualTo(root.get("orderDate"), orderDateFrom);
      } else {
        return cb.lessThanOrEqualTo(root.get("orderDate"), orderDateTo);
      }
    };
  }

}
