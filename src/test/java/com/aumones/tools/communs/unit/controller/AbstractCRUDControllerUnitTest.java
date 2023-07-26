package com.aumones.tools.communs.unit.controller;

import com.aumones.tools.communs.data.model.AbstractModel;
import com.aumones.tools.communs.service.AbstractCRUDService;
import com.aumones.tools.communs.web.dto.request.AbstractCreateRequestDto;
import com.aumones.tools.communs.web.dto.request.AbstractUpdateRequestDto;
import com.aumones.tools.communs.web.dto.response.AbstractResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public abstract class AbstractCRUDControllerUnitTest<ID, T extends AbstractModel<ID>,
    C extends AbstractCreateRequestDto<T>, U extends AbstractUpdateRequestDto<T>, R extends AbstractResponseDto<ID>> {

  @Autowired
  protected MockMvc mockMvc;

  public abstract AbstractCRUDService<ID, T, C, U> getService();

  public abstract List<T> getModels();

  public abstract void setup();

  public abstract C buildCreateRequest();

  public abstract U buildUpdateRequest();

  public abstract T buildUpdatedResult(ID id);

  public abstract void assertResponseDto(ResultActions result, int index, T model) throws Exception;

  public abstract void assertResponseDto(ResultActions result, T model) throws Exception;

  public void testList(String endpoint) throws Exception {
    // Étape 1 : Préparation des données de test
    when(getService().list()).thenReturn(getModels());

    // Étape 2 : Exécution de la méthode à tester
    ResultActions result = mockMvc.perform(get(endpoint)).andDo(print());

    // Étape 3 : Vérification des résultats
    result.andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$", hasSize(getModels().size())));

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

    when(getService().list(eq(pageable))).thenReturn(page);

    // Étape 2 : Exécution de l'api à tester
    ResultActions result = mockMvc.perform(get(endpoint).queryParams(requestParams)).andDo(print());

    // Étape 3 : Vérification des résultats
    result.andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.content", hasSize(getModels().size())));

    for (int i = 0; i < getModels().size(); i++) {
      assertResponseDto(result, i, getModels().get(i));
    }
  }

  public void testCreate(String endpoint) throws Exception {
    // Étape 1 : Préparation des données de test
    C createRequest = buildCreateRequest();
    when(getService().create(createRequest)).thenReturn(getModels().get(0));

    // Étape 2 : Exécution de l'api à tester
    ObjectMapper objectMapper = new ObjectMapper();
    String requestBody = objectMapper.writeValueAsString(createRequest);

    ResultActions result = mockMvc.perform(post(endpoint)
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestBody))
        .andDo(print());

    // Étape 3 : Vérification des résultats
    result.andExpect(status().isCreated())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.id").exists());

    assertResponseDto(result, getModels().get(0));
  }

  public void testUpdate(String endpoint, ID id) throws Exception {
    // Étape 1 : Préparation des données de test
    U updateRequest = buildUpdateRequest();
    T updateResult = buildUpdatedResult(id);
    when(getService().update(eq(id), eq(updateRequest))).thenReturn(updateResult);

    // Étape 2 : Exécution de l'api à tester
    ObjectMapper objectMapper = new ObjectMapper();
    String requestBody = objectMapper.writeValueAsString(updateRequest);

    ResultActions result = mockMvc.perform(put(endpoint)
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestBody))
        .andDo(print());

    // Étape 3 : Vérification des résultats
    result.andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.id").exists());

    assertResponseDto(result, updateResult);
  }
}
