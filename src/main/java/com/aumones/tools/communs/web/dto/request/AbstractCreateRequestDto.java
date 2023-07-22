package com.aumones.tools.communs.web.dto.request;

import com.aumones.tools.communs.data.model.AbstractModel;

public abstract class AbstractCreateRequestDto<T extends AbstractModel<?>> {

  public abstract T toModel();
}
