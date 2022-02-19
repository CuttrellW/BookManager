package com.BookManager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.BookManager.domain.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long>{
    
}
