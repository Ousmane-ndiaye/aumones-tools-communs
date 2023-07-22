package com.aumones.tools.communs.exemple.web.controller;

import com.aumones.tools.communs.exemple.data.model.MongoExempleModel;
import com.aumones.tools.communs.exemple.service.MongoExempleService;
import com.aumones.tools.communs.exemple.web.api.MongoExempleApi;
import com.aumones.tools.communs.exemple.web.dto.request.MongoExempleCreateRequestDto;
import com.aumones.tools.communs.exemple.web.dto.request.ExempleSearchRequestDto;
import com.aumones.tools.communs.exemple.web.dto.request.MongoExempleUpdateRequestDto;
import com.aumones.tools.communs.exemple.web.dto.response.MongoExempleResponseDto;
import com.aumones.tools.communs.web.controller.AbstractCRUDAndSearchController;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MongoExempleController extends AbstractCRUDAndSearchController<String, MongoExempleModel, ExempleSearchRequestDto,
    MongoExempleCreateRequestDto, MongoExempleUpdateRequestDto, MongoExempleResponseDto> implements MongoExempleApi {

  public MongoExempleController(MongoExempleService service) {
    super(service);
  }

  @Override
  public ResponseEntity<List<MongoExempleResponseDto>> list(ExempleSearchRequestDto searchRequest) {
    return super.list(searchRequest);
  }

  @Override
  public ResponseEntity<Page<MongoExempleResponseDto>> list(ExempleSearchRequestDto searchRequest, Pageable page) {
    return super.list(searchRequest, page);
  }

  @Override
  public ResponseEntity<MongoExempleResponseDto> single(String id) {
    return super.single(id);
  }

  @Override
  public ResponseEntity<MongoExempleResponseDto> create(MongoExempleCreateRequestDto createRequest) {
    return super.create(createRequest);
  }

  @Override
  public ResponseEntity<MongoExempleResponseDto> update(String id, MongoExempleUpdateRequestDto updateRequest) {
    return super.update(id, updateRequest);
  }

  @Override
  public MongoExempleResponseDto toResponse(MongoExempleModel model) {
    return new MongoExempleResponseDto(model);
  }
}