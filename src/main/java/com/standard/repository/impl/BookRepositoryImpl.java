package com.standard.repository.impl;

import com.standard.dto.Paging;
import com.standard.dto.mapper.BookMapper;
import com.standard.dto.request.BookSearchRequest;
import com.standard.dto.response.BookResponse;
import com.standard.dto.response.BookSearchResponse;
import com.standard.entity.Book;
import com.standard.repository.BookRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BookRepositoryImpl implements BookRepositoryCustom {

    @PersistenceContext(unitName = "entityManagerFactory")
    private EntityManager entityManager;

    @Override
    public BookSearchResponse search(BookSearchRequest bookSearchRequest) {
        List<BookResponse> bookResponses = new ArrayList<>();
        StringBuilder countJpql = new StringBuilder("SELECT COUNT(1) FROM Book b ");
        StringBuilder whereClauseJpql = new StringBuilder("WHERE 1 = 1 ");
        Map<String, Object> params = new HashMap<>();

        if (bookSearchRequest.getId() != null) {
            whereClauseJpql.append("AND id = :id ");
            params.put("id", bookSearchRequest.getId());
        }
        if (bookSearchRequest.getName() != null) {
            whereClauseJpql.append("AND name LIKE CONCAT('%', :name, '%') ");
            params.put("name", bookSearchRequest.getName());
        }
        if (bookSearchRequest.getAuthorId() != null) {
            whereClauseJpql.append("AND authorId = :authorId ");
            params.put("authorId", bookSearchRequest.getAuthorId());
        }

        countJpql.append(whereClauseJpql);
        Query countQuery = entityManager.createQuery(countJpql.toString());
        params.forEach(countQuery::setParameter);
        Number totalElement = (Number) countQuery.getSingleResult();

        if (totalElement.intValue() > 0) {
            StringBuilder jpql = new StringBuilder("SELECT b FROM Book b ");
            jpql.append(whereClauseJpql);

            Query query = entityManager.createQuery(jpql.toString());
            query.setFirstResult((bookSearchRequest.getPaging().getPage() - 1) * bookSearchRequest.getPaging().getPageSize());
            query.setMaxResults(bookSearchRequest.getPaging().getPageSize());
            params.forEach(query::setParameter);
            List<Book> books = query.getResultList();
            bookResponses = BookMapper.INSTANCE.toResponses(books);
        }

        return BookSearchResponse.builder()
                .content(bookResponses)
                .paging(Paging.builder()
                        .page(bookSearchRequest.getPaging().getPage())
                        .pageSize(bookSearchRequest.getPaging().getPageSize())
                        .totalElement(totalElement.intValue())
                        .build())
                .build();
    }

}
