package com.aumones.tools.communs.service.impl;

import com.aumones.tools.communs.data.model.AbstractModel;
import com.aumones.tools.communs.data.repository.AbstractRepositoryCustom;
import com.aumones.tools.communs.service.AbstractCRUDAndSearchService;
import com.aumones.tools.communs.web.dto.request.AbstractCreateRequestDto;
import com.aumones.tools.communs.web.dto.request.AbstractSearchRequestDto;
import com.aumones.tools.communs.web.dto.request.AbstractUpdateRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class AbstractCRUDAndSearchServiceImpl<ID, T extends AbstractModel<ID>, S extends AbstractSearchRequestDto,
    C extends AbstractCreateRequestDto<T>, U extends AbstractUpdateRequestDto<T>> extends AbstractCRUDServiceImpl<ID, T, C, U>
    implements AbstractCRUDAndSearchService<ID, T, S, C, U> {

  protected AbstractRepositoryCustom<ID, T, S> repository;

  public AbstractCRUDAndSearchServiceImpl(AbstractRepositoryCustom<ID, T, S> repository) {
    super(repository);
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
}
