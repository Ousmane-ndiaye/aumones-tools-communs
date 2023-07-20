package com.aumones.tools.communs.unit.controller;

import com.aumones.tools.communs.exemple.mongo.data.model.MongoModel;
import com.aumones.tools.communs.exemple.mongo.service.MongoService;
import com.aumones.tools.communs.exemple.mongo.web.controller.MongoController;
import com.aumones.tools.communs.exemple.mongo.web.dto.request.MongoCreateRequestDto;
import com.aumones.tools.communs.exemple.mongo.web.dto.request.MongoSearchRequestDto;
import com.aumones.tools.communs.exemple.mongo.web.dto.request.MongoUpdateRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(MongoController.class)
public class MongoControllerUnitTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private MongoService service;

  private List<MongoModel> models;

  @BeforeEach
  public void setup() {
    this.models = Arrays.asList(
        new MongoModel("123ID", "John Doe", 40),
        new MongoModel("456ID", "Jane Smith", 35)
    );
  }

  @Test
  public void testList() throws Exception {
    // Étape 1 : Préparation des données de test
    when(service.list(any(MongoSearchRequestDto.class))).thenReturn(models);

    // Étape 2 : Exécution de l'api à tester
    ResultActions result = mockMvc.perform(get("/api/mongos/list"))
        .andDo(print());

    // Étape 3 : Vérification des résultats
    result.andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$", hasSize(2)))
        .andExpect(jsonPath("$[0].id").value(models.get(0).getId()))
        .andExpect(jsonPath("$[0].name").value(models.get(0).getName()))
        .andExpect(jsonPath("$[0].age").value(models.get(0).getAge()))
        .andExpect(jsonPath("$[1].id").value(models.get(1).getId()))
        .andExpect(jsonPath("$[1].name").value(models.get(1).getName()))
        .andExpect(jsonPath("$[1].age").value(models.get(1).getAge()));
  }

  @Test
  public void testListWithFilter() throws Exception {
    // Étape 1 : Préparation des données de test
    MongoSearchRequestDto searchRequest = new MongoSearchRequestDto();
    searchRequest.setName(models.get(0).getName());

    MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
    requestParams.add("name", searchRequest.getName());

    when(service.list(eq(searchRequest))).thenReturn(Collections.singletonList(models.get(0)));

    // Étape 2 : Exécution de l'api à tester
    ResultActions result = mockMvc.perform(get("/api/mongos/list")
            .queryParams(requestParams))
        .andDo(print());

    // Étape 3 : Vérification des résultats
    result.andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$", hasSize(1)))
        .andExpect(jsonPath("$[0].id").value(models.get(0).getId()))
        .andExpect(jsonPath("$[0].name").value(models.get(0).getName()))
        .andExpect(jsonPath("$[0].age").value(models.get(0).getAge()));
  }

  @Test
  public void testCreate() throws Exception {
    // Étape 1 : Préparation des données de test
    MongoCreateRequestDto createRequest = new MongoCreateRequestDto(models.get(0).getName(), models.get(0).getAge());
    when(service.create(eq(createRequest))).thenReturn(models.get(0));

    // Étape 2 : Exécution de l'api à tester
    ObjectMapper objectMapper = new ObjectMapper();
    String requestBody = objectMapper.writeValueAsString(createRequest);

    ResultActions result = mockMvc.perform(post("/api/mongos/create")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestBody))
        .andDo(print());

    // Étape 3 : Vérification des résultats
    result.andExpect(status().isCreated())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.id").exists())
        .andExpect(jsonPath("$.name").value(models.get(0).getName()))
        .andExpect(jsonPath("$.age").value(models.get(0).getAge()));
  }

  @Test
  public void testUpdate() throws Exception {
    // Étape 1 : Préparation des données de test
    MongoUpdateRequestDto updateRequest = new MongoUpdateRequestDto(models.get(0).getName(), models.get(0).getAge());
    when(service.update(eq(models.get(0).getId()), any(MongoUpdateRequestDto.class))).thenReturn(models.get(0));

    // Étape 2 : Exécution de l'api à tester
    ObjectMapper objectMapper = new ObjectMapper();
    String requestBody = objectMapper.writeValueAsString(updateRequest);

    ResultActions result = mockMvc.perform(put("/api/mongos/update/"+models.get(0).getId())
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestBody))
        .andDo(print());

    // Étape 3 : Vérification des résultats
    result.andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.id").exists())
        .andExpect(jsonPath("$.name").value(models.get(0).getName()))
        .andExpect(jsonPath("$.age").value(models.get(0).getAge()));
  }
}
