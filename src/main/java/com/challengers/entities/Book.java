package com.challengers.entities;

import com.challengers.util.UniqueIdGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Malika(mxp134930) on 11/14/2015.
 */

@Table(name = "book")
@Entity
public class Book implements Serializable {
    @Id
    @Column(name = "book_id")
    private Long bookId;

    @Column(name = "book_title")
    private String bookTitle;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name="book_authors", joinColumns=@JoinColumn(name="book_id"))
    @Column(name = "author_name")
    private Set<String> authorNames = new HashSet<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name="book_publishers", joinColumns=@JoinColumn(name="book_id"))
    @Column(name = "publisher_name")
    private Set<String> publisherNames = new HashSet<>();

    @Column(name = "published_year")
    private int publishedYear;

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "language")
    private String language;

    @Column(name = "price")
    private double price;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "sold")
    private int sold;

    public Book() {
    }

    public Book(String book_title, Set<String> author_name, Set<String> publisher_name, int published_year, String isbn, String language,double price, int quantity, int sold) {
        this.bookId = UniqueIdGenerator.generateId();
        this.bookTitle = book_title;
        this.authorNames = author_name;
        this.publisherNames = publisher_name;
        this.publishedYear = published_year;
        this.isbn = isbn;
        this.language = language;
        this.price = price;
        this.quantity = quantity;
        this.sold = sold;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public Set<String> getAuthorNames() {
        return authorNames;
    }

    public void setAuthorNames(Set<String> authorNames) {
        this.authorNames = authorNames;
    }

    public Set<String> getPublisherNames() {
        return publisherNames;
    }

    public void setPublisherNames(Set<String> publisherNames) {
        this.publisherNames = publisherNames;
    }

    public int getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(int publishedYear) {
        this.publishedYear = publishedYear;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getSold() {
        return sold;
    }

    public void setSold(int sold) {
        if(sold > quantity){
            throw new RuntimeException("Not in Stock");
        }
        this.sold = sold;
    }


}
