package com.main.expensemanagerapi.repository.organizationEntity;

import com.main.expensemanagerapi.domain.Organization;
import com.main.expensemanagerapi.repository.EntityRepository;

public interface OrganizationEntityRepository extends EntityRepository<Organization> {
    Organization getByUserSub(String userSub);
    boolean hasOrganizationByUserSub(String userSub);
}
