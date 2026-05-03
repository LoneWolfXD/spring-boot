package com.example.libraryapp.service;

import com.example.libraryapp.entity.Author;
import com.example.libraryapp.entity.Book;
import com.example.libraryapp.repository.AuthorRepository;
import com.example.libraryapp.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class LibraryService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    @Autowired
    public LibraryService(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooksWithAuthors() {
        return bookRepository.findAllWithAuthors();
    }

    public Page<Book> getBooksPaginatedAndSearched(String keyword, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        if (keyword != null && keyword.trim().isEmpty()) {
            keyword = null;
        }
        return bookRepository.searchBooks(keyword, pageable);
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Transactional
    public Book saveBook(Book book) {
        if (book.getAuthor() != null && book.getAuthor().getId() == null) {
            authorRepository.save(book.getAuthor());
        }
        return bookRepository.save(book);
    }

    @Transactional
    public Author saveAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Transactional
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
