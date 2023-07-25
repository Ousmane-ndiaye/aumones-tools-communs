package com.aumones.tools.communs.exemple.web.dto.request;

import com.aumones.tools.communs.exemple.data.mongo.model.MongoExempleModel;
import com.aumones.tools.communs.web.dto.request.AbstractCreateRequestDto;

import java.util.Objects;

public class MongoExempleCreateRequestDto extends AbstractCreateRequestDto<MongoExempleModel> {

  // @UniqueField(document = MongoExempleModel.class, field = "name", message = "name already exist")
  private String name;

  private int age;

  public MongoExempleCreateRequestDto(String name, int age) {
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
  public MongoExempleModel toModel() {
    return  new MongoExempleModel(name, age);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof MongoExempleCreateRequestDto that)) return false;
    return getAge() == that.getAge() && getName().equals(that.getName());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getName(), getAge());
  }
}