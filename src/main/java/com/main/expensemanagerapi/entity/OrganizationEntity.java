package com.main.expensemanagerapi.entity;

import com.main.expensemanagerapi.domain.Organization;
import com.main.expensemanagerapi.vo.UserVo;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document("organization")
public class OrganizationEntity extends RootEntity {

    private UserVo owner;
    private List<UserVo> users;
    public OrganizationEntity(String id, UserVo owner, List<UserVo> users) {
        super(id);
        this.owner = owner;
        this.users = users;
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    public UserVo getOwner() {
        return owner;
    }

    public List<UserVo> getUsers() {
        return users;
    }

    public static OrganizationEntity toEntity(Organization organization) {
        return new OrganizationEntity(
                organization.getId(),
                organization.getOwner(),
                organization.getUsers().values().stream().toList()
        );
    }

    public static Organization toDomain(OrganizationEntity organization) {
        return new Organization(
          organization.getId(),
          organization.getOwner(),
          organization.getUsers()
        );
    }
}
