package com.main.expensemanagerapi.repository.accountEntity;

import com.main.expensemanagerapi.domain.account.Account;
import com.main.expensemanagerapi.repository.EntityRepository;

import java.util.List;

public interface AccountEntityRepository extends EntityRepository<Account> {
    List<Account> findByOrganizationId(final String organizationId);
}
