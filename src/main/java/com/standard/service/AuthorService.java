package com.standard.service;

import com.standard.dto.AuthorRequest;
import com.standard.entity.Author;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AuthorService {

    List<Author> getAll();

    Author getById(Integer id);

    Author create(AuthorRequest authorRequest);

    Author update(AuthorRequest authorRequest);

    void delete(Integer id);

}
