package com.aumones.tools.communs.exemple.web.dto.response;

import com.aumones.tools.communs.exemple.data.mongo.model.MongoExempleModel;
import com.aumones.tools.communs.web.dto.response.AbstractResponseDto;

public class MongoExempleResponseDto extends AbstractResponseDto<String> {

  private String name;

  private int age;

  public MongoExempleResponseDto(MongoExempleModel model) {
    super(model);
    this.name = model.getName();
    this.age = model.getAge();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }
}
