package com.aumones.tools.communs.exemple.mongo.web.dto.request;

import com.aumones.tools.communs.exemple.mongo.data.model.MongoModel;
import com.aumones.tools.communs.web.dto.request.AbstractUpdateRequestDto;

public class MongoUpdateRequestDto extends AbstractUpdateRequestDto<MongoModel> {

  private String name;

  private int age;

  public MongoUpdateRequestDto() {
  }

  public MongoUpdateRequestDto(String name, int age) {
    this.name = name;
    this.age = age;
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

  @Override
  public MongoModel toModel(MongoModel model) {
    model.setName(name);
    model.setAge(age);
    return model;
  }
}
