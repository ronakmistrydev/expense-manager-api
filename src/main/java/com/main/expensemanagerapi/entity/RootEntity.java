package com.main.expensemanagerapi.entity;

import org.springframework.data.annotation.Id;

public abstract class RootEntity {
    @Id
    private final String id;

    RootEntity(String id) {
        this.id = id;
    }
}
