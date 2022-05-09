package com.main.expensemanagerapi.repository;

import com.main.expensemanagerapi.domain.Category;
import com.main.expensemanagerapi.entity.CategoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryRepository implements EntityRepository<Category> {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Category getById(String id) {
        return null;
    }

    @Override
    public List<Category> findAll() {
        return mongoTemplate.findAll(CategoryEntity.class)
                .stream()
                .map(CategoryEntity::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void save(Category obj) {}
}
