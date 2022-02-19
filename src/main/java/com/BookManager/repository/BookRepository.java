package com.BookManager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.BookManager.domain.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{
    
}
