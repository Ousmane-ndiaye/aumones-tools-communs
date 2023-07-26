package com.aumones.tools.communs.unit.controller;

import com.aumones.tools.communs.data.model.AbstractModel;
import com.aumones.tools.communs.service.AbstractCRUDAndSearchService;
import com.aumones.tools.communs.web.dto.request.AbstractCreateRequestDto;
import com.aumones.tools.communs.web.dto.request.AbstractSearchRequestDto;
import com.aumones.tools.communs.web.dto.request.AbstractUpdateRequestDto;
import com.aumones.tools.communs.web.dto.response.AbstractResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.MultiValueMap;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public abstract class AbstractCRUDAndSearchControllerUnitTest<ID, T extends AbstractModel<ID>, S extends AbstractSearchRequestDto,
    C extends AbstractCreateRequestDto<T>, U extends AbstractUpdateRequestDto<T>, R extends AbstractResponseDto<ID>>
    extends AbstractCRUDControllerUnitTest<ID, T, C, U, R> {

  public abstract AbstractCRUDAndSearchService<ID, T, S, C, U> getService();

  public abstract S buildSearchRequest();

  public abstract void assertResponsePageableDto(ResultActions result, int index, T model) throws Exception;


  public abstract MultiValueMap<String, String> buildRequestParams();

  public void testListWithSearch(String endpoint) throws Exception {
    // Étape 1 : Préparation des données de test
    S searchRequest = buildSearchRequest();
    MultiValueMap<String, String> requestParams = buildRequestParams();
    when(getService().list(eq(searchRequest))).thenReturn(getModels());

    // Étape 2 : Exécution de la méthode à tester
    ResultActions result = mockMvc.perform(get(endpoint).queryParams(requestParams)).andDo(print());

    // Étape 3 : Vérification des résultats
    result.andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$", hasSize(getModels().size())));

    for (int i = 0; i < getModels().size(); i++) {
      T model = getModels().get(i);
      result.andExpect(jsonPath("$[" + i + "].id").value(model.getId()));
      assertResponseDto(result, i, model);
    }
  }

  public void testListWithSearchAndPageable(String endpoint) throws Exception {
    // Étape 1 : Préparation des données de test
    S searchRequest = buildSearchRequest();
    MultiValueMap<String, String> requestParams = buildRequestParams();
    requestParams.add("page", "0");
    Pageable pageable = PageRequest.of(0, 10);
    Page<T> page = new PageImpl<>(getModels(), pageable, getModels().size());

    when(getService().list(eq(searchRequest), any(Pageable.class))).thenReturn(page);

    // Étape 2 : Exécution de l'api à tester
    ResultActions result = mockMvc.perform(get(endpoint).queryParams(requestParams)).andDo(print());

    // Étape 3 : Vérification des résultats
    result.andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.content", hasSize(getModels().size())));

    for (int i = 0; i < getModels().size(); i++) {
      T model = getModels().get(i);
      assertResponsePageableDto(result, i, model);
    }
  }
}
