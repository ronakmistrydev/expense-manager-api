package com.main.expensemanagerapi.repository;

import com.main.expensemanagerapi.domain.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

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
        List<Category> categories = new LinkedList<>();
        return categories;
    }

    @Override
    public void save(Category obj) {}
}
