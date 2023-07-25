package com.aumones.tools.communs.data.model.mongo;

import com.aumones.tools.communs.data.model.AbstractModel;
import org.springframework.data.annotation.Id;

public class MongoAbstractModel extends AbstractModel<String> {

  @Id
  protected String id;

  public MongoAbstractModel() {}

  @Override
  public String getId() {
    return this.id;
  }

  @Override
  public void setId(String id) {
    this.id = id;
  }
}
