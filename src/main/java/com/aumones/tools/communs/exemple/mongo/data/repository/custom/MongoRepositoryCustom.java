package com.aumones.tools.communs.exemple.mongo.data.repository.custom;

import com.aumones.tools.communs.data.repository.mongo.MongoAbstractRepositoryCustom;
import com.aumones.tools.communs.exemple.mongo.web.dto.request.MongoSearchRequestDto;
import com.aumones.tools.communs.exemple.mongo.data.model.MongoModel;

public interface MongoRepositoryCustom extends MongoAbstractRepositoryCustom<MongoModel, MongoSearchRequestDto> {
}
