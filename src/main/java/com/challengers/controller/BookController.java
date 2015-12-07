package com.challengers.controller;

import com.challengers.dto.BookDto;
import com.challengers.entities.Book;
import com.challengers.services.BookService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

/**
 * Created by Malika(mxp134930) on 11/21/2015.
 */

@RestController
@RequestMapping("/book")

public class BookController {
    @Autowired
    private BookService bookService;

    @RequestMapping("/getbooks")
    public List<Book> getAllBooks() throws JsonProcessingException {
        return bookService.getAllBooks();
    }

    @RequestMapping("/title/{bookTitle}")
    public List<Book> getByName(@PathVariable String bookTitle) throws JsonProcessingException {
        return bookService.findBookByTitle(bookTitle);
    }

    @RequestMapping("/author/{authorName}")
    public List<Book> getByAuthor(@PathVariable String authorName) throws JsonProcessingException {
        return bookService.findBookByAuthor(authorName);
    }

    @RequestMapping("/publisher/{publisherName}")
    public List<Book> getByPublisher(@PathVariable String publisherName) throws JsonProcessingException {
        return bookService.findBookByPublisher(publisherName);
    }

    @RequestMapping("/isbn/{isbn}")
    public Book getByIsbn(@PathVariable String isbn) throws JsonProcessingException {
        return bookService.findBookByIsbn(isbn);
    }

    @RequestMapping("/language/{language}")
    public List<Book> getByLanguage(@PathVariable String language) throws JsonProcessingException {
        return bookService.findBookByLanguage(language);
    }

    @RequestMapping(value = "/addbook", method = RequestMethod.POST)
    public ResponseEntity<?> addBook(@RequestBody BookDto bookDto){
        HttpHeaders httpHeaders = new HttpHeaders();
        Book book = bookService.createBook(bookDto);
        httpHeaders.setLocation(ServletUriComponentsBuilder
                .fromCurrentRequest()
                .buildAndExpand()
                .toUri());
        return new ResponseEntity<>("Book Added Successfully, book id : " + book.getBookId(), httpHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/updatebook", method = RequestMethod.POST)
    public ResponseEntity<?> updateBook(@RequestBody BookDto bookDto){
        HttpHeaders httpHeaders = new HttpHeaders();
        Book bookToUpdate = bookService.updateBook(bookDto);
        if(bookToUpdate != null){
            httpHeaders.setLocation(ServletUriComponentsBuilder
            .fromCurrentRequest()
            .buildAndExpand()
            .toUri());
            return new ResponseEntity<>("Book Updated Successfully", httpHeaders, HttpStatus.OK);
        }
        else {
            httpHeaders.setLocation(ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .buildAndExpand()
                    .toUri());
            return new ResponseEntity<>("Book not found, book Id : " + bookDto.getBookId(), httpHeaders, HttpStatus.NOT_FOUND);

        }
    }
}
