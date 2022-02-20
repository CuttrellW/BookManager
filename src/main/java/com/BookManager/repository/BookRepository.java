package com.bookmanager.repository;

import com.bookmanager.domain.Book;
import com.bookmanager.domain.Author;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{
    
    Book findByTitleContainingIgnoreCase(String bookTitle);

    List<Book> findByGenreContainingIgnoreCase(String genre);

    List<Book> findByAuthor(Author author);
}