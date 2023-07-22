package com.aumones.tools.communs.exemple.data.repository;

import com.aumones.tools.communs.data.repository.jpa.JpaAbstractRepositoryCustom;
import com.aumones.tools.communs.exemple.data.model.JpaExempleModel;
import com.aumones.tools.communs.exemple.web.dto.request.ExempleSearchRequestDto;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaExempleRepository extends JpaAbstractRepositoryCustom<JpaExempleModel, ExempleSearchRequestDto> {

}
