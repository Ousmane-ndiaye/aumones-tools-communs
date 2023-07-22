package com.aumones.tools.communs.exemple.web.dto.request;

import com.aumones.tools.communs.web.dto.request.AbstractSearchRequestDto;

import java.util.Objects;

public class ExempleSearchRequestDto extends AbstractSearchRequestDto {
  private String name;

  private Integer age;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof ExempleSearchRequestDto that)) return false;
    return Objects.equals(getName(), that.getName()) && Objects.equals(getAge(), that.getAge());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getName(), getAge());
  }
}