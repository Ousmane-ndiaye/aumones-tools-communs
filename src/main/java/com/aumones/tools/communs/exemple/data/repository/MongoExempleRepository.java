package com.aumones.tools.communs.exemple.data.repository;

import com.aumones.tools.communs.data.repository.mongo.MongoAbstractRepositoryCustom;
import com.aumones.tools.communs.exemple.data.model.MongoExempleModel;
import com.aumones.tools.communs.exemple.web.dto.request.ExempleSearchRequestDto;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoExempleRepository extends MongoAbstractRepositoryCustom<MongoExempleModel, ExempleSearchRequestDto> {

}
