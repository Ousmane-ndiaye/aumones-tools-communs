package com.aumones.tools.communs.exemple.mongo.web.dto.request;

import com.aumones.tools.communs.web.dto.request.AbstractSearchRequestDto;

public class MongoSearchRequestDto extends AbstractSearchRequestDto {
  private String name;

  private int age;

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