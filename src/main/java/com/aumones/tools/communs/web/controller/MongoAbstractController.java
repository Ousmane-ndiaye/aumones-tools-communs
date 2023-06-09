package com.aumones.tools.communs.web.controller;

import com.aumones.tools.communs.data.model.mongo.MongoAbstractModel;
import com.aumones.tools.communs.service.AbstractService;
import com.aumones.tools.communs.web.dto.request.AbstractCreateRequestDto;
import com.aumones.tools.communs.web.dto.request.AbstractSearchRequestDto;
import com.aumones.tools.communs.web.dto.request.AbstractUpdateRequestDto;
import com.aumones.tools.communs.web.dto.response.AbstractResponseDto;
import com.aumones.tools.communs.web.exception.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.stream.Collectors;

public abstract class MongoAbstractController<T extends MongoAbstractModel, S extends AbstractSearchRequestDto,
    C extends AbstractCreateRequestDto<T>, U extends AbstractUpdateRequestDto<T>, R extends AbstractResponseDto<String>> {

  protected AbstractService<T, S, C, U> service;

  public MongoAbstractController(AbstractService<T, S, C, U> service) {
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

  public ResponseEntity<R> single(String id) {
    T item = service.get(id);
    if (item == null)
      throw new EntityNotFoundException("item.notFound");

    return ResponseEntity.status(HttpStatus.OK).body(toResponse(item));
  }

  public ResponseEntity<R> create(C createRequest) {
    createRequest = beforeCreate(createRequest);

    T item = service.create(createRequest);

    afterCreate(createRequest, item);

    return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(item));
  }

  public ResponseEntity<R> update(String id, U updateRequest) {
    updateRequest = beforeUpdate(id, updateRequest);

    T item = service.update(id, updateRequest);

    afterUpdate(updateRequest, item);

    return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(item));
  }

  public C beforeCreate(C createRequestDto) {
    return createRequestDto;
  }

  public void afterCreate(C createRequestDto,T item) { }

  public U beforeUpdate(String id, U updateRequestDto) {
    return updateRequestDto;
  }

  public void afterUpdate(U updateRequestDto, T item) { }

  public abstract R toResponse(T model);
}
