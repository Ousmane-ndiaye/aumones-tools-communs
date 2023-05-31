package com.aumones.tools.communs.data.repository.jpa;

import com.aumones.tools.communs.data.model.jpa.JpaAbstractModel;
import com.aumones.tools.communs.web.dto.request.AbstractSearchRequestDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface JpaAbstractRepository<T extends JpaAbstractModel, S extends AbstractSearchRequestDto>
    extends JpaRepository<T, Long>, JpaAbstractRepositoryCustom<T, S> {
}
