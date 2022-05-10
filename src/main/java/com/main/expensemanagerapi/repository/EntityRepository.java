package com.main.expensemanagerapi.repository;

public interface EntityRepository<T> {
    T getById(String id);
    void save(T obj);
}
