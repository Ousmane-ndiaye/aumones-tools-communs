package com.aumones.tools.communs.data.model.jpa;

import com.aumones.tools.communs.data.model.AbstractModel;
import jakarta.persistence.*;

@MappedSuperclass
public class JpaAbstractModel extends AbstractModel<Long> {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  protected Long id;

  public JpaAbstractModel() {}

  @Override
  public Long getId() {
    return this.id;
  }

  @Override
  public void setId(Long id) {
    this.id = id;
  }
}
