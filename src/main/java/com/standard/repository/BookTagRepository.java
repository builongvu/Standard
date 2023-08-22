package com.standard.repository;

import com.standard.entity.BookTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookTagRepository extends JpaRepository<BookTag, Integer> {
}
