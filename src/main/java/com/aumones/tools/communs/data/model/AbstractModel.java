package com.aumones.tools.communs.data.model;

import com.aumones.tools.communs.utils.designs.AuditMetadata;

public abstract class AbstractModel<ID> extends AuditMetadata<ID> {

  public abstract ID getId();

  public abstract void setId(ID id);
}
