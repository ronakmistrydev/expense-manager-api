package com.main.expensemanagerapi.repository;

import com.main.expensemanagerapi.domain.Category;
import com.main.expensemanagerapi.entity.CategoryEntity;
import com.main.expensemanagerapi.entity.SubCategoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
        List<CategoryEntity> categoryEntities = this.mongoTemplate.findAll(CategoryEntity.class);

        categoryEntities.forEach((categoryEntity) -> {
            Query query = new Query();
            query.addCriteria(Criteria.where("parentId").is(categoryEntity.getId()));
            List<SubCategoryEntity> subCategories = this.mongoTemplate.find(query, SubCategoryEntity.class);
            categories.add(CategoryEntity.toDomain(categoryEntity, subCategories));
        });

        return categories;
    }

    @Override
    public void save(Category obj) {}
}
