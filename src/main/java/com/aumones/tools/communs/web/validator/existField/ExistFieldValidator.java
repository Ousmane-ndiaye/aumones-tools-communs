package com.aumones.tools.communs.web.validator.existField;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ExistFieldValidator implements ConstraintValidator<ExistField, String> {

    @Autowired
    private MongoTemplate mongoTemplate;

    private Class<?> document;

    private String field;

    @Override
    public void initialize(ExistField existField) {
        this.document = existField.document();
        this.field = existField.field();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) return true;
        final Query query = new Query();

        if (field.equals("_id")) {
            query.addCriteria(Criteria.where(field).is(new ObjectId(value)));
        } else {
            query.addCriteria(Criteria.where(field).regex("^.*"+value.toLowerCase()+".*$", "i"));
        }

        return mongoTemplate.exists(query, document);
    }
}
