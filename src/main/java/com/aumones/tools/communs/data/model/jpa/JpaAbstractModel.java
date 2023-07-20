package com.aumones.tools.communs.data.model.jpa;

import com.aumones.tools.communs.data.model.AbstractModel;
import com.aumones.tools.communs.utils.designs.AuditMetadata;
import org.springframework.data.annotation.Id;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.MappedSuperclass;

@Entity
@MappedSuperclass
public class JpaAbstractModel extends AuditMetadata<Long> implements AbstractModel<Long> {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  protected Long id;

  public JpaAbstractModel() {}

  @Override
  public Long getId() {
    return id;
  }

  @Override
  public void setId(Long id) {
    this.id = id;
  }
}
