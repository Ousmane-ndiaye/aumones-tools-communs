package com.aumones.tools.communs.unit.controller;

import com.aumones.tools.communs.exemple.data.mongo.model.MongoExempleModel;
import com.aumones.tools.communs.exemple.service.mongo.MongoExempleService;
import com.aumones.tools.communs.exemple.web.controller.MongoExempleController;
import com.aumones.tools.communs.exemple.web.dto.request.ExempleSearchRequestDto;
import com.aumones.tools.communs.exemple.web.dto.request.MongoExempleCreateRequestDto;
import com.aumones.tools.communs.exemple.web.dto.request.MongoExempleUpdateRequestDto;
import com.aumones.tools.communs.exemple.web.dto.response.MongoExempleResponseDto;
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
@WebMvcTest(MongoExempleController.class)
public class MongoExempleControllerUnitTest extends AbstractCRUDAndSearchControllerUnitTest<String, MongoExempleModel,
    ExempleSearchRequestDto, MongoExempleCreateRequestDto, MongoExempleUpdateRequestDto, MongoExempleResponseDto> {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private MongoExempleService service;

  private final List<MongoExempleModel> models = Arrays.asList(
      new MongoExempleModel("123L", "John Doe", 40),
      new MongoExempleModel("456L", "Jane Smith", 35)
  );

  @Override
  public MongoExempleService getService() {
    return service;
  }

  @Override
  public List<MongoExempleModel> getModels() {
    return this.models;
  }

  @Override
  public ExempleSearchRequestDto buildSearchRequest() {
    ExempleSearchRequestDto exempleSearchRequest = new ExempleSearchRequestDto();
    exempleSearchRequest.setName(models.get(0).getName());
    return exempleSearchRequest;
  }

  @Override
  public MultiValueMap<String, String> buildRequestParams() {
    MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
    requestParams.add("name", models.get(0).getName());
    return requestParams;
  }

  @Override
  public MongoExempleCreateRequestDto buildCreateRequest() {
    return new MongoExempleCreateRequestDto(models.get(0).getName(), models.get(0).getAge());
  }

  @Override
  public MongoExempleUpdateRequestDto buildUpdateRequest() {
    return new MongoExempleUpdateRequestDto("Peter Jefferson", 25);
  }

  @Override
  public MongoExempleModel buildUpdatedResult(String id) {
    return new MongoExempleModel(id, "Peter Jefferson", 25);
  }

  @Override
  public void assertResponseDto(ResultActions result, int index, MongoExempleModel model) throws Exception {
    result.andExpect(jsonPath("$[" + index + "].id").value(model.getId()))
        .andExpect(jsonPath("$[" + index + "].name").value(model.getName()))
        .andExpect(jsonPath("$[" + index + "].age").value(model.getAge()));
  }

  @Override
  public void assertResponseDto(ResultActions result, MongoExempleModel model) throws Exception {
    result.andExpect(jsonPath("$.id").value(model.getId()))
        .andExpect(jsonPath("$.name").value(model.getName()))
        .andExpect(jsonPath("$.age").value(model.getAge()));
  }

  @Override
  public void assertResponsePageableDto(ResultActions result, int index, MongoExempleModel model) throws Exception {
    result.andExpect(jsonPath("$.content[" + index + "].id").value(model.getId()))
        .andExpect(jsonPath("$.content[" + index + "].name").value(model.getName()))
        .andExpect(jsonPath("$.content[" + index + "].age").value(model.getAge()));
  }

  @BeforeEach
  public void setup() {}

  @Test
  public void testListWithSearch() throws Exception {
    super.testListWithSearch("/api/exemple-mongo/list");
  }

  @Test
  public void testListWithSearchAndPageable() throws Exception {
    super.testListWithSearchAndPageable("/api/exemple-mongo/list/pageable");
  }

  @Test
  public void testCreate() throws Exception {
    super.testCreate("/api/exemple-mongo/create");
  }

  @Test
  public void testUpdate() throws Exception {
    super.testUpdate("/api/exemple-mongo/update/"+models.get(0).getId(), models.get(0).getId());
  }
}
