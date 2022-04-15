package com.main.expensemanagerapi.domain;

public class SubCategory {

    private String id;
    private String parentId;
    private String name;

    void create(String id, String parentId, String name) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
    }
}
