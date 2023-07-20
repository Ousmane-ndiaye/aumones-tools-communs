package com.aumones.tools.communs.exemple.mongo.web.dto.response;

import com.aumones.tools.communs.exemple.mongo.data.model.MongoModel;
import com.aumones.tools.communs.web.dto.response.MongoAbstractResponseDto;

public class MongoResponseDto extends MongoAbstractResponseDto {

  private String name;

  private int age;

  public MongoResponseDto() {}

  public MongoResponseDto(MongoModel model) {
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
