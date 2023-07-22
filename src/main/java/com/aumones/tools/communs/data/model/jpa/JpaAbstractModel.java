package com.aumones.tools.communs.data.model.jpa;

import com.aumones.tools.communs.data.model.AbstractModel;
import org.springframework.data.annotation.Id;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.MappedSuperclass;

@Entity
@MappedSuperclass
public class JpaAbstractModel extends AbstractModel<Long> {

  @Override
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  public Long getId() {
    return super.getId();
  }

  @Override
  public void setId(Long id) {
    super.setId(id);
  }
}
