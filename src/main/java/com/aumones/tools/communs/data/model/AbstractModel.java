package com.aumones.tools.communs.data.model;

import com.aumones.tools.communs.utils.designs.AuditMetadata;
import org.springframework.data.annotation.Id;

public abstract class AbstractModel extends AuditMetadata {

  @Id
  protected String id;

  public AbstractModel() {}

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }
}
