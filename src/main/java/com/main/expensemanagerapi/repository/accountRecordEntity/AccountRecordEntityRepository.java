package com.main.expensemanagerapi.repository.accountRecordEntity;

import com.main.expensemanagerapi.domain.AccountRecord;
import com.main.expensemanagerapi.repository.EntityRepository;

import java.util.List;

public interface AccountRecordEntityRepository extends EntityRepository<AccountRecord> {
    List<AccountRecord> findByOrganizationId(final String organizationId);
}
