package com.aumones.tools.communs.unit.service;

import com.aumones.tools.communs.data.model.AbstractModel;
import com.aumones.tools.communs.data.repository.AbstractRepository;
import com.aumones.tools.communs.service.AbstractCRUDService;
import com.aumones.tools.communs.web.dto.request.AbstractCreateRequestDto;
import com.aumones.tools.communs.web.dto.request.AbstractUpdateRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public abstract class AbstractCRUDServiceUnitTest<ID, T extends AbstractModel<ID>, C extends AbstractCreateRequestDto<T>,
    U extends AbstractUpdateRequestDto<T>> {

  public abstract AbstractRepository<ID, T> getRepository();

  public abstract AbstractCRUDService<ID, T, C, U> getService();

  public abstract List<T> getModels();

  public abstract void setup();

  public abstract Class<T> getClassName();

  public abstract C buildCreateRequest();

  public abstract U buildUpdateRequest();

  public abstract void assertModelsEquals(T expected, T actual);

  @Test
  public void testList() {
    // Étape 1 : Préparation des données de test
    when(getRepository().findAll()).thenReturn(getModels());

    // Étape 2 : Exécution de la méthode à tester
    List<T> modelList = getService().list();

    // Étape 3 : Vérification des résultats
    assertEquals(modelList.size(), getModels().size());
    for (int i = 0; i < getModels().size(); i++) {
      assertModelsEquals(modelList.get(i), getModels().get(i));
    }
  }

  @Test
  public void testListWithPageable() {
    // Étape 1 : Préparation des données de test
    int pageSize = 10;
    Pageable pageable = PageRequest.of(0, pageSize);
    Page<T> page = new PageImpl<>(getModels(), pageable, getModels().size());
    when(getRepository().findAll(pageable)).thenReturn(page);

    // Étape 2 : Exécution de la méthode à tester
    Page<T> modelPage = getService().list(pageable);

    // Étape 3 : Vérification des résultats
    assertEquals(getModels().size(), modelPage.getTotalElements());
    assertEquals(pageSize, modelPage.getSize());

    List<T> modelList = modelPage.getContent();
    assertEquals(getModels().size(), modelList.size());
    for (int i = 0; i < modelList.size(); i++) {
      assertModelsEquals(getModels().get(i), modelList.get(i));
    }
  }

  @Test
  public void testGet() {
    // Étape 1 : Préparation des données de test
    T data = getModels().get(0);
    when(getRepository().findById(getModels().get(0).getId())).thenReturn(Optional.of(data));

    // Étape 2 : Exécution de la méthode à tester
    T model = getService().get(getModels().get(0).getId());

    // Étape 3 : Vérification des résultats
    assertNotEquals(model, null);
    assertModelsEquals(model, getModels().get(0));
  }

  @Test
  public void testGetNull() {
    // Étape 1 : Préparation des données de test
    when(getRepository().findById(getModels().get(0).getId())).thenReturn(Optional.empty());

    // Étape 2 : Exécution de la méthode à tester
    T model = getService().get(getModels().get(0).getId());

    // Étape 3 : Vérification des résultats
    assertNull(model);
  }

  @Test
  public void testCreate() {
    // Étape 1 : Préparation des données de test
    when(getRepository().save(any(getClassName()))).thenAnswer(invocation -> {
      T model = invocation.getArgument(0);
      model.setId(getModels().get(0).getId());
      return model;
    });
    C requestDto = buildCreateRequest();

    // Étape 2 : Exécution de la méthode à tester
    T modelCreated = getService().create(requestDto);

    // Étape 3 : Vérification des résultats
    assertNotNull(modelCreated);
    assertModelsEquals(modelCreated, getModels().get(0));
  }

  @Test
  public void testUpdate() {
    // Étape 1 : Préparation des données de test
    U requestDto = buildUpdateRequest();
    T currentModel = getModels().get(0);
    when(getRepository().findById(getModels().get(0).getId())).thenReturn(Optional.of(currentModel));

    // Étape 2 : Exécution de la méthode à tester
    T modelUpdated = getService().update(getModels().get(0).getId(), requestDto);

    // Étape 3 : Vérification des résultats
    assertModelsEquals(modelUpdated, getModels().get(0));
  }
}
