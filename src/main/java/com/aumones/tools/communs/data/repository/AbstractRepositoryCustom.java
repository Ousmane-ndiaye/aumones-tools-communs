package com.aumones.tools.communs.data.repository;

import com.aumones.tools.communs.data.model.AbstractModel;
import com.aumones.tools.communs.web.dto.request.AbstractSearchRequestDto;

public interface AbstractRepositoryCustom<ID, T extends AbstractModel<ID>, S extends AbstractSearchRequestDto>
    extends AbstractRepository<ID, T>, AbstractCustomQuery<ID, T, S> {

}
