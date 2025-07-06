package com.example.library.bookService.impl;

import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.library.bookEntity.Book;
import com.example.library.bookRepository.BookRepository;
import com.example.library.bookService.BookService;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();

    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);

    }

    @Override
    public List<Book> searchBooks(String keyword) {
        return bookRepository.searchBooks(keyword);
    }

    @Override
    public Book createBook(Book book) {
        return bookRepository.save(book);

    }

    @Override
    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public Book updateBook(Long id, Book book) {
        return bookRepository.findById(id).map(existingBook -> {
            existingBook.setTitre(book.getTitre());
            existingBook.setAuteur(book.getAuteur());
            existingBook.setIsbn(book.getIsbn());
            return bookRepository.save(existingBook);
        }).orElse(null);
    }

    @Override
    public List<Book> getByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    @Override
    public List<Book> getByTitre(String titre) {
        return bookRepository.findByTitre(titre);
    }

    @Override
    public List<Book> getByAuteur(String auteur) {
        return bookRepository.findByAuteur(auteur);
    }

    @Override
    public List<Book> findByCategory(String category) {
        return bookRepository.findByCategory(category);
    }
    @Override
    public List<Book> getAllBooksSortedBy(String field) {
        return bookRepository.getAllBooksSortedBy(field);
    }
    @Override
public Page<Book> getBookByPage(int page, int size, String sortBy) {
    Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
    return bookRepository.findAll(pageable);
}
}