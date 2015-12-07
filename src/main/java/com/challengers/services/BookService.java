package com.challengers.services;

import com.challengers.dto.BookDto;
import com.challengers.entities.Book;
import com.challengers.repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Created by Malika(mxp134930) on 12/6/2015.
 */
@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Transactional(readOnly = false)
    @CachePut(value = "defaultCache", key = "#bookDto.isbn")
    public Book createBook(BookDto bookDto) {
        Book book = new Book(bookDto.getBookTitle(),bookDto.getAuthorNames(), bookDto.getPublisherNames(), bookDto.getPublishedYear(),
                bookDto.getIsbn(), bookDto.getLanguage(), bookDto.getPrice(), bookDto.getQuantity(), bookDto.getSold());
        return bookRepository.save(book);
    }

    @Transactional(readOnly = false)
    @CacheEvict(value = "defaultCache", key = "#bookDto.isbn")
    public Book updateBook(BookDto bookDto) {
        Book book = bookRepository.findOne(bookDto.getBookId());
        if(book != null) {
            book.setBookTitle(bookDto.getBookTitle());
            book.setAuthorNames(bookDto.getAuthorNames());
            book.setPublisherNames(bookDto.getPublisherNames());
            book.setPublishedYear(bookDto.getPublishedYear());
            book.setIsbn(bookDto.getIsbn());
            book.setLanguage(bookDto.getLanguage());
            book.setPrice(bookDto.getPrice());
            book.setQuantity(bookDto.getQuantity());
            book.setSold(bookDto.getSold());
            return bookRepository.save(book);
        } else
            return null;
    }

    @Transactional(readOnly = true)
    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "defaultCache", key = "#isbn")
    public Book findBookByIsbn(String isbn) {
        System.out.println("Fetching data from database by isbn : " + isbn);
        return bookRepository.findByIsbn(isbn);
    }


    @Transactional(readOnly = true)
    public List<Book> findBookByTitle(String title) {
        return bookRepository.findByBookTitle(title);
    }

    @Transactional(readOnly = true)
    public List<Book> findBookByAuthor(String author) {
        return bookRepository.findByAuthorName(author);
    }

    @Transactional(readOnly = true)
    public List<Book> findBookByPublisher(String publisher) {
        return bookRepository.findByAuthorName(publisher);
    }

    @Transactional(readOnly = true)
    public List<Book> findBookByLanguage(String language) {
        return bookRepository.findByLanguage(language);
    }


}
