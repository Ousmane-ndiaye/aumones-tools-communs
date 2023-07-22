package com.aumones.tools.communs.web.controller;

import com.aumones.tools.communs.data.model.AbstractModel;
import com.aumones.tools.communs.service.AbstractCRUDAndSearchService;
import com.aumones.tools.communs.web.dto.request.AbstractCreateRequestDto;
import com.aumones.tools.communs.web.dto.request.AbstractSearchRequestDto;
import com.aumones.tools.communs.web.dto.request.AbstractUpdateRequestDto;
import com.aumones.tools.communs.web.dto.response.AbstractResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractCRUDAndSearchController<ID, T extends AbstractModel<ID>, S extends AbstractSearchRequestDto,
    C extends AbstractCreateRequestDto<T>, U extends AbstractUpdateRequestDto<T>, R extends AbstractResponseDto<ID>>
    extends AbstractCRUDController<ID, T, C, U, R> {

  protected AbstractCRUDAndSearchService<ID, T, S, C, U> service;

  public AbstractCRUDAndSearchController(AbstractCRUDAndSearchService<ID, T, S, C, U> service) {
    super(service);
    this.service = service;
  }

  public ResponseEntity<List<R>> list(S searchRequest) {
    List<R> items = service.list(searchRequest).stream().map(this::toResponse).collect(Collectors.toList());
    return ResponseEntity.status(HttpStatus.OK).body(items);
  }

  public ResponseEntity<Page<R>> list(S searchRequest, Pageable page) {
    Page<R> items = service.list(searchRequest, page).map(this::toResponse);
    return ResponseEntity.status(HttpStatus.OK).body(items);
  }

}
