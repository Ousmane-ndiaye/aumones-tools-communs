package com.aumones.tools.communs.data.repository.jpa;

import com.aumones.tools.communs.data.model.jpa.JpaAbstractModel;
import com.aumones.tools.communs.data.repository.AbstractRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaAbstractRepository<T extends JpaAbstractModel> extends AbstractRepository<Long, T>, JpaRepository<T, Long> {

}
