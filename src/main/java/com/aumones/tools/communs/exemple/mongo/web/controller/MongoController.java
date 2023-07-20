package com.aumones.tools.communs.exemple.mongo.web.controller;

import com.aumones.tools.communs.exemple.mongo.data.model.MongoModel;
import com.aumones.tools.communs.exemple.mongo.service.MongoService;
import com.aumones.tools.communs.exemple.mongo.web.api.MongoApi;
import com.aumones.tools.communs.exemple.mongo.web.dto.request.MongoCreateRequestDto;
import com.aumones.tools.communs.exemple.mongo.web.dto.request.MongoSearchRequestDto;
import com.aumones.tools.communs.exemple.mongo.web.dto.request.MongoUpdateRequestDto;
import com.aumones.tools.communs.exemple.mongo.web.dto.response.MongoResponseDto;
import com.aumones.tools.communs.web.controller.MongoAbstractController;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MongoController extends MongoAbstractController<MongoModel, MongoSearchRequestDto,
    MongoCreateRequestDto, MongoUpdateRequestDto, MongoResponseDto> implements MongoApi {

  public MongoController(MongoService service) {
    super(service);
  }

  @Override
  public ResponseEntity<List<MongoResponseDto>> list(MongoSearchRequestDto searchRequest) {
    return super.list(searchRequest);
  }

  @Override
  public ResponseEntity<Page<MongoResponseDto>> list(MongoSearchRequestDto searchRequest, Pageable page) {
    return super.list(searchRequest, page);
  }

  @Override
  public ResponseEntity<MongoResponseDto> single(String id) {
    return super.single(id);
  }

  @Override
  public ResponseEntity<MongoResponseDto> create(MongoCreateRequestDto createRequest) {
    return super.create(createRequest);
  }

  @Override
  public ResponseEntity<MongoResponseDto> update(String id, MongoUpdateRequestDto updateRequest) {
    return super.update(id, updateRequest);
  }

  @Override
  public MongoResponseDto toResponse(MongoModel model) {
    return new MongoResponseDto(model);
  }
}