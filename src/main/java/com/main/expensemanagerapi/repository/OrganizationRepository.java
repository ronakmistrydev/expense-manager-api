package com.main.expensemanagerapi.repository;

import com.main.expensemanagerapi.domain.Organization;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrganizationRepository extends MongoRepository<Organization, String> { }
