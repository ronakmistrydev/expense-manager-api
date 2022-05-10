package com.main.expensemanagerapi.repository.categoryEntity;

import com.main.expensemanagerapi.domain.Category;
import com.main.expensemanagerapi.entity.CategoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CategoryEntityMongoDbRepository implements CategoryEntityRepository {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public CategoryEntityMongoDbRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Category getById(String id) {
        return null;
    }

    @Override
    public void save(Category obj) {}

    @Override
    public List<Category> findAll() {
        return mongoTemplate.findAll(CategoryEntity.class)
                .stream()
                .map(CategoryEntity::toDomain)
                .collect(Collectors.toList());
    }
}
