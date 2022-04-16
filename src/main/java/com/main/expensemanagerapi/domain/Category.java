package com.main.expensemanagerapi.domain;

import java.util.Date;
import java.util.HashMap;

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

    public String getId() {
        return id;
    }

    public void addSubCategory(String name, String by) {
        String subCategoryId = "subcategory-create"+new Date().getTime();
        this.subCategories.put(
            subCategoryId,
            new SubCategory(subCategoryId, this.getId(), by, name)
        );
    }
}
