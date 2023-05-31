package com.aumones.tools.communs.data.repository.mongo;

import com.aumones.tools.communs.data.model.mongo.MongoAbstractModel;
import com.aumones.tools.communs.web.dto.request.AbstractSearchRequestDto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoAbstractRepository<T extends MongoAbstractModel, S extends AbstractSearchRequestDto>
    extends MongoRepository<T, String>, MongoAbstractRepositoryCustom<T, S> {
}
