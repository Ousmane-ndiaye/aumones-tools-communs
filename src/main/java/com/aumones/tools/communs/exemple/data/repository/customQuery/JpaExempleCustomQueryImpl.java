package com.aumones.tools.communs.exemple.data.repository.customQuery;

import com.aumones.tools.communs.data.repository.jpa.JpaAbstractCustomQueryImpl;
import com.aumones.tools.communs.exemple.data.model.JpaExempleModel;
import com.aumones.tools.communs.exemple.web.dto.request.ExempleSearchRequestDto;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class JpaExempleCustomQueryImpl extends JpaAbstractCustomQueryImpl<JpaExempleModel, ExempleSearchRequestDto> {

  @Override
  public List<Predicate> setListPredicates(ExempleSearchRequestDto searchRequest, CriteriaBuilder criteriaBuilder, Root<JpaExempleModel> root) {
    List<Predicate> listCriteria = new ArrayList<>();
    if (searchRequest.getName() != null && !searchRequest.getName().isEmpty()) {
      listCriteria.add(criteriaBuilder.equal(root.get("name"), searchRequest.getName()));
    }
    if (searchRequest.getAge() != null) {
      listCriteria.add(criteriaBuilder.equal(root.get("age"), searchRequest.getAge()));
    }
    return listCriteria;
  }

  @Override
  public Class<JpaExempleModel> getEntityClass() {
    return JpaExempleModel.class;
  }
}
