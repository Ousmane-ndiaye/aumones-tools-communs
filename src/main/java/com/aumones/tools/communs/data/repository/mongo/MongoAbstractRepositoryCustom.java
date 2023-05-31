package com.aumones.tools.communs.data.repository.mongo;

import com.aumones.tools.communs.data.model.mongo.MongoAbstractModel;
import com.aumones.tools.communs.data.repository.AbstractRepositoryCustom;
import com.aumones.tools.communs.web.dto.request.AbstractSearchRequestDto;

public interface MongoAbstractRepositoryCustom<T extends MongoAbstractModel, S extends AbstractSearchRequestDto>
    extends AbstractRepositoryCustom<T, S> {

}
