package com.aumones.tools.communs.web.validator.uniqueObject;

import com.aumones.tools.communs.utils.designs.UniqueObjectValidatorAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.List;

public class UniqueObjectValidator implements ConstraintValidator<UniqueObject, UniqueObjectValidatorAdapter> {

    @Autowired
    private MongoTemplate mongoTemplate;

    private Class<?> document;

    @Override
    public void initialize(UniqueObject uniqueObject) {
        this.document = uniqueObject.document();
    }

    @Override
    public boolean isValid(UniqueObjectValidatorAdapter object, ConstraintValidatorContext context) {
        if (object == null) return false;

        List<Criteria> listCriteria = object.buildUniqueObjectValidatorCriteria();

        final Query query = new Query().addCriteria(new Criteria().andOperator(listCriteria.toArray(new Criteria[0])));

        return !mongoTemplate.exists(query, document);
    }
}
