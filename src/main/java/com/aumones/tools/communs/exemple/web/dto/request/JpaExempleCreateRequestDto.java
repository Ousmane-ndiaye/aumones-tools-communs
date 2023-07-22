package com.aumones.tools.communs.exemple.web.dto.request;

import com.aumones.tools.communs.exemple.data.model.JpaExempleModel;
import com.aumones.tools.communs.web.dto.request.AbstractCreateRequestDto;

import java.util.Objects;

public class JpaExempleCreateRequestDto extends AbstractCreateRequestDto<JpaExempleModel> {

  // @UniqueField(document = MongoExempleModel.class, field = "name", message = "name already exist")
  private String name;

  private int age;

  public JpaExempleCreateRequestDto(String name, int age) {
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
  public JpaExempleModel toModel() {
    return  new JpaExempleModel(name, age);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof JpaExempleCreateRequestDto that)) return false;
    return getAge() == that.getAge() && getName().equals(that.getName());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getName(), getAge());
  }
}