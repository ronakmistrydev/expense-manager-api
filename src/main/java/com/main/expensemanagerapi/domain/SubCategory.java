package com.main.expensemanagerapi.domain;

public class SubCategory {
    private final String id;
    private final String parentId;
    private final String createdBy;
    private String name;
    public SubCategory(String id, String parentId, String createdBy, String name) {
        this.id = id;
        this.parentId = parentId;
        this.createdBy = createdBy;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getParentId() {
        return parentId;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public String getName() {
        return name;
    }
}
