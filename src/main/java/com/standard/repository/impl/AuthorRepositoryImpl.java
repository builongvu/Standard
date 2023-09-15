package com.standard.repository.impl;

import com.standard.dto.response.AuthorResponse;
import com.standard.repository.AuthorRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AuthorRepositoryImpl implements AuthorRepositoryCustom {

    @PersistenceContext(unitName = "entityManagerFactory")
    private EntityManager entityManager;

//    @Override
//    public List<AuthorResponse> findAllCustom() {
//        // Định nghĩa câu truy vấn JPQL
//        String jpql = "SELECT NEW com.standard.dto.response.AuthorResponse(a.name, a.dateOfBirth, a.hometown, a.description, " +
//                "(SELECT NEW com.standard.dto.response.BookResponse(b.name, b.publicationDate, b.author.id) FROM Book b WHERE b.author.id = a.id)) " +
//                "FROM Author a";
//
//        Query query = entityManager.createQuery(jpql);
//
//        List<AuthorResponse> authors = new ArrayList<>();
//
//        List<Object[]> results = query.getResultList();
//
//        for (Object[] result : results) {
//            AuthorResponse author = (AuthorResponse) result[0];
//            authors.add(author);
//        }
//
//        return authors;
//    }

    @Override
    public List<AuthorResponse> findAllCustom() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT name, date_of_birth, hometown, description, ");
        sql.append("(SELECT name, publication_date FROM book WHERE author_id = a.id) ");
        sql.append("FROM author a");

        Query query = entityManager.createNativeQuery(sql.toString());
        List<Object[]> results = query.getResultList();


        return null;
    }

}
