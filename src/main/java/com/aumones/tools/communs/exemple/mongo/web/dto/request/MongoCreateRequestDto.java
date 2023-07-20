package com.aumones.tools.communs.exemple.mongo.web.dto.request;

import com.aumones.tools.communs.exemple.mongo.data.model.MongoModel;
import com.aumones.tools.communs.web.dto.request.AbstractCreateRequestDto;
import com.aumones.tools.communs.web.validator.uniqueField.UniqueField;

import java.util.Objects;

public class MongoCreateRequestDto extends AbstractCreateRequestDto<MongoModel> {

  // @UniqueField(document = MongoModel.class, field = "name", message = "name already exist")
  private String name;

  private int age;

  public MongoCreateRequestDto() {}

  public MongoCreateRequestDto(String name, int age) {
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
  public MongoModel toModel() {
    return  new MongoModel(name, age);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof MongoCreateRequestDto that)) return false;
    return getAge() == that.getAge() && getName().equals(that.getName());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getName(), getAge());
  }
}