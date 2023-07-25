package com.aumones.tools.communs.data.repository.mongo;

import com.aumones.tools.communs.data.model.mongo.MongoAbstractModel;
import com.aumones.tools.communs.data.repository.AbstractRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;


@NoRepositoryBean
public interface MongoAbstractRepository<T extends MongoAbstractModel> extends AbstractRepository<String, T>,
    MongoRepository<T, String> {

}
