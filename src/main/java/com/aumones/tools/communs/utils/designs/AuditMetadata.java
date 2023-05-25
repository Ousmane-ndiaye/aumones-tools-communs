package com.aumones.tools.communs.utils.designs;


import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

public abstract class AuditMetadata {

  @CreatedDate
  protected LocalDateTime createdDate;

  @LastModifiedDate
  protected LocalDateTime lastModifiedDate;

  @CreatedBy
  protected String createdByUserId;

  @LastModifiedBy
  protected String modifiedByUserId;

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

  public String getCreatedByUserId() {
    return this.createdByUserId;
  }

  public void setCreatedByUserId(String createdByUserId) {
    this.createdByUserId = createdByUserId;
  }

  public String getModifiedByUserId() {
    return this.modifiedByUserId;
  }

  public void setModifiedByUserId(String modifiedByUserId) {
    this.modifiedByUserId = modifiedByUserId;
  }

}
