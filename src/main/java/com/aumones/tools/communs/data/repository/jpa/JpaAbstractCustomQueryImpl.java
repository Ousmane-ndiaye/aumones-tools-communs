package com.aumones.tools.communs.data.repository.jpa;

import com.aumones.tools.communs.data.model.jpa.JpaAbstractModel;
import com.aumones.tools.communs.data.repository.AbstractCustomQuery;
import com.aumones.tools.communs.web.dto.request.AbstractSearchRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.List;

public abstract class JpaAbstractCustomQueryImpl<T extends JpaAbstractModel, S extends AbstractSearchRequestDto>
    implements AbstractCustomQuery<Long, T, S> {

  @PersistenceContext
  protected EntityManager entityManager;

  protected Sort.Direction sortDirection = Sort.Direction.DESC;

  protected String sortField = "createdDate";

  @Override
  public List<T> search(S searchRequest) {
    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    CriteriaQuery<T> query = criteriaBuilder.createQuery(getEntityClass());
    Root<T> root = query.from(getEntityClass());

    List<Predicate> predicates = setListPredicates(searchRequest, criteriaBuilder, root);
    if (!predicates.isEmpty()) {
      query.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));
    }

    query.orderBy(sortDirection.equals(Sort.Direction.DESC) ?
            criteriaBuilder.desc(root.get(sortField)) : criteriaBuilder.asc(root.get(sortField)));

    TypedQuery<T> typedQuery = entityManager.createQuery(query);
    return typedQuery.getResultList();
  }

  @Override
  public Page<T> search(S searchRequest, Pageable page) {
    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    CriteriaQuery<T> query = criteriaBuilder.createQuery(getEntityClass());
    Root<T> root = query.from(getEntityClass());

    List<Predicate> predicates = setListPredicates(searchRequest, criteriaBuilder, root);
    if (!predicates.isEmpty()) {
      query.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));
    }

    query.orderBy(criteriaBuilder.desc(root.get(sortField)));

    TypedQuery<T> typedQuery = entityManager.createQuery(query);
    typedQuery.setFirstResult((int) page.getOffset());
    typedQuery.setMaxResults(page.getPageSize());

    List<T> resultList = typedQuery.getResultList();

    CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
    countQuery.select(criteriaBuilder.count(countQuery.from(getEntityClass())));
    countQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));

    Long totalElements = entityManager.createQuery(countQuery).getSingleResult();

    return new PageImpl<>(resultList, page, totalElements);
  }

  public abstract List<Predicate> setListPredicates(S searchRequest, CriteriaBuilder criteriaBuilder, Root<T> root);

  public abstract Class<T> getEntityClass();
}
