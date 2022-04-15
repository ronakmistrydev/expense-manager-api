package com.main.expensemanagerapi.repository;

import com.main.expensemanagerapi.domain.Organization;
import com.main.expensemanagerapi.entity.OrganizationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrganizationEntityRepository implements EntityRepository<Organization> {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Organization getById(String id) {
        return null;
    }

    @Override
    public List<Organization> findAll() {
        return null;
    }

    @Override
    public void save(Organization organization) {
        mongoTemplate.save(OrganizationEntity.toEntity(organization));
    }
}
