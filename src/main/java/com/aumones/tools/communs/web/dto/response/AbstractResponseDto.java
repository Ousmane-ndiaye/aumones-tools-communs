package com.aumones.tools.communs.web.dto.response;

public abstract class AbstractResponseDto {

  private String id;

  public AbstractResponseDto() {}

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }
}
