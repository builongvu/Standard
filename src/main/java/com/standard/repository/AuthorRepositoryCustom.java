package com.standard.repository;

import com.standard.dto.request.AuthorSearchRequest;
import com.standard.dto.response.AuthorResponse;
import com.standard.dto.response.AuthorSearchResponse;
import com.standard.entity.Author;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepositoryCustom {

    AuthorSearchResponse search(AuthorSearchRequest authorSearchRequest);

}
