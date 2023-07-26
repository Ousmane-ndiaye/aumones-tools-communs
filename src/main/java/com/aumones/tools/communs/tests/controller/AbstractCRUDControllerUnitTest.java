package com.aumones.tools.communs.tests.controller;

import com.aumones.tools.communs.data.model.AbstractModel;
import com.aumones.tools.communs.service.AbstractCRUDService;
import com.aumones.tools.communs.web.dto.request.AbstractCreateRequestDto;
import com.aumones.tools.communs.web.dto.request.AbstractUpdateRequestDto;
import com.aumones.tools.communs.web.dto.response.AbstractResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;


public abstract class AbstractCRUDControllerUnitTest<ID, T extends AbstractModel<ID>,
    C extends AbstractCreateRequestDto<T>, U extends AbstractUpdateRequestDto<T>, R extends AbstractResponseDto<ID>> {

  @Autowired
  protected MockMvc mockMvc;

  public abstract AbstractCRUDService<ID, T, C, U> getService();

  public abstract List<T> getModels();

  public abstract void setup();

  public abstract C buildCreateRequest();

  public abstract U buildUpdateRequest();

  public abstract T buildCreatedResult();

  public abstract T buildUpdatedResult(ID id);

  public abstract void assertResponseDto(ResultActions result, int index, T model) throws Exception;

  public abstract void assertResponseDto(ResultActions result, T model) throws Exception;

  public void testList(String endpoint) throws Exception {
    // Étape 1 : Préparation des données de test
    Mockito.when(getService().list()).thenReturn(getModels());

    // Étape 2 : Exécution de la méthode à tester
    ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get(endpoint)).andDo(MockMvcResultHandlers.print());

    // Étape 3 : Vérification des résultats
    result.andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(getModels().size())));

    for (int i = 0; i < getModels().size(); i++) {
      assertResponseDto(result, i, getModels().get(i));
    }
  }

  public void testListWithPageable(String endpoint) throws Exception {
    // Étape 1 : Préparation des données de test
    MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
    requestParams.add("page", "0");
    int pageSize = 10;
    Pageable pageable = PageRequest.of(0, pageSize);
    Page<T> page = new PageImpl<>(getModels(), pageable, getModels().size());

    Mockito.when(getService().list(ArgumentMatchers.eq(pageable))).thenReturn(page);

    // Étape 2 : Exécution de l'api à tester
    ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get(endpoint).queryParams(requestParams)).andDo(MockMvcResultHandlers.print());

    // Étape 3 : Vérification des résultats
    result.andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.hasSize(getModels().size())));

    for (int i = 0; i < getModels().size(); i++) {
      assertResponseDto(result, i, getModels().get(i));
    }
  }

  public void testGet(String endpoint, ID id) throws Exception {
    // Étape 1 : Préparation des données de test
    T model = getModels().stream().filter(_model -> _model.getId().equals(id)).findFirst().orElse(null);
    Assertions.assertNotNull(model, "ITEM WITH THIS ID NOT FOUND IN LIST");
    Mockito.when(getService().get(id)).thenReturn(model);

    // Étape 2 : Exécution de la méthode à tester
    ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get(endpoint)).andDo(MockMvcResultHandlers.print());

    // Étape 3 : Vérification des résultats
    result.andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));

    assertResponseDto(result, model);
  }

  public void testCreate(String endpoint) throws Exception {
    // Étape 1 : Préparation des données de test
    C createRequest = buildCreateRequest();
    T createResult = buildCreatedResult();
    Mockito.when(getService().create(createRequest)).thenReturn(createResult);

    // Étape 2 : Exécution de l'api à tester
    ObjectMapper objectMapper = new ObjectMapper();
    String requestBody = objectMapper.writeValueAsString(createRequest);

    ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post(endpoint)
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestBody))
        .andDo(MockMvcResultHandlers.print());

    // Étape 3 : Vérification des résultats
    result.andExpect(MockMvcResultMatchers.status().isCreated())
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());

    assertResponseDto(result, createResult);
  }

  public void testUpdate(String endpoint, ID id) throws Exception {
    // Étape 1 : Préparation des données de test
    U updateRequest = buildUpdateRequest();
    T updateResult = buildUpdatedResult(id);
    Mockito.when(getService().update(ArgumentMatchers.eq(id), ArgumentMatchers.eq(updateRequest))).thenReturn(updateResult);

    // Étape 2 : Exécution de l'api à tester
    ObjectMapper objectMapper = new ObjectMapper();
    String requestBody = objectMapper.writeValueAsString(updateRequest);

    ResultActions result = mockMvc.perform(MockMvcRequestBuilders.put(endpoint)
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestBody))
        .andDo(MockMvcResultHandlers.print());

    // Étape 3 : Vérification des résultats
    result.andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());

    assertResponseDto(result, updateResult);
  }
}
