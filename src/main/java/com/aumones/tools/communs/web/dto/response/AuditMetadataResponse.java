package com.aumones.tools.communs.web.dto.response;

import java.time.LocalDateTime;

public class AuditMetadataResponse<ID> {

  protected LocalDateTime createdDate;

  protected LocalDateTime lastModifiedDate;

  protected ID createdByUserId;

  protected ID modifiedByUserId;

  public LocalDateTime getCreatedDate() {
    return this.createdDate;
  }

  public void setCreatedDate(LocalDateTime createdDate) {
    this.createdDate = createdDate;
  }

  public LocalDateTime getLastModifiedDate() {
    return this.lastModifiedDate;
  }

  public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
    this.lastModifiedDate = lastModifiedDate;
  }

  public ID getCreatedByUserId() {
    return this.createdByUserId;
  }

  public void setCreatedByUserId(ID createdByUserId) {
    this.createdByUserId = createdByUserId;
  }

  public ID getModifiedByUserId() {
    return this.modifiedByUserId;
  }

  public void setModifiedByUserId(ID modifiedByUserId) {
    this.modifiedByUserId = modifiedByUserId;
  }
}
