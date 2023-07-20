package com.aumones.tools.communs.web.dto.response;

import com.aumones.tools.communs.data.model.mongo.MongoAbstractModel;

public abstract class MongoAbstractResponseDto extends AbstractResponseDto<String> {

  public MongoAbstractResponseDto() {}

  public MongoAbstractResponseDto(MongoAbstractModel model) {
    super(model.getCreatedDate(), model.getLastModifiedDate(), model.getCreatedByUserId(),model.getModifiedByUserId());
    this.id = model.getId();
  }
}
