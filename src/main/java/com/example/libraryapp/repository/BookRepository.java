package com.example.libraryapp.repository;

import com.example.libraryapp.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    
    @Query("SELECT b FROM Book b JOIN FETCH b.author")
    List<Book> findAllWithAuthors();

    @Query(value = "SELECT b FROM Book b JOIN FETCH b.author WHERE " +
                   "(:keyword IS NULL OR LOWER(b.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
                   "LOWER(b.author.name) LIKE LOWER(CONCAT('%', :keyword, '%')))",
           countQuery = "SELECT count(b) FROM Book b LEFT JOIN b.author WHERE " +
                        "(:keyword IS NULL OR LOWER(b.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
                        "LOWER(b.author.name) LIKE LOWER(CONCAT('%', :keyword, '%')))")
    Page<Book> searchBooks(@Param("keyword") String keyword, Pageable pageable);
}
