package com.aumones.tools.communs.web.dto.response;

import com.aumones.tools.communs.data.model.jpa.JpaAbstractModel;

public abstract class JpaAbstractResponseDto extends AbstractResponseDto<Long> {

  public JpaAbstractResponseDto() {}

  public JpaAbstractResponseDto(JpaAbstractModel model) {
    super(model.getCreatedDate(), model.getLastModifiedDate(), model.getCreatedByUserId(),model.getModifiedByUserId());
    this.id = model.getId();
  }

}
