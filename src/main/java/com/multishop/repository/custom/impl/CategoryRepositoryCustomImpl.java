package com.multishop.repository.custom.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.multishop.entity.Category;
import com.multishop.model.dto.CategorySearchCriteria;
import com.multishop.repository.custom.CategoryRepositoryCustom;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Repository
public class CategoryRepositoryCustomImpl implements CategoryRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Page<Category> searchCategories(CategorySearchCriteria criteria) {
        StringBuilder jpql = new StringBuilder("SELECT DISTINCT c FROM Category c WHERE 1=1 ");
        StringBuilder countJpql = new StringBuilder("SELECT COUNT(DISTINCT c) FROM Category c WHERE 1=1 ");
        List<Object> params = new ArrayList<>();
        int idx = 1;

        // keySearch
        if (criteria.getKeySearch() != null && !criteria.getKeySearch().isBlank()) {
            jpql.append(" AND LOWER(c.name) LIKE LOWER(CONCAT('%', ?" + idx + ", '%'))");
            countJpql.append(" AND LOWER(c.name) LIKE LOWER(CONCAT('%', ?" + idx + ", '%'))");
            params.add(criteria.getKeySearch());
            idx++;
        }

        // status
        if (criteria.getStatus() != null) {
            jpql.append(" AND c.status = ?" + idx);
            countJpql.append(" AND c.status = ?" + idx);
            params.add(criteria.getStatus());
            idx++;
        }

        // parentId
        if (criteria.getParentId() != null) {
            jpql.append(" AND c.parent.id = ?" + idx);
            countJpql.append(" AND c.parent.id = ?" + idx);
            params.add(criteria.getParentId());
            idx++;
        }

        // level
        if (criteria.getLevel() != null) {
            jpql.append(" AND c.level = ?" + idx);
            countJpql.append(" AND c.level = ?" + idx);
            params.add(criteria.getLevel());
            idx++;
        }

        // path
        if (criteria.getPath() != null && !criteria.getPath().isBlank()) {
            jpql.append(" AND c.path LIKE ?" + idx);
            countJpql.append(" AND c.path LIKE ?" + idx);
            params.add(criteria.getPath() + "%");
            idx++;
        }

        // sort
        jpql.append(" ORDER BY c." + criteria.getSortBy() + " " + criteria.getSortDir());

        TypedQuery<Category> query = entityManager.createQuery(jpql.toString(), Category.class);
        TypedQuery<Long> countQuery = entityManager.createQuery(countJpql.toString(), Long.class);

        for (int i = 0; i < params.size(); i++) {
            query.setParameter(i + 1, params.get(i));
            countQuery.setParameter(i + 1, params.get(i));
        }

        query.setFirstResult(criteria.getPageNo() * criteria.getPageSize());
        query.setMaxResults(criteria.getPageSize());

        List<Category> results = query.getResultList();
        Long total = countQuery.getSingleResult();

        return new PageImpl<>(results, PageRequest.of(criteria.getPageNo(), criteria.getPageSize()), total);
    }
}
