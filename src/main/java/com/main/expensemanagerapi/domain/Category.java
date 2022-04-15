package com.main.expensemanagerapi.domain;

public class Category {

    private String id;
    private String name;

    void create(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
