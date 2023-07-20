package com.aumones.tools.communs.data.model.mongo;

import com.aumones.tools.communs.data.model.AbstractModel;
import com.aumones.tools.communs.utils.designs.AuditMetadata;
import org.springframework.data.annotation.Id;

public class MongoAbstractModel extends AuditMetadata<String> implements AbstractModel<String> {

  @Id
  protected String id;

  public MongoAbstractModel() {}

  @Override
  public String getId() {
    return id;
  }

  @Override
  public void setId(String id) {
    this.id = id;
  }
}
