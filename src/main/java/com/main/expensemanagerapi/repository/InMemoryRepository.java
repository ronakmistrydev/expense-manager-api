package com.main.expensemanagerapi.repository;

import java.util.HashMap;
import java.util.List;

public class InMemoryRepository<T> implements EntityRepository<T> {
    private final HashMap<String, T> map = new HashMap();

    @Override
    public T getById(String id) {
        return this.map.get(id);
    }

    @Override
    public List<T> findAll() {
        return this.map.values().stream().toList();
    }

    @Override
    public void save(String id, T obj) {
        this.map.put(id, obj);
    }
}
