package com.aumones.tools.communs.service;

import com.aumones.tools.communs.data.model.AbstractModel;
import com.aumones.tools.communs.web.dto.request.AbstractCreateRequestDto;
import com.aumones.tools.communs.web.dto.request.AbstractSearchRequestDto;
import com.aumones.tools.communs.web.dto.request.AbstractUpdateRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AbstractService<T extends AbstractModel<?>, S extends AbstractSearchRequestDto,
    C extends AbstractCreateRequestDto<T>, U extends AbstractUpdateRequestDto<T>> {

  List<T> list(S searchRequest);

  Page<T> list(S searchRequest, Pageable page);

  T get(Object id);

  T create(C createRequestDto);

  T update(Object id, U updateRequestDto);

  T beforeSaveModel(C createRequestDto, T model);

  T beforeSaveModel(U updateRequestDto, T model);

  T afterSaveModel(C createRequestDto, T model);

  T afterSaveModel(U updateRequestDto, T model);
}
