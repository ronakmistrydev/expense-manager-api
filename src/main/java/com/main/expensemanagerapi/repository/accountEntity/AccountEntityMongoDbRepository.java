package com.main.expensemanagerapi.repository.accountEntity;

import com.main.expensemanagerapi.domain.AccountRecord;
import com.main.expensemanagerapi.domain.account.Account;
import com.main.expensemanagerapi.entity.AccountEntity;
import com.main.expensemanagerapi.entity.AccountRecordEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

@Repository
public class AccountEntityMongoDbRepository implements AccountEntityRepository {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public AccountEntityMongoDbRepository(final MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Account getById(final String id) {
        AccountEntity account = this.mongoTemplate.findById(id, AccountEntity.class);
        assert account != null;

        Query query = new Query();
        query.addCriteria(Criteria.where("fromAccountId").is(id));
        List<AccountRecordEntity> accountRecordEntities = this.mongoTemplate.find(query, AccountRecordEntity.class);

        BigDecimal balance = accountRecordEntities
                .stream()
                .map(AccountRecordEntity::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return AccountEntity.toDomain(account, balance);
    }

    @Override
    public void save(Account account) {
        mongoTemplate.save(AccountEntity.toEntity(account));
    }

    @Override
    public List<Account> findByOrganizationId(String organizationId) {
        LinkedList<Account> accounts = new LinkedList<>();
        Query query = new Query();
        query.addCriteria(Criteria.where("organizationId").is(organizationId));
        List<String> accountIds = this.mongoTemplate.find(query, AccountEntity.class).stream().map(AccountEntity::getId).toList();
        for (String accountId : accountIds) accounts.add(this.getById(accountId));
        return accounts;
    }
}
