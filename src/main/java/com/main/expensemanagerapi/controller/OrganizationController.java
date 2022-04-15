package com.main.expensemanagerapi.controller;

import com.main.expensemanagerapi.domain.Organization;
import com.main.expensemanagerapi.repository.InMemoryRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/organization")
public class OrganizationController {

    private final InMemoryRepository organizationRepository = new InMemoryRepository<Organization>();

    @GetMapping
    List<Organization> findAll() {
        return this.organizationRepository.findAll();
    }

    @PostMapping
    void create() {
        String id = "create-account-"+ new Date().getTime();
        this.organizationRepository.save(id, new Organization().create(id));
    }
}
