package com.aumones.tools.communs.web.dto.request;

import com.aumones.tools.communs.data.model.AbstractModel;

public abstract class AbstractUpdateRequestDto<T extends AbstractModel> {

  public abstract T toModel(T currItem);
}
