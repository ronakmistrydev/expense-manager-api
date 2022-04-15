package com.main.expensemanagerapi.entity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("organization")
public class OrganizationEntity extends RootEntity {

    public OrganizationEntity(String id) {
        super(id);
    }
}
