package com.aumones.tools.communs.web.dto.response;

public abstract class AbstractResponseDto<ID> {

  protected ID id;

  public AbstractResponseDto() {}

  public ID getId() {
    return id;
  }

  public void setId(ID id) {
    this.id = id;
  }
}
