package com.aumones.tools.communs.unit.service;

import com.aumones.tools.communs.exemple.data.mongo.model.MongoExempleModel;
import com.aumones.tools.communs.exemple.data.mongo.repository.MongoExempleRepository;
import com.aumones.tools.communs.exemple.service.mongo.MongoExempleServiceImpl;
import com.aumones.tools.communs.exemple.web.dto.request.MongoExempleCreateRequestDto;
import com.aumones.tools.communs.exemple.web.dto.request.ExempleSearchRequestDto;
import com.aumones.tools.communs.exemple.web.dto.request.MongoExempleUpdateRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
public class MongoExempleServiceImplUnitTest {

  @Mock
  private MongoExempleRepository repository;

  @InjectMocks
  private MongoExempleServiceImpl service;

  private List<MongoExempleModel> models;

  @BeforeEach
  public void setup() {
    this.models = Arrays.asList(
        new MongoExempleModel("123ID", "John Doe", 40),
        new MongoExempleModel("456ID", "Jane Smith", 35)
    );
  }

  @Test
  public void testList() {
    // Étape 1 : Préparation des données de test
    ExempleSearchRequestDto searchRequest = new ExempleSearchRequestDto();
    when(repository.search(eq(searchRequest))).thenReturn(models);

    // Étape 2 : Exécution de la méthode à tester
    List<MongoExempleModel> modelList = service.list(searchRequest);

    // Étape 3 : Vérification des résultats
    assertEquals(modelList.size(), models.size());
    assertEquals(modelList.get(0).getId(), models.get(0).getId());
    assertEquals(modelList.get(0).getName(), models.get(0).getName());
    assertEquals(modelList.get(0).getAge(), models.get(0).getAge());
    assertEquals(modelList.get(1).getId(), models.get(1).getId());
    assertEquals(modelList.get(1).getName(), models.get(1).getName());
    assertEquals(modelList.get(1).getAge(), models.get(1).getAge());
  }

  @Test
  public void testGet() {
    // Étape 1 : Préparation des données de test
    MongoExempleModel data = models.get(0);
    when(repository.findById(models.get(0).getId())).thenReturn(Optional.of(data));

    // Étape 2 : Exécution de la méthode à tester
    MongoExempleModel model = service.get(models.get(0).getId());

    // Étape 3 : Vérification des résultats
    assertNotEquals(model, null);
    assertEquals(model.getId(), models.get(0).getId());
    assertEquals(model.getName(), models.get(0).getName());
    assertEquals(model.getAge(), models.get(0).getAge());
  }

  @Test
  public void testGetNull() {
    // Étape 1 : Préparation des données de test
    when(repository.findById(models.get(0).getId())).thenReturn(Optional.empty());

    // Étape 2 : Exécution de la méthode à tester
    MongoExempleModel model = service.get(models.get(0).getId());

    // Étape 3 : Vérification des résultats
    assertNull(model);
  }

  @Test
  public void testCreate() {
    // Étape 1 : Préparation des données de test
    when(repository.save(any(MongoExempleModel.class))).thenAnswer(invocation -> {
      MongoExempleModel model = invocation.getArgument(0);
      model.setId(models.get(0).getId());
      return model;
    });
    MongoExempleCreateRequestDto requestDto = new MongoExempleCreateRequestDto(models.get(0).getName(), models.get(0).getAge());

    // Étape 2 : Exécution de la méthode à tester
    MongoExempleModel modelCreated = service.create(requestDto);

    // Étape 3 : Vérification des résultats
    assertNotNull(modelCreated);
    assertEquals(modelCreated.getId(), models.get(0).getId());
    assertEquals(requestDto.getName(), modelCreated.getName());
    assertEquals(requestDto.getAge(), modelCreated.getAge());
  }

  @Test
  public void testUpdate() {
    // Étape 1 : Préparation des données de test
    MongoExempleUpdateRequestDto requestDto = new MongoExempleUpdateRequestDto("Peter Jefferson", 25);
    MongoExempleModel currentModel = models.get(0);
    when(repository.findById(models.get(0).getId())).thenReturn(Optional.of(currentModel));

    // Étape 2 : Exécution de la méthode à tester
    MongoExempleModel modelUpdated = service.update(models.get(0).getId(), requestDto);

    // Étape 3 : Vérification des résultats
    assertEquals(models.get(0).getId(), modelUpdated.getId());
    assertEquals(requestDto.getName(), modelUpdated.getName());
    assertEquals(requestDto.getAge(), modelUpdated.getAge());
  }
}
