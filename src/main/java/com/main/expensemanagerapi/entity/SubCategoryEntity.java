package com.main.expensemanagerapi.entity;

import com.main.expensemanagerapi.domain.SubCategory;

import java.util.HashMap;

public class SubCategoryEntity extends RootEntity {

    private final String createdBy;

    private final String name;

    public SubCategoryEntity(String id, String createdBy, String name) {
        super(id);
        this.createdBy = createdBy;
        this.name = name;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public String getName() {
        return name;
    }

    public static SubCategoryEntity toEntity(SubCategory domain) {
        return new SubCategoryEntity(
                domain.getId(),
                domain.getCreatedBy(),
                domain.getName()
        );
    }

    public static SubCategory toDomain(String parentId, SubCategoryEntity entity) {
        return new SubCategory(
                entity.getId(),
                parentId,
                entity.getCreatedBy(),
                entity.getName()
        );
    }

    public static HashMap<String, SubCategoryEntity> toEntityMap(HashMap<String, SubCategory> subCategories) {
        HashMap<String, SubCategoryEntity> categoryHashMap = new HashMap<>();
        subCategories.forEach((key, value) -> categoryHashMap.put(key, SubCategoryEntity.toEntity(value)));
        return categoryHashMap;
    }

    public static HashMap<String, SubCategory> toDomainMap( String parentId, HashMap<String, SubCategoryEntity> subCategories) {
        HashMap<String, SubCategory> categoryHashMap = new HashMap<>();
        subCategories.forEach((key, value) -> categoryHashMap.put(key, SubCategoryEntity.toDomain(parentId, value)));
        return categoryHashMap;
    }
}
