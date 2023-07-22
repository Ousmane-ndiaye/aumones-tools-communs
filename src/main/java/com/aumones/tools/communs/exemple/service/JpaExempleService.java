package com.aumones.tools.communs.exemple.service;

import com.aumones.tools.communs.exemple.data.model.JpaExempleModel;
import com.aumones.tools.communs.exemple.web.dto.request.ExempleSearchRequestDto;
import com.aumones.tools.communs.exemple.web.dto.request.JpaExempleCreateRequestDto;
import com.aumones.tools.communs.exemple.web.dto.request.JpaExempleUpdateRequestDto;
import com.aumones.tools.communs.service.AbstractCRUDAndSearchService;

public interface JpaExempleService extends AbstractCRUDAndSearchService<Long, JpaExempleModel, ExempleSearchRequestDto,
    JpaExempleCreateRequestDto, JpaExempleUpdateRequestDto> {
}
