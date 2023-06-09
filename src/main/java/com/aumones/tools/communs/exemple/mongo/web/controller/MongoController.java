package com.aumones.tools.communs.exemple.mongo.web.controller;

import com.aumones.tools.communs.exemple.mongo.data.model.MongoModel;
import com.aumones.tools.communs.exemple.mongo.service.MongoService;
import com.aumones.tools.communs.exemple.mongo.web.api.MongoApi;
import com.aumones.tools.communs.exemple.mongo.web.dto.request.MongoCreateRequestDto;
import com.aumones.tools.communs.exemple.mongo.web.dto.request.MongoSearchRequestDto;
import com.aumones.tools.communs.exemple.mongo.web.dto.request.MongoUpdateRequestDto;
import com.aumones.tools.communs.exemple.mongo.web.dto.response.MongoResponseDto;
import com.aumones.tools.communs.web.controller.MongoAbstractController;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MongoController extends MongoAbstractController<MongoModel, MongoSearchRequestDto,
    MongoCreateRequestDto, MongoUpdateRequestDto, MongoResponseDto> implements MongoApi {

  public MongoController(MongoService service) {
    super(service);
  }

  @Override
  public MongoResponseDto toResponse(MongoModel information) {
    return new MongoResponseDto(information);
  }
}