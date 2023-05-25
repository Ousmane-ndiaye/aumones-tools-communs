package com.aumones.tools.communs.web.validator.existObject;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ExistObjectValidator implements ConstraintValidator<ExistObject, String> {

    @Autowired
    private MongoTemplate mongoTemplate;

    private Class<?> document;

    private String field;

    @Override
    public void initialize(ExistObject existObject) {
        this.document = existObject.document();
        this.field = existObject.field();
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
