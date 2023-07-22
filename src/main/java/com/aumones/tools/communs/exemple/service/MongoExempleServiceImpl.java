package com.aumones.tools.communs.exemple.service;

import com.aumones.tools.communs.exemple.data.repository.MongoExempleRepository;
import com.aumones.tools.communs.exemple.web.dto.request.MongoExempleCreateRequestDto;
import com.aumones.tools.communs.exemple.web.dto.request.ExempleSearchRequestDto;
import com.aumones.tools.communs.exemple.web.dto.request.MongoExempleUpdateRequestDto;
import com.aumones.tools.communs.exemple.data.model.MongoExempleModel;
import com.aumones.tools.communs.service.impl.AbstractCRUDAndSearchServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class MongoExempleServiceImpl extends AbstractCRUDAndSearchServiceImpl<String, MongoExempleModel, ExempleSearchRequestDto,
    MongoExempleCreateRequestDto, MongoExempleUpdateRequestDto> implements MongoExempleService {

  public MongoExempleServiceImpl(MongoExempleRepository repository) {
    super(repository);
  }
}
