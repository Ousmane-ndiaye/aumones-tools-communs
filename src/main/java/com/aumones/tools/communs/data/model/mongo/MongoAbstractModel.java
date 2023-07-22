package com.aumones.tools.communs.data.model.mongo;

import com.aumones.tools.communs.data.model.AbstractModel;
import org.springframework.data.annotation.Id;

public class MongoAbstractModel extends AbstractModel<String> {

  @Override
  @Id
  public String getId() {
    return super.getId();
  }

  @Override
  public void setId(String id) {
    super.setId(id);
  }
}
