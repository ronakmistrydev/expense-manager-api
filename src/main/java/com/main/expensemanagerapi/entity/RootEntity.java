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
}
