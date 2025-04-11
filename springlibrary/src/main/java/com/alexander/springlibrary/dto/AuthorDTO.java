package com.alexander.springlibrary.dto;

public class AuthorDTO {

    private Long id;
    private String name;
    private String biography; // Добавляем поле biography

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

    public String getBiography() {
        return biography; // Геттер для biography
    }

    public void setBiography(String biography) {
        this.biography = biography; // Сеттер для biography
    }
}
