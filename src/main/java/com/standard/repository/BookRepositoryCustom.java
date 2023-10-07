package com.standard.repository;

import com.standard.dto.request.BookSearchRequest;
import com.standard.dto.response.BookSearchResponse;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepositoryCustom {

    BookSearchResponse search(BookSearchRequest bookSearchRequest);

}
