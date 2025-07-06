package com.example.library.bookRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.library.bookEntity.Book;


@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByIsbn(String isbn);
    List<Book> findByAuteur(String auteur);
    List<Book> findByTitre(String titre);
    @Query("SELECT b FROM Book b WHERE LOWER(b.titre) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
       "OR LOWER(b.auteur) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
       "OR LOWER(b.isbn) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Book> searchBooks(@Param("keyword") String keyword);
    List<Book> findByCategory(String category);
    List<Book> getAllBooksSortedBy(String field);

}


