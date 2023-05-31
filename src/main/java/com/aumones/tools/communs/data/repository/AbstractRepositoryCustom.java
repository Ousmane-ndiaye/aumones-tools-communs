package com.aumones.tools.communs.data.repository;

import com.aumones.tools.communs.data.model.AbstractModel;
import com.aumones.tools.communs.web.dto.request.AbstractSearchRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AbstractRepositoryCustom<T extends AbstractModel, S extends AbstractSearchRequestDto> {

  List<T> search(S s);

  Page<T> search(S s, Pageable page);
}
