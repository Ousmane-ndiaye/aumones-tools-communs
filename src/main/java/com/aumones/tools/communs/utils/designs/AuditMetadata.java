package com.aumones.tools.communs.utils.designs;


import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

public abstract class AuditMetadata<ID> {

  @CreatedDate
  protected LocalDateTime createdDate;

  @LastModifiedDate
  protected LocalDateTime lastModifiedDate;

  @CreatedBy
  protected ID createdByUserId;

  @LastModifiedBy
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
