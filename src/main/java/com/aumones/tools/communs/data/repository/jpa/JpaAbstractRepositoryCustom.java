package com.aumones.tools.communs.data.repository.jpa;

import com.aumones.tools.communs.data.model.jpa.JpaAbstractModel;
import com.aumones.tools.communs.data.repository.AbstractRepositoryCustom;
import com.aumones.tools.communs.web.dto.request.AbstractSearchRequestDto;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface JpaAbstractRepositoryCustom<T extends JpaAbstractModel, S extends AbstractSearchRequestDto>
    extends AbstractRepositoryCustom<Long, T, S>, JpaAbstractRepository<T> {

}
