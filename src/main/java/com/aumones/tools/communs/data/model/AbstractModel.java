package com.aumones.tools.communs.data.model;

import com.aumones.tools.communs.utils.designs.AuditMetadata;

public class AbstractModel<ID> extends AuditMetadata<ID> {

  protected ID id;

  public ID getId() {
    return id;
  }

  public void setId(ID id) {
    this.id = id;
  }
}
