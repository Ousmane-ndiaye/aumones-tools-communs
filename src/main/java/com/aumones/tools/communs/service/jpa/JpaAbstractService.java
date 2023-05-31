package com.aumones.tools.communs.service.jpa;

import com.aumones.tools.communs.data.model.jpa.JpaAbstractModel;
import com.aumones.tools.communs.data.repository.jpa.JpaAbstractRepository;
import com.aumones.tools.communs.service.AbstractService;
import com.aumones.tools.communs.web.dto.request.AbstractCreateRequestDto;
import com.aumones.tools.communs.web.dto.request.AbstractSearchRequestDto;
import com.aumones.tools.communs.web.dto.request.AbstractUpdateRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public abstract class JpaAbstractService<T extends JpaAbstractModel, S extends AbstractSearchRequestDto,
    C extends AbstractCreateRequestDto<T>, U extends AbstractUpdateRequestDto<T>> implements AbstractService<T, S, C, U> {

  protected JpaAbstractRepository<T, S> repository;

  public JpaAbstractService(JpaAbstractRepository<T, S> repository) {
    this.repository = repository;
  }

  @Override
  public List<T> list(S searchRequest) {
    return repository.search(searchRequest);
  }

  @Override
  public Page<T> list(S searchRequest, Pageable page) {
    return repository.search(searchRequest, page);
  }

  @Override
  public T get(Object id) {
    assert id instanceof Long;
    return repository.findById((Long) id).orElse(null);
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
  public T update(Object id, U updateRequestDto) {
    assert id instanceof Long;
    T currItem = repository.findById((Long) id).orElse(null);
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
