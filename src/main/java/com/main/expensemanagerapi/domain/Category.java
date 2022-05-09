package com.main.expensemanagerapi.domain;

public class Category {

    private final String id;
    private final String createdBy;
    private final String name;
    private final String parentId;

    public Category(String id, String createdBy, String name) {
        this.id = id;
        this.createdBy = createdBy;
        this.name = name;
        this.parentId = null;
    }

    public Category(String id, String createdBy, String name, String parentId) {
        this.id = id;
        this.createdBy = createdBy;
        this.name = name;
        this.parentId = parentId;
    }

    public String getId() {
        return id;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public String getName() {
        return name;
    }

    public String getParentId() {
        return parentId;
    }
}
