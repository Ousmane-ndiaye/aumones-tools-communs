package com.aumones.tools.communs.exemple.data.jpa.repository.customQuery;

import com.aumones.tools.communs.data.repository.AbstractCustomQuery;
import com.aumones.tools.communs.exemple.data.jpa.model.JpaExempleModel;
import com.aumones.tools.communs.exemple.web.dto.request.ExempleSearchRequestDto;

public interface JpaExempleCustomQuery extends AbstractCustomQuery<Long, JpaExempleModel, ExempleSearchRequestDto> {
}
