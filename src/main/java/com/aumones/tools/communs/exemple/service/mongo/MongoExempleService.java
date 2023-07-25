package com.aumones.tools.communs.exemple.service.mongo;

import com.aumones.tools.communs.exemple.data.mongo.model.MongoExempleModel;
import com.aumones.tools.communs.exemple.web.dto.request.MongoExempleCreateRequestDto;
import com.aumones.tools.communs.exemple.web.dto.request.ExempleSearchRequestDto;
import com.aumones.tools.communs.exemple.web.dto.request.MongoExempleUpdateRequestDto;
import com.aumones.tools.communs.service.AbstractCRUDAndSearchService;

public interface MongoExempleService extends AbstractCRUDAndSearchService<String, MongoExempleModel, ExempleSearchRequestDto,
    MongoExempleCreateRequestDto, MongoExempleUpdateRequestDto> {
}
