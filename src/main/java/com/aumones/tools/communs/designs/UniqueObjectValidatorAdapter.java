package com.aumones.tools.communs.designs;

import org.springframework.data.mongodb.core.query.Criteria;

import java.util.List;

public interface UniqueObjectValidatorAdapter {

  List<Criteria> buildUniqueObjectValidatorCriteria();

}
