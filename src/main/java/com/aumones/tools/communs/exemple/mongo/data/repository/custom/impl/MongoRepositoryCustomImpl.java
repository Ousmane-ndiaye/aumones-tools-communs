package com.aumones.tools.communs.exemple.mongo.data.repository.custom.impl;

import com.aumones.tools.communs.data.repository.mongo.MongoAbstractRepositoryCustomImpl;
import com.aumones.tools.communs.exemple.mongo.data.repository.custom.MongoRepositoryCustom;
import com.aumones.tools.communs.exemple.mongo.web.dto.request.MongoSearchRequestDto;
import com.aumones.tools.communs.exemple.mongo.data.model.MongoModel;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MongoRepositoryCustomImpl extends MongoAbstractRepositoryCustomImpl<MongoModel, MongoSearchRequestDto>
    implements MongoRepositoryCustom {

  @Override
  public List<Criteria> setListCriteria(MongoSearchRequestDto searchRequest) {
    List<Criteria> listCriteria = new ArrayList<>();
    if (searchRequest == null) {
      return listCriteria;
    }
    if (searchRequest.getName() != null && !searchRequest.getName().isEmpty()) {
      listCriteria.add(Criteria.where("name").is(searchRequest.getName()));
    }
    if (searchRequest.getAge() != null) {
      listCriteria.add(Criteria.where("age").is(searchRequest.getAge()));
    }
    return listCriteria;
  }

  @Override
  public Class<MongoModel> getDocumentClass() {
    return MongoModel.class;
  }
}
