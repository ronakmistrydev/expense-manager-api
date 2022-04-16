package com.main.expensemanagerapi.entity;

import com.main.expensemanagerapi.domain.Organization;
import com.main.expensemanagerapi.vo.UserVo;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document("organization")
public class OrganizationEntity extends RootEntity {
    private List<UserVo> users;
    public OrganizationEntity(String id, List<UserVo> users) {
        super(id);
        this.users = users;
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    public static OrganizationEntity toEntity(Organization organization) {
        return new OrganizationEntity(organization.getId(), organization.getUsers().values().stream().toList());
    }
}
