package com.aumones.tools.communs.exemple.service.jpa;

import com.aumones.tools.communs.exemple.data.jpa.model.JpaExempleModel;
import com.aumones.tools.communs.exemple.data.jpa.repository.JpaExempleRepository;
import com.aumones.tools.communs.exemple.web.dto.request.ExempleSearchRequestDto;
import com.aumones.tools.communs.exemple.web.dto.request.JpaExempleCreateRequestDto;
import com.aumones.tools.communs.exemple.web.dto.request.JpaExempleUpdateRequestDto;
import com.aumones.tools.communs.service.impl.AbstractCRUDAndSearchServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class JpaExempleServiceImpl extends AbstractCRUDAndSearchServiceImpl<Long, JpaExempleModel, ExempleSearchRequestDto,
    JpaExempleCreateRequestDto, JpaExempleUpdateRequestDto> implements JpaExempleService {

  public JpaExempleServiceImpl(JpaExempleRepository repository) {
    super(repository);
  }
}
