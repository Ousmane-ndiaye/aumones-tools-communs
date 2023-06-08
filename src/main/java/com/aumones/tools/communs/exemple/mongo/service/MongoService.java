package com.aumones.tools.communs.exemple.mongo.service;

import com.aumones.tools.communs.data.repository.mongo.MongoAbstractRepository;
import com.aumones.tools.communs.exemple.mongo.web.dto.request.MongoCreateRequestDto;
import com.aumones.tools.communs.exemple.mongo.web.dto.request.MongoSearchRequestDto;
import com.aumones.tools.communs.exemple.mongo.web.dto.request.MongoUpdateRequestDto;
import com.aumones.tools.communs.exemple.mongo.data.model.MongoModel;
import com.aumones.tools.communs.service.mongo.MongoAbstractService;
import org.springframework.stereotype.Service;

@Service
public class MongoService extends MongoAbstractService<MongoModel, MongoSearchRequestDto,
    MongoCreateRequestDto, MongoUpdateRequestDto> {

  public MongoService(MongoAbstractRepository<MongoModel, MongoSearchRequestDto> repository) {
    super(repository);
  }
}
