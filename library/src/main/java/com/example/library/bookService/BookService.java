package com.example.library.bookService;

import java.util.List;

import com.example.library.bookEntity.Book;

public interface BookService {
    List<Book> getAllBooks();
    List<Book> getByIsbn(String isbn);
    List<Book> getByTitre(String titre);
    List<Book> getByAuteur(String auteur);
    Book getBookById(Long id);
    Book updateBook(Long id,Book book);
    Book createBook(Book book);
    void deleteBook(Long id);


}