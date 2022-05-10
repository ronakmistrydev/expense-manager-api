package com.main.expensemanagerapi.repository;

public interface EntityRepository<T> {
    T getById(final String id);
    void save(final T obj);
}
