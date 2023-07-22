package com.aumones.tools.communs.exemple.data.repository.customQuery;

import com.aumones.tools.communs.data.repository.mongo.MongoAbstractCustomQueryImpl;
import com.aumones.tools.communs.exemple.data.model.MongoExempleModel;
import com.aumones.tools.communs.exemple.web.dto.request.ExempleSearchRequestDto;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MongoExempleCustomQueryImpl extends MongoAbstractCustomQueryImpl<MongoExempleModel, ExempleSearchRequestDto> {

  @Override
  public List<Criteria> setListCriteria(ExempleSearchRequestDto searchRequest) {
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
  public Class<MongoExempleModel> getDocumentClass() {
    return MongoExempleModel.class;
  }
}
