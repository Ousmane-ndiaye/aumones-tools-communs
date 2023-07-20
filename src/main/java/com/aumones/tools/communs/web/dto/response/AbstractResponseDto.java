package com.aumones.tools.communs.web.dto.response;

import java.time.LocalDateTime;

public abstract class AbstractResponseDto<ID> extends AuditMetadataResponse<ID> {

  protected ID id;

  public AbstractResponseDto() {}

  public AbstractResponseDto(LocalDateTime createdDate, LocalDateTime lastModifiedDate, ID createdByUserId, ID modifiedByUserId) {
    super(createdDate, lastModifiedDate, createdByUserId, modifiedByUserId);
  }

  public ID getId() {
    return id;
  }

  public void setId(ID id) {
    this.id = id;
  }
}
