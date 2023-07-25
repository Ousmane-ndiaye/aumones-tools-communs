package com.aumones.tools.communs.exemple.data.mongo.repository;

import com.aumones.tools.communs.data.repository.mongo.MongoAbstractRepositoryCustom;
import com.aumones.tools.communs.exemple.data.mongo.model.MongoExempleModel;
import com.aumones.tools.communs.exemple.data.mongo.repository.customQuery.MongoExempleCustomQuery;
import com.aumones.tools.communs.exemple.web.dto.request.ExempleSearchRequestDto;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoExempleRepository extends MongoAbstractRepositoryCustom<MongoExempleModel, ExempleSearchRequestDto>,
    MongoExempleCustomQuery {

}
