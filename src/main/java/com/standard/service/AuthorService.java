package com.standard.service;

import com.standard.dto.request.AuthorRequest;
import com.standard.dto.request.AuthorSearchRequest;
import com.standard.dto.response.AuthorResponse;
import com.standard.dto.response.AuthorSearchResponse;
import com.standard.entity.Author;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public interface AuthorService {

    List<Author> getAll();

    Author getById(Long id);

    Author create(AuthorRequest authorRequest);

    Author update(long id, AuthorRequest authorRequest);

    void delete(Long id);

    AuthorSearchResponse search(AuthorSearchRequest authorSearchRequest);

    void exportExcel(HttpServletResponse response) throws IOException;

}
