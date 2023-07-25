package com.aumones.tools.communs.exemple.data.mongo.repository.customQuery;

import com.aumones.tools.communs.data.repository.AbstractCustomQuery;
import com.aumones.tools.communs.exemple.data.mongo.model.MongoExempleModel;
import com.aumones.tools.communs.exemple.web.dto.request.ExempleSearchRequestDto;

public interface MongoExempleCustomQuery extends AbstractCustomQuery<String, MongoExempleModel, ExempleSearchRequestDto> {
}
