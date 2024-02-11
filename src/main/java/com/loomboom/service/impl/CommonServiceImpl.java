package com.loomboom.service.impl;

import org.springframework.stereotype.Service;

import com.loomboom.service.CommonService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommonServiceImpl implements CommonService {

    private final EntityManager entityManager;

    @Override
    public Boolean checkDuplicateValue(Object value, Class<?> table, String field) {
        if (value == null) {
            return false;
        }
        // String tableName = getTableName
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = criteriaBuilder.createQuery(Long.class);

        Root<?> root = query.from(table);

        Predicate predicate = criteriaBuilder.equal(root.get(field), value);
        query.select(criteriaBuilder.count(root)).where(predicate);
        Long count = entityManager.createQuery(query).getSingleResult();

        return count <= 0;
    }

}
