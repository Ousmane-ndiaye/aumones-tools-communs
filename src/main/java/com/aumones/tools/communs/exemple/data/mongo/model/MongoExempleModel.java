package com.aumones.tools.communs.exemple.data.mongo.model;

import com.aumones.tools.communs.data.model.mongo.MongoAbstractModel;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "exemple_mongo")
public class MongoExempleModel extends MongoAbstractModel {

  private String name;

  private int age;

  public MongoExempleModel() {}

  public MongoExempleModel(String name, int age) {
    this.name = name;
    this.age = age;
  }

  public MongoExempleModel(String id, String name, int age) {
    this.id = id;
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
  public String toString() {
    return "MongoExempleModel{" +
        "id='" + id + '\'' +
        "name='" + name + '\'' +
        ", age=" + age +
        '}';
  }
}
