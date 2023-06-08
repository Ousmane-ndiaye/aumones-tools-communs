package com.aumones.tools.communs.exemple.mongo.data.model;

import com.aumones.tools.communs.data.model.mongo.MongoAbstractModel;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "mongos")
public class MongoModel extends MongoAbstractModel {

  private String name;

  private int age;

  public MongoModel() {}

  public MongoModel(String name, int age) {
    this.name = name;
    this.age = age;
  }

  public MongoModel(String id, String name, int age) {
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
}
