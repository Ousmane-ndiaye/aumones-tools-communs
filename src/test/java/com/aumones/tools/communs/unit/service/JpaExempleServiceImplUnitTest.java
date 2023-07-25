package com.aumones.tools.communs.unit.service;

import com.aumones.tools.communs.exemple.data.jpa.model.JpaExempleModel;
import com.aumones.tools.communs.exemple.data.jpa.repository.JpaExempleRepository;
import com.aumones.tools.communs.exemple.service.jpa.JpaExempleServiceImpl;
import com.aumones.tools.communs.exemple.web.dto.request.ExempleSearchRequestDto;
import com.aumones.tools.communs.exemple.web.dto.request.JpaExempleCreateRequestDto;
import com.aumones.tools.communs.exemple.web.dto.request.JpaExempleUpdateRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class JpaExempleServiceImplUnitTest {

  @Mock
  private JpaExempleRepository repository;

  @InjectMocks
  private JpaExempleServiceImpl service;

  private List<JpaExempleModel> models;

  @BeforeEach
  public void setup() {
    this.models = Arrays.asList(
        new JpaExempleModel(123L, "John Doe", 40),
        new JpaExempleModel(456L, "Jane Smith", 35)
    );
  }

  @Test
  public void testList() {
    // Étape 1 : Préparation des données de test
    ExempleSearchRequestDto searchRequest = new ExempleSearchRequestDto();
    when(repository.search(eq(searchRequest))).thenReturn(models);

    // Étape 2 : Exécution de la méthode à tester
    List<JpaExempleModel> modelList = service.list(searchRequest);

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
    JpaExempleModel data = models.get(0);
    when(repository.findById(models.get(0).getId())).thenReturn(Optional.of(data));

    // Étape 2 : Exécution de la méthode à tester
    JpaExempleModel model = service.get(models.get(0).getId());

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
    JpaExempleModel model = service.get(models.get(0).getId());

    // Étape 3 : Vérification des résultats
    assertNull(model);
  }

  @Test
  public void testCreate() {
    // Étape 1 : Préparation des données de test
    when(repository.save(any(JpaExempleModel.class))).thenAnswer(invocation -> {
      JpaExempleModel model = invocation.getArgument(0);
      model.setId(models.get(0).getId());
      return model;
    });
    JpaExempleCreateRequestDto requestDto = new JpaExempleCreateRequestDto(models.get(0).getName(), models.get(0).getAge());

    // Étape 2 : Exécution de la méthode à tester
    JpaExempleModel modelCreated = service.create(requestDto);

    // Étape 3 : Vérification des résultats
    assertNotNull(modelCreated);
    assertEquals(modelCreated.getId(), models.get(0).getId());
    assertEquals(requestDto.getName(), modelCreated.getName());
    assertEquals(requestDto.getAge(), modelCreated.getAge());
  }

  @Test
  public void testUpdate() {
    // Étape 1 : Préparation des données de test
    JpaExempleUpdateRequestDto requestDto = new JpaExempleUpdateRequestDto("Peter Jefferson", 25);
    JpaExempleModel currentModel = models.get(0);
    when(repository.findById(models.get(0).getId())).thenReturn(Optional.of(currentModel));

    // Étape 2 : Exécution de la méthode à tester
    JpaExempleModel modelUpdated = service.update(models.get(0).getId(), requestDto);

    // Étape 3 : Vérification des résultats
    assertEquals(models.get(0).getId(), modelUpdated.getId());
    assertEquals(requestDto.getName(), modelUpdated.getName());
    assertEquals(requestDto.getAge(), modelUpdated.getAge());
  }
}
