package com.challengers.repo;

import com.challengers.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Malika(mxp134930) on 11/15/2015.
 */
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByBookTitle(String title);

    @Query("select distinct book from Book book where (:authorName) in elements(book.authorNames)")
    List<Book> findByAuthorName(@Param("authorName")String authorName);

    @Query("select distinct book from Book book where (:publisherName) in elements(book.publisherNames)")
    List<Book> findByPublisherName(@Param("publisherName")String publisherName);

    Book findByIsbn(String isbn);

    List<Book> findByLanguage(String language);
}
