package com.main.expensemanagerapi.repository;

import java.util.List;

public interface EntityRepository<T> {
    T getById(String id);
    List<T> findAll();
    void save(String id, T obj);
}
