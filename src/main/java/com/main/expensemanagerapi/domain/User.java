package com.main.expensemanagerapi.domain;

public class User {
    private String id;
    private String sub;

    void create(String id, String sub) {
        this.id = id;
        this.sub = sub;
    }
}
