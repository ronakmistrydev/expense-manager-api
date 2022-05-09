package com.main.expensemanagerapi.entity;

import com.main.expensemanagerapi.domain.Category;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("category")
public class CategoryEntity extends RootEntity {

    private final String createdBy;
    private final String name;
    private final String parentId;

    public CategoryEntity(String id, String createdBy, String name, String parentId) {
        super(id);
        this.createdBy = createdBy;
        this.name = name;
        this.parentId = parentId;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public String getName() {
        return name;
    }

    public String getParentId() {
        return parentId;
    }

    public static Category toDomain(CategoryEntity entity) {
        return new Category(
                entity.getId(),
                entity.getCreatedBy(),
                entity.getName(),
                ObjectUtils.isEmpty(entity.getParentId()) ? null : entity.getParentId()
        );
    }
}
