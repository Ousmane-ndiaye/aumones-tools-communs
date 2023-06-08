package com.aumones.tools.communs.unit.service;

import com.aumones.tools.communs.exemple.mongo.web.dto.request.MongoCreateRequestDto;
import com.aumones.tools.communs.exemple.mongo.web.dto.request.MongoSearchRequestDto;
import com.aumones.tools.communs.exemple.mongo.web.dto.request.MongoUpdateRequestDto;
import com.aumones.tools.communs.exemple.mongo.data.model.MongoModel;
import com.aumones.tools.communs.exemple.mongo.data.repository.MongoRepository;
import com.aumones.tools.communs.exemple.mongo.service.MongoService;
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
public class MongoServiceUnitTest {

  @Mock
  private MongoRepository repository;

  @InjectMocks
  private MongoService service;

  @BeforeEach
  public void setup() {

  }

  @Test
  public void testList() {
    // Étape 1 : Préparation des données de test
    MongoModel data1 = new MongoModel("123ID", "user1", 23),
        data2 = new MongoModel("456ID","user2", 27);
    MongoSearchRequestDto searchRequest = new MongoSearchRequestDto();
    when(repository.search(searchRequest)).thenReturn(Arrays.asList(data1, data2));

    // Étape 2 : Exécution de la méthode à tester
    List<MongoModel> modelList = service.list(searchRequest);

    // Étape 3 : Vérification des résultats
    assertEquals(modelList.size(), 2);
    assertEquals(modelList.get(0).getId(), "123ID");
    assertEquals(modelList.get(1).getId(), "456ID");
    assertEquals(modelList.get(0).getName(), "user1");
    assertEquals(modelList.get(1).getName(), "user2");
    assertEquals(modelList.get(0).getAge(), 23);
    assertEquals(modelList.get(1).getAge(), 27);
  }

  @Test
  public void testGet() {
    // Étape 1 : Préparation des données de test
    MongoModel data = new MongoModel("123ID", "user1", 23);
    when(repository.findById("123ID")).thenReturn(Optional.of(data));

    // Étape 2 : Exécution de la méthode à tester
    MongoModel model = service.get("123ID");

    // Étape 3 : Vérification des résultats
    assertNotEquals(model, null);
    assertEquals(model.getId(), "123ID");
    assertEquals(model.getName(), "user1");
    assertEquals(model.getAge(), 23);
  }

  @Test
  public void testGetNull() {
    // Étape 1 : Préparation des données de test
    when(repository.findById("123ID")).thenReturn(Optional.empty());

    // Étape 2 : Exécution de la méthode à tester
    MongoModel model = service.get("123ID");

    // Étape 3 : Vérification des résultats
    assertNull(model);
  }

  @Test
  public void testCreate() {
    // Étape 1 : Préparation des données de test
    when(repository.save(any(MongoModel.class))).thenAnswer(invocation -> {
      MongoModel model = invocation.getArgument(0);
      model.setId("123ID");
      return model;
    });
    MongoCreateRequestDto requestDto = new MongoCreateRequestDto("user1", 25);

    // Étape 2 : Exécution de la méthode à tester
    MongoModel modelCreated = service.create(requestDto);

    // Étape 3 : Vérification des résultats
    assertNotNull(modelCreated.getId());
    assertEquals(requestDto.getName(), modelCreated.getName());
    assertEquals(requestDto.getAge(), modelCreated.getAge());
  }

  @Test
  public void testUpdate() {
    // Étape 1 : Préparation des données de test
    MongoUpdateRequestDto requestDto = new MongoUpdateRequestDto("userEdited", 25);
    MongoModel currentModel = new MongoModel("123ID", "user1", 23);
    when(repository.findById("123ID")).thenReturn(Optional.of(currentModel));

    // Étape 2 : Exécution de la méthode à tester
    MongoModel modelUpdated = service.update("123ID", requestDto);

    // Étape 3 : Vérification des résultats
    assertEquals("123ID", modelUpdated.getId());
    assertEquals(requestDto.getName(), modelUpdated.getName());
    assertEquals(requestDto.getAge(), modelUpdated.getAge());
  }
}
