package com.main.expensemanagerapi.controller;

import com.main.expensemanagerapi.domain.Organization;
import com.main.expensemanagerapi.repository.OrganizationEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class MutationController {

    @Autowired
    private OrganizationEntityRepository organizationEntityRepository;

    @PostMapping("/register")
    public String register() {
        String organizationId = UUID.randomUUID().toString();
        String userSub = UUID.randomUUID().toString();
        Organization organization = new Organization(organizationId, userSub);
        organizationEntityRepository.save(organization);
        return organizationId;
    }
}
