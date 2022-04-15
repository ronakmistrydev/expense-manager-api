package com.main.expensemanagerapi.new_domain;

import java.util.HashMap;
import java.util.HashSet;

public class Category {

    private final String id;
    private final String name;
    private final String createdBy;

    private HashMap<String, SubCategory> subCategories;

    public Category(String id, String name, String createdBy) {
        this.id = id;
        this.name = name;
        this.createdBy = createdBy;
        this.subCategories = new HashMap<>();
    }
}
