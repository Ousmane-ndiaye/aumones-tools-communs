package com.aumones.tools.communs.web.dto.response;

import com.aumones.tools.communs.data.model.AbstractModel;

public abstract class AbstractResponseDto<ID> extends AuditMetadataResponse<ID> {

  protected ID id;

  public AbstractResponseDto(AbstractModel<ID> model) {
    super(model.getCreatedDate(), model.getLastModifiedDate(), model.getCreatedByUserId(), model.getModifiedByUserId());
    this.id = model.getId();
  }

  public ID getId() {
    return id;
  }

  public void setId(ID id) {
    this.id = id;
  }
}
