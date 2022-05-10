package com.main.expensemanagerapi.repository.organizationEntity;

import com.main.expensemanagerapi.domain.Organization;
import com.main.expensemanagerapi.entity.OrganizationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

@Component
public class OrganizationEntityMongoDbRepository implements OrganizationEntityRepository {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public OrganizationEntityMongoDbRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Organization getById(String id) {
        OrganizationEntity organizationEntity = mongoTemplate.findById(id, OrganizationEntity.class);
        assert organizationEntity != null;
        return OrganizationEntity.toDomain(organizationEntity);
    }

    @Override
    public void save(Organization organization) {
        mongoTemplate.save(OrganizationEntity.toEntity(organization));
    }

    @Override
    public Organization getByUserSub(String userSub) {
        Query query = new Query();
        query.addCriteria(Criteria.where("owner.sub").is(userSub));
        OrganizationEntity organizationEntity = mongoTemplate.findOne(query, OrganizationEntity.class);
        assert organizationEntity != null;
        return OrganizationEntity.toDomain(organizationEntity);
    }

    @Override
    public boolean hasOrganizationByUserSub(String userSub) {
        Query query = new Query();
        query.addCriteria(Criteria.where("owner.sub").is(userSub));
        return mongoTemplate.exists(query, OrganizationEntity.class);
    }
}
