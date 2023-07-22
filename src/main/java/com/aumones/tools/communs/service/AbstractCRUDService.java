package com.aumones.tools.communs.service;

import com.aumones.tools.communs.data.model.AbstractModel;
import com.aumones.tools.communs.web.dto.request.AbstractCreateRequestDto;
import com.aumones.tools.communs.web.dto.request.AbstractUpdateRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AbstractCRUDService<ID, T extends AbstractModel<ID>, C extends AbstractCreateRequestDto<T>,
    U extends AbstractUpdateRequestDto<T>> {

  List<T> list();

  Page<T> list(Pageable page);

  T get(ID id);

  T create(C createRequestDto);

  T update(ID id, U updateRequestDto);

  T beforeSaveModel(C createRequestDto, T model);

  T beforeSaveModel(U updateRequestDto, T model);

  T afterSaveModel(C createRequestDto, T model);

  T afterSaveModel(U updateRequestDto, T model);
}
