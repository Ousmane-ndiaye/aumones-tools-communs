package com.aumones.tools.communs.data.repository;

import com.aumones.tools.communs.data.model.AbstractModel;
import com.aumones.tools.communs.web.dto.request.AbstractSearchRequestDto;
import org.springframework.stereotype.Repository;


@Repository
public interface AbstractRepository<T extends AbstractModel, S extends AbstractSearchRequestDto>
    extends AbstractRepositoryCustom<T, S> {
}
