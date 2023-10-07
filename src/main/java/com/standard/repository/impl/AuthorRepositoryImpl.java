package com.standard.repository.impl;

import com.standard.dto.Paging;
import com.standard.dto.mapper.AuthorMapper;
import com.standard.dto.request.AuthorSearchRequest;
import com.standard.dto.response.AuthorResponse;
import com.standard.dto.response.AuthorSearchResponse;
import com.standard.entity.Author;
import com.standard.repository.AuthorRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AuthorRepositoryImpl implements AuthorRepositoryCustom {

    @PersistenceContext(unitName = "entityManagerFactory")
    private EntityManager entityManager;

    @Override
    public AuthorSearchResponse search(AuthorSearchRequest authorSearchRequest) {
        List<AuthorResponse> authorResponses = new ArrayList<>();
        StringBuilder countJpql = new StringBuilder("SELECT COUNT(1) from Author a ");
        StringBuilder whereClauseJpql = new StringBuilder("WHERE 1 = 1 ");
        Map<String, Object> params = new HashMap<>();

        if (authorSearchRequest.getId() != null) {
            whereClauseJpql.append("AND id = :id ");
            params.put("id", authorSearchRequest.getId());
        }
        if (authorSearchRequest.getName() != null) {
            whereClauseJpql.append("AND name LIKE CONCAT('%', :name, '%') ");
            params.put("name", authorSearchRequest.getName());
        }
        if (authorSearchRequest.getHometown() != null) {
            whereClauseJpql.append("AND hometown LIKE CONCAT('%', :hometown, '%') ");
            params.put("hometown", authorSearchRequest.getHometown());
        }

        countJpql.append(whereClauseJpql);
        Query countQuery = entityManager.createQuery(countJpql.toString());
        params.forEach(countQuery::setParameter);
        Number totalElement = (Number) countQuery.getSingleResult();

        if (totalElement.intValue() > 0) {
            StringBuilder jpql = new StringBuilder("SELECT a FROM Author a ");
            jpql.append(whereClauseJpql);

            Query query = entityManager.createQuery(jpql.toString());
            query.setFirstResult((authorSearchRequest.getPaging().getPage() - 1) * authorSearchRequest.getPaging().getPageSize());
            query.setMaxResults(authorSearchRequest.getPaging().getPageSize());
            params.forEach(query::setParameter);
            List<Author> authors = query.getResultList();
            authorResponses = AuthorMapper.INSTANCE.toResponses(authors);
        }

        return AuthorSearchResponse.builder()
                .content(authorResponses)
                .paging(Paging.builder()
                        .page(authorSearchRequest.getPaging().getPage())
                        .pageSize(authorSearchRequest.getPaging().getPageSize())
                        .totalElement(totalElement.intValue())
                        .build())
                .build();
    }

}
