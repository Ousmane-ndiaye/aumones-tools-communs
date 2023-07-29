package com.aumones.tools.communs.tests.service;

import com.aumones.tools.communs.data.model.AbstractModel;
import com.aumones.tools.communs.data.repository.AbstractRepositoryCustom;
import com.aumones.tools.communs.service.AbstractCRUDAndSearchService;
import com.aumones.tools.communs.web.dto.request.AbstractCreateRequestDto;
import com.aumones.tools.communs.web.dto.request.AbstractSearchRequestDto;
import com.aumones.tools.communs.web.dto.request.AbstractUpdateRequestDto;
import org.junit.jupiter.api.Assertions;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public abstract class AbstractCRUDAndSearchServiceUnitTest<ID, T extends AbstractModel<ID>, S extends AbstractSearchRequestDto,
    C extends AbstractCreateRequestDto<T>, U extends AbstractUpdateRequestDto<T>> extends AbstractCRUDServiceUnitTest<ID, T, C, U> {

  public abstract AbstractRepositoryCustom<ID, T, S> getRepository();

  public abstract AbstractCRUDAndSearchService<ID, T, S, C, U> getService();

  public void testListWithSearch(S searchRequest) {
    // Étape 1 : Préparation des données de test
    Mockito.when(getRepository().search(ArgumentMatchers.eq(searchRequest))).thenReturn(getModels());

    // Étape 2 : Exécution de la méthode à tester
    List<T> modelList = getService().list(searchRequest);

    // Étape 3 : Vérification des résultats
    Assertions.assertEquals(modelList.size(), getModels().size());
    for (int i = 0; i < getModels().size(); i++) {
      assertModelsEquals(modelList.get(i), getModels().get(i));
    }
  }

  public void testListWithSearchAndPageable(S searchRequest, int pageSize, int currentPage) {
    // Étape 1 : Préparation des données de test
    Pageable pageable = PageRequest.of(currentPage, pageSize);
    Page<T> page = new PageImpl<>(getModels(), pageable, getModels().size());
    Mockito.when(getRepository().search(ArgumentMatchers.eq(searchRequest), ArgumentMatchers.eq(pageable))).thenReturn(page);

    // Étape 2 : Exécution de la méthode à tester
    Page<T> modelPage = getService().list(searchRequest, pageable);

    // Étape 3 : Vérification des résultats
    Assertions.assertEquals(getModels().size(), modelPage.getTotalElements());
    Assertions.assertEquals(pageSize, modelPage.getSize());

    List<T> modelList = modelPage.getContent();
    Assertions.assertEquals(getModels().size(), modelList.size());
    for (int i = 0; i < modelList.size(); i++) {
      assertModelsEquals(getModels().get(i), modelList.get(i));
    }
  }
}
