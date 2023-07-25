package com.aumones.tools.communs.exemple.web.dto.request;

import com.aumones.tools.communs.exemple.data.jpa.model.JpaExempleModel;
import com.aumones.tools.communs.web.dto.request.AbstractUpdateRequestDto;

public class JpaExempleUpdateRequestDto extends AbstractUpdateRequestDto<JpaExempleModel> {

  private String name;

  private int age;

  public JpaExempleUpdateRequestDto(String name, int age) {
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
  public JpaExempleModel toModel(JpaExempleModel model) {
    model.setName(name);
    model.setAge(age);
    return model;
  }
}
