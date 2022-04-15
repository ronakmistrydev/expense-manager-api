package com.main.expensemanagerapi.entity;

import com.main.expensemanagerapi.domain.Organization;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("organization")
public class OrganizationEntity extends RootEntity {

    public OrganizationEntity(String id) {
        super(id);
    }

    public static OrganizationEntity toEntity(Organization organization) {
        return new OrganizationEntity(organization.getId());
    }
}
