package com.aumones.tools.communs.data.repository;

import com.aumones.tools.communs.data.model.AbstractModel;
import com.aumones.tools.communs.data.repository.custom.AbstractRepositoryCustom;
import com.aumones.tools.communs.web.dto.request.AbstractSearchRequestDto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AbstractRepository<T extends AbstractModel, S extends AbstractSearchRequestDto>
    extends MongoRepository<T, String>, AbstractRepositoryCustom<T, S> {
}
