package com.standard.repository;

import com.standard.dto.response.AuthorResponse;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepositoryCustom {

    List<AuthorResponse> findAllCustom();

}
