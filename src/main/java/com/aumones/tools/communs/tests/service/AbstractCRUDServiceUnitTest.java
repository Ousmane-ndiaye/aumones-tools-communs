package com.aumones.tools.communs.tests.service;

import com.aumones.tools.communs.data.model.AbstractModel;
import com.aumones.tools.communs.data.repository.AbstractRepository;
import com.aumones.tools.communs.service.AbstractCRUDService;
import com.aumones.tools.communs.web.dto.request.AbstractCreateRequestDto;
import com.aumones.tools.communs.web.dto.request.AbstractUpdateRequestDto;
import org.junit.jupiter.api.Assertions;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public abstract class AbstractCRUDServiceUnitTest<ID, T extends AbstractModel<ID>, C extends AbstractCreateRequestDto<T>,
    U extends AbstractUpdateRequestDto<T>> {

  public abstract AbstractRepository<ID, T> getRepository();

  public abstract AbstractCRUDService<ID, T, C, U> getService();

  public abstract List<T> getModels();

  public abstract Class<T> getClassName();

  public abstract void assertModelsEquals(T expected, T actual);

  public void testList() {
    // Étape 1 : Préparation des données de test
    Mockito.when(getRepository().findAll()).thenReturn(getModels());

    // Étape 2 : Exécution de la méthode à tester
    List<T> modelList = getService().list();

    // Étape 3 : Vérification des résultats
    Assertions.assertEquals(modelList.size(), getModels().size());
    for (int i = 0; i < getModels().size(); i++) {
      assertModelsEquals(modelList.get(i), getModels().get(i));
    }
  }

  public void testListWithPageable(int pageSize, int currentPage) {
    // Étape 1 : Préparation des données de test
    Pageable pageable = PageRequest.of(currentPage, pageSize);
    Page<T> page = new PageImpl<>(getModels(), pageable, getModels().size());
    Mockito.when(getRepository().findAll(pageable)).thenReturn(page);

    // Étape 2 : Exécution de la méthode à tester
    Page<T> modelPage = getService().list(pageable);

    // Étape 3 : Vérification des résultats
    Assertions.assertEquals(getModels().size(), modelPage.getTotalElements());
    Assertions.assertEquals(pageSize, modelPage.getSize());

    List<T> modelList = modelPage.getContent();
    Assertions.assertEquals(getModels().size(), modelList.size());
    for (int i = 0; i < modelList.size(); i++) {
      assertModelsEquals(getModels().get(i), modelList.get(i));
    }
  }

  public void testGet(ID id) {
    // Étape 1 : Préparation des données de test
    T currentModel = getModels().stream().filter(_model -> _model.getId().equals(id)).findFirst().orElse(null);
    Assertions.assertNotNull(currentModel, "ITEM WITH THIS ID NOT FOUND IN LIST");
    Mockito.when(getRepository().findById(id)).thenReturn(Optional.of(currentModel));

    // Étape 2 : Exécution de la méthode à tester
    T model = getService().get(id);

    // Étape 3 : Vérification des résultats
    Assertions.assertNotEquals(model, null);
    assertModelsEquals(model, currentModel);
  }

  public void testGetNull() {
    // Étape 1 : Préparation des données de test
    Mockito.when(getRepository().findById(getModels().get(0).getId())).thenReturn(Optional.empty());

    // Étape 2 : Exécution de la méthode à tester
    T model = getService().get(getModels().get(0).getId());

    // Étape 3 : Vérification des résultats
    Assertions.assertNull(model);
  }

  public void testCreate(C createRequest, T expectedItem) {
    // Étape 1 : Préparation des données de test
    Mockito.when(getRepository().save(ArgumentMatchers.any(getClassName()))).thenAnswer(invocation -> {
      T model = invocation.getArgument(0);
      model.setId(getModels().get(0).getId());
      return model;
    });

    // Étape 2 : Exécution de la méthode à tester
    T modelCreated = getService().create(createRequest);

    // Étape 3 : Vérification des résultats
    Assertions.assertNotNull(modelCreated);
    assertModelsEquals(modelCreated, expectedItem);
  }

  public void testUpdate(ID id, U updateRequest, T expectedItem) {
    // Étape 1 : Préparation des données de test
    T currentModel = getModels().stream().filter(_model -> _model.getId().equals(id)).findFirst().orElse(null);
    Assertions.assertNotNull(currentModel, "ITEM WITH THIS ID NOT FOUND IN LIST");
    Mockito.when(getRepository().findById(id)).thenReturn(Optional.of(currentModel));

    // Étape 2 : Exécution de la méthode à tester
    T modelUpdated = getService().update(getModels().get(0).getId(), updateRequest);

    // Étape 3 : Vérification des résultats
    assertModelsEquals(modelUpdated, expectedItem);
  }
}
