package com.main.expensemanagerapi.domain;

public class Category {

    private String id;
    private String name;

    public void create(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
