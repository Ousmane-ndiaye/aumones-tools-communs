package com.aumones.tools.communs.exemple.web.controller;

import com.aumones.tools.communs.exemple.data.jpa.model.JpaExempleModel;
import com.aumones.tools.communs.exemple.service.jpa.JpaExempleService;
import com.aumones.tools.communs.exemple.web.api.JpaExempleApi;
import com.aumones.tools.communs.exemple.web.dto.request.ExempleSearchRequestDto;
import com.aumones.tools.communs.exemple.web.dto.request.JpaExempleCreateRequestDto;
import com.aumones.tools.communs.exemple.web.dto.request.JpaExempleUpdateRequestDto;
import com.aumones.tools.communs.exemple.web.dto.response.JpaExempleResponseDto;
import com.aumones.tools.communs.web.controller.AbstractCRUDAndSearchController;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class JpaExempleController extends AbstractCRUDAndSearchController<Long, JpaExempleModel, ExempleSearchRequestDto,
    JpaExempleCreateRequestDto, JpaExempleUpdateRequestDto, JpaExempleResponseDto> implements JpaExempleApi {

  public JpaExempleController(JpaExempleService service) {
    super(service);
  }

  @Override
  public ResponseEntity<List<JpaExempleResponseDto>> list(ExempleSearchRequestDto searchRequest) {
    return super.list(searchRequest);
  }

  @Override
  public ResponseEntity<Page<JpaExempleResponseDto>> list(ExempleSearchRequestDto searchRequest, Pageable page) {
    return super.list(searchRequest, page);
  }

  @Override
  public ResponseEntity<JpaExempleResponseDto> single(Long id) {
    return super.single(id);
  }

  @Override
  public ResponseEntity<JpaExempleResponseDto> create(JpaExempleCreateRequestDto createRequest) {
    return super.create(createRequest);
  }

  @Override
  public ResponseEntity<JpaExempleResponseDto> update(Long id, JpaExempleUpdateRequestDto updateRequest) {
    return super.update(id, updateRequest);
  }

  @Override
  public JpaExempleResponseDto toResponse(JpaExempleModel model) {
    return new JpaExempleResponseDto(model);
  }
}