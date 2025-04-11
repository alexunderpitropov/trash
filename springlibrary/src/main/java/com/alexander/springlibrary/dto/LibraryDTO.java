package com.alexander.springlibrary.dto;

import java.util.List;

public class LibraryDTO {

    private Long id;
    private String name;
    private String address; // Добавляем поле address
    private List<String> books;

    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() { // Геттер для address
        return address;
    }

    public void setAddress(String address) { // Сеттер для address
        this.address = address;
    }

    public List<String> getBooks() {
        return books;
    }

    public void setBooks(List<String> books) {
        this.books = books;
    }
}
