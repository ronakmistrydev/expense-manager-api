package com.main.expensemanagerapi.entity;

import org.springframework.data.annotation.Id;

import java.util.Date;

public abstract class RootEntity {
    @Id
    private final String id;
    protected Date createdAt;
    protected Date updatedAt;

    RootEntity(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }
}
