package com.aumones.tools.communs.web.dto.response;

import java.time.LocalDateTime;

public class AuditMetadataResponse<ID> {

  protected LocalDateTime createdDate;

  protected LocalDateTime lastModifiedDate;

  protected ID createdByUserId;

  protected ID modifiedByUserId;

  public AuditMetadataResponse() {
  }

  public AuditMetadataResponse(LocalDateTime createdDate, LocalDateTime lastModifiedDate, ID createdByUserId, ID modifiedByUserId) {
    this.createdDate = createdDate;
    this.lastModifiedDate = lastModifiedDate;
    this.createdByUserId = createdByUserId;
    this.modifiedByUserId = modifiedByUserId;
  }

  public LocalDateTime getCreatedDate() {
    return createdDate;
  }

  public LocalDateTime getLastModifiedDate() {
    return lastModifiedDate;
  }

  public ID getCreatedByUserId() {
    return createdByUserId;
  }

  public ID getModifiedByUserId() {
    return modifiedByUserId;
  }
}
