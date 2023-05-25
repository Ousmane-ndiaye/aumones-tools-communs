package com.aumones.tools.communs.service;

import com.aumones.tools.communs.data.model.AbstractModel;
import com.aumones.tools.communs.data.repository.AbstractRepository;
import com.aumones.tools.communs.web.dto.request.AbstractCreateRequestDto;
import com.aumones.tools.communs.web.dto.request.AbstractSearchRequestDto;
import com.aumones.tools.communs.web.dto.request.AbstractUpdateRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public abstract class AbstractService<T extends AbstractModel, S extends AbstractSearchRequestDto,
    C extends AbstractCreateRequestDto<T>, U extends AbstractUpdateRequestDto<T>> {

  protected AbstractRepository<T, S> repository;

  public AbstractService(AbstractRepository<T, S> repository) {
    this.repository = repository;
  }

  public List<T> list(S searchRequest) {
    return repository.search(searchRequest);
  }

  public Page<T> list(S searchRequest, Pageable page) {
    return repository.search(searchRequest, page);
  }

  public T get(String id) {
    return repository.findById(id).orElse(null);
  }

  public T create(C createRequestDto) {
    T newItem = createRequestDto.toModel();
    newItem = beforeSaveModel(createRequestDto, newItem);
    repository.save(newItem);
    newItem = afterSaveModel(createRequestDto, newItem);
    return newItem;
  }

  public T update(String id, U updateRequestDto) {
    T currItem = repository.findById(id).orElse(null);
    assert currItem != null;
    currItem = updateRequestDto.toModel(currItem);
    currItem = beforeSaveModel(updateRequestDto, currItem);
    repository.save(currItem);
    currItem = afterSaveModel(updateRequestDto, currItem);
    return currItem;
  }

  public T beforeSaveModel(C createRequestDto, T model) {
    return model;
  }

  public T beforeSaveModel(U updateRequestDto, T model) {
    return model;
  }

  public T afterSaveModel(C createRequestDto, T model) {
    return model;
  }

  public T afterSaveModel(U updateRequestDto, T model) {
    return model;
  }
}
