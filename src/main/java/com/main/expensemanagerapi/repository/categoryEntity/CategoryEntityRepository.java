package com.main.expensemanagerapi.repository.categoryEntity;

import com.main.expensemanagerapi.domain.Category;
import com.main.expensemanagerapi.repository.EntityRepository;

import java.util.List;

public interface CategoryEntityRepository extends EntityRepository<Category> {
    List<Category> findAll();
}
