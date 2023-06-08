package com.aumones.tools.communs.exemple.mongo.data.repository;

import com.aumones.tools.communs.data.repository.mongo.MongoAbstractRepository;
import com.aumones.tools.communs.exemple.mongo.web.dto.request.MongoSearchRequestDto;
import com.aumones.tools.communs.exemple.mongo.data.model.MongoModel;
import com.aumones.tools.communs.exemple.mongo.data.repository.custom.MongoRepositoryCustom;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoRepository extends MongoAbstractRepository<MongoModel, MongoSearchRequestDto>, MongoRepositoryCustom {
}
