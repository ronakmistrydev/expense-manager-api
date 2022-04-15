package com.main.expensemanagerapi.domain;

public class User {
    private String id;
    private String sub;

    private String organizationId;

    public String getId() {
        return id;
    }

    public String getSub() {
        return sub;
    }

    public User create(String id, String organizationId, String sub) {
        this.id = id;
        this.organizationId = organizationId;
        this.sub = sub;
        return this;
    }
}
