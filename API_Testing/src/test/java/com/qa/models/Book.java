package com.qa.models;
/**
 * This class represents a Book entity in our system.
 */
public class Book {
    private Integer id;
    private String title;
    private String author;

    public Book() {
    }
    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
