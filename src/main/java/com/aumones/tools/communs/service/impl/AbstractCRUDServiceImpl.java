package com.aumones.tools.communs.service.impl;

import com.aumones.tools.communs.data.model.AbstractModel;
import com.aumones.tools.communs.data.repository.AbstractRepository;
import com.aumones.tools.communs.service.AbstractCRUDService;
import com.aumones.tools.communs.web.dto.request.AbstractCreateRequestDto;
import com.aumones.tools.communs.web.dto.request.AbstractUpdateRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class AbstractCRUDServiceImpl<ID, T extends AbstractModel<ID>, C extends AbstractCreateRequestDto<T>,
    U extends AbstractUpdateRequestDto<T>> implements AbstractCRUDService<ID, T, C, U> {

  protected AbstractRepository<ID, T> repository;

  public AbstractCRUDServiceImpl(AbstractRepository<ID, T> repository) {
    this.repository = repository;
  }

  @Override
  public List<T> list() {
    return repository.findAll();
  }

  @Override
  public Page<T> list(Pageable page) {
    return repository.findAll(page);
  }

  @Override
  public T get(ID id) {
    return repository.findById(id).orElse(null);
  }

  @Override
  public T create(C createRequestDto) {
    T newItem = createRequestDto.toModel();
    newItem = beforeSaveModel(createRequestDto, newItem);
    repository.save(newItem);
    newItem = afterSaveModel(createRequestDto, newItem);
    return newItem;
  }

  @Override
  public T update(ID id, U updateRequestDto) {
    T currItem = repository.findById(id).orElse(null);
    assert currItem != null;
    currItem = updateRequestDto.toModel(currItem);
    currItem = beforeSaveModel(updateRequestDto, currItem);
    repository.save(currItem);
    currItem = afterSaveModel(updateRequestDto, currItem);
    return currItem;
  }

  @Override
  public T beforeSaveModel(C createRequestDto, T model) {
    return model;
  }

  @Override
  public T beforeSaveModel(U updateRequestDto, T model) {
    return model;
  }

  @Override
  public T afterSaveModel(C createRequestDto, T model) {
    return model;
  }

  @Override
  public T afterSaveModel(U updateRequestDto, T model) {
    return model;
  }
}
