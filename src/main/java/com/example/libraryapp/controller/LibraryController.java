package com.example.libraryapp.controller;

import com.example.libraryapp.entity.Author;
import com.example.libraryapp.entity.Book;
import com.example.libraryapp.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class LibraryController {

    private final LibraryService libraryService;

    @Autowired
    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping("/")
    public String listBooks(Model model) {
        List<Book> books = libraryService.getAllBooksWithAuthors();
        model.addAttribute("books", books);
        return "list";
    }

    @GetMapping("/add-book")
    public String showAddBookForm(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("authors", libraryService.getAllAuthors());
        return "add-book";
    }

    @PostMapping("/add-book")
    public String addBook(@ModelAttribute("book") Book book, @RequestParam(required = false) String newAuthorName) {
        try {
            if (newAuthorName != null && !newAuthorName.trim().isEmpty()) {
                Author author = new Author();
                author.setName(newAuthorName);
                book.setAuthor(author);
            } else if (book.getAuthor() == null || book.getAuthor().getId() == null) {
                // simple validation
                throw new IllegalArgumentException("Author must be selected or provided");
            }
            libraryService.saveBook(book);
            return "redirect:/";
        } catch (Exception e) {
            return "redirect:/add-book?error=true";
        }
    }

    @GetMapping("/edit-book/{id}")
    public String showEditBookForm(@PathVariable("id") Long id, Model model) {
        Book book = libraryService.getBookById(id);
        if (book == null) {
            return "redirect:/";
        }
        model.addAttribute("book", book);
        model.addAttribute("authors", libraryService.getAllAuthors());
        return "edit-book";
    }

    @PostMapping("/update-book/{id}")
    public String updateBook(@PathVariable("id") Long id, @ModelAttribute("book") Book book) {
        try {
            Book existingBook = libraryService.getBookById(id);
            if (existingBook != null) {
                existingBook.setTitle(book.getTitle());
                existingBook.setPublicationYear(book.getPublicationYear());
                existingBook.setAuthor(book.getAuthor());
                libraryService.saveBook(existingBook);
            }
            return "redirect:/";
        } catch (Exception e) {
            return "redirect:/edit-book/" + id + "?error=true";
        }
    }
}
