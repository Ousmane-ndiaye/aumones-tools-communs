package com.aumones.tools.communs.tests.controller;

import com.aumones.tools.communs.data.model.AbstractModel;
import com.aumones.tools.communs.service.AbstractCRUDAndSearchService;
import com.aumones.tools.communs.web.dto.request.AbstractCreateRequestDto;
import com.aumones.tools.communs.web.dto.request.AbstractSearchRequestDto;
import com.aumones.tools.communs.web.dto.request.AbstractUpdateRequestDto;
import com.aumones.tools.communs.web.dto.response.AbstractResponseDto;
import org.hamcrest.Matchers;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.MultiValueMap;

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
    Mockito.when(getService().list(ArgumentMatchers.eq(searchRequest))).thenReturn(getModels());

    // Étape 2 : Exécution de la méthode à tester
    ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get(endpoint).queryParams(requestParams)).andDo(MockMvcResultHandlers.print());

    // Étape 3 : Vérification des résultats
    result.andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(getModels().size())));

    for (int i = 0; i < getModels().size(); i++) {
      T model = getModels().get(i);
      result.andExpect(MockMvcResultMatchers.jsonPath("$[" + i + "].id").value(model.getId()));
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

    Mockito.when(getService().list(ArgumentMatchers.eq(searchRequest), ArgumentMatchers.any(Pageable.class))).thenReturn(page);

    // Étape 2 : Exécution de l'api à tester
    ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get(endpoint)
          .queryParams(requestParams))
        .andDo(MockMvcResultHandlers.print());

    // Étape 3 : Vérification des résultats
    result.andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.hasSize(getModels().size())));

    for (int i = 0; i < getModels().size(); i++) {
      T model = getModels().get(i);
      assertResponsePageableDto(result, i, model);
    }
  }
}
