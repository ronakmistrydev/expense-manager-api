package com.main.expensemanagerapi.entity;

import com.main.expensemanagerapi.domain.Category;
import com.main.expensemanagerapi.domain.SubCategory;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.List;

@Document("category")
public class CategoryEntity extends RootEntity {

    private final String name;

    private final String createdBy;

    private final HashMap<String, SubCategoryEntity> subCategories;

    public CategoryEntity(String id, String name, String createdBy, HashMap<String, SubCategoryEntity> subCategories) {
        super(id);
        this.name = name;
        this.createdBy = createdBy;
        this.subCategories = subCategories;
    }

    public String getName() {
        return name;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public HashMap<String, SubCategoryEntity> getSubCategories() {
        return subCategories;
    }

    public static CategoryEntity toEntity(Category domain) {
        return new CategoryEntity(
                domain.getId(),
                domain.getName(),
                domain.getCreatedBy(),
                SubCategoryEntity.toEntityMap(domain.getSubCategories())
        );
    }

    public static Category toDomain(CategoryEntity entity, List<SubCategoryEntity> subCategoryEntities) {
        HashMap<String, SubCategory> subCategoryHashMap = new HashMap<>();

        subCategoryEntities
                .forEach((value) -> subCategoryHashMap.put(entity.getId(), SubCategoryEntity.toDomain(entity.getId(), value)));

        return new Category(
                entity.getId(),
                entity.getName(),
                entity.getCreatedBy(),
                subCategoryHashMap
        );
    }
}
