package com.aumones.tools.communs.unit.controller;

import com.aumones.tools.communs.exemple.data.jpa.model.JpaExempleModel;
import com.aumones.tools.communs.exemple.service.jpa.JpaExempleService;
import com.aumones.tools.communs.exemple.web.controller.JpaExempleController;
import com.aumones.tools.communs.exemple.web.dto.request.ExempleSearchRequestDto;
import com.aumones.tools.communs.exemple.web.dto.request.JpaExempleCreateRequestDto;
import com.aumones.tools.communs.exemple.web.dto.request.JpaExempleUpdateRequestDto;
import com.aumones.tools.communs.exemple.web.dto.response.JpaExempleResponseDto;
import com.aumones.tools.communs.tests.controller.AbstractCRUDAndSearchControllerUnitTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(JpaExempleController.class)
public class JpaExempleControllerUnitTest extends AbstractCRUDAndSearchControllerUnitTest<Long, JpaExempleModel,
    ExempleSearchRequestDto, JpaExempleCreateRequestDto, JpaExempleUpdateRequestDto, JpaExempleResponseDto> {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private JpaExempleService service;

  private final List<JpaExempleModel> models = Arrays.asList(
      new JpaExempleModel(123L, "John Doe", 40),
      new JpaExempleModel(456L, "Jane Smith", 35)
  );

  @Override
  public JpaExempleService getService() {
    return service;
  }

  @Override
  public List<JpaExempleModel> getModels() {
    return this.models;
  }

  @Override
  public void assertResponseDto(ResultActions result, int index, JpaExempleModel model) throws Exception {
    result.andExpect(jsonPath("$[" + index + "].id").value(model.getId()))
        .andExpect(jsonPath("$[" + index + "].name").value(model.getName()))
        .andExpect(jsonPath("$[" + index + "].age").value(model.getAge()));
  }

  @Override
  public void assertResponseDto(ResultActions result, JpaExempleModel model) throws Exception {
    result.andExpect(jsonPath("$.id").value(model.getId()))
        .andExpect(jsonPath("$.name").value(model.getName()))
        .andExpect(jsonPath("$.age").value(model.getAge()));
  }

  @Override
  public void assertResponsePageableDto(ResultActions result, int index, JpaExempleModel model) throws Exception {
    result.andExpect(jsonPath("$.content[" + index + "].id").value(model.getId()))
        .andExpect(jsonPath("$.content[" + index + "].name").value(model.getName()))
        .andExpect(jsonPath("$.content[" + index + "].age").value(model.getAge()));
  }

  @BeforeEach
  public void setup() {}

  @Test
  public void testListWithSearch() throws Exception {
    ExempleSearchRequestDto exempleSearchRequest = new ExempleSearchRequestDto();
    exempleSearchRequest.setName(models.get(0).getName());
    MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
    requestParams.add("name", models.get(0).getName());

    super.testListWithSearch("/api/exemple-jpa/list", exempleSearchRequest, requestParams);
  }

  @Test
  public void testListWithSearchAndPageable() throws Exception {
    ExempleSearchRequestDto exempleSearchRequest = new ExempleSearchRequestDto();
    exempleSearchRequest.setName(models.get(0).getName());
    MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
    requestParams.add("name", models.get(0).getName());
    super.testListWithSearchAndPageable("/api/exemple-jpa/list/pageable", exempleSearchRequest, requestParams,
        10, 0);
  }

  @Test
  public void testCreate() throws Exception {
    JpaExempleCreateRequestDto createRequest = new JpaExempleCreateRequestDto(models.get(0).getName(),
        models.get(0).getAge());
    JpaExempleModel expectedItem = new JpaExempleModel(models.get(0).getId(),
        "Peter Jefferson", 25);
    super.testCreate("/api/exemple-jpa/create", createRequest, expectedItem);
  }

  @Test
  public void testUpdate() throws Exception {
    String endpoint = "/api/exemple-jpa/update/"+models.get(0).getId();
    JpaExempleUpdateRequestDto updateRequest = new JpaExempleUpdateRequestDto("Peter Jefferson", 25);
    JpaExempleModel expectedItem = new JpaExempleModel(models.get(0).getId(),
        "Peter Jefferson", 25);
    super.testUpdate(endpoint, models.get(0).getId(), updateRequest, expectedItem);
  }
}
