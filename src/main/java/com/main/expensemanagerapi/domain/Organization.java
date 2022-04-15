package com.main.expensemanagerapi.domain;

import java.io.Serializable;

public class Organization implements Serializable {
    private String id;

    public String getId() {
        return this.id;
    }

    public Organization create(String id) {
        this.id = id;
        return this;
    }
}
