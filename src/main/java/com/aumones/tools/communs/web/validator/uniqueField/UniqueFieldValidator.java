package com.aumones.tools.communs.web.validator.uniqueField;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueFieldValidator implements ConstraintValidator<UniqueField, String> {

    @Autowired
    private MongoTemplate mongoTemplate;

    private Class<?> document;

    private String field;

    @Override
    public void initialize(UniqueField uniqueField) {
        this.document = uniqueField.document();
        this.field = uniqueField.field();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) return true;
        final Query query = new Query();
        query.addCriteria(Criteria.where(field).regex("^.*"+value.toLowerCase()+".*$", "i"));
        return !mongoTemplate.exists(query, document);
    }
}
