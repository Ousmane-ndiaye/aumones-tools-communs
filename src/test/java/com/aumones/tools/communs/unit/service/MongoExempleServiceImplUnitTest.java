package com.aumones.tools.communs.unit.service;

import com.aumones.tools.communs.exemple.data.mongo.model.MongoExempleModel;
import com.aumones.tools.communs.exemple.data.mongo.repository.MongoExempleRepository;
import com.aumones.tools.communs.exemple.service.mongo.MongoExempleServiceImpl;
import com.aumones.tools.communs.exemple.web.dto.request.ExempleSearchRequestDto;
import com.aumones.tools.communs.exemple.web.dto.request.MongoExempleCreateRequestDto;
import com.aumones.tools.communs.exemple.web.dto.request.MongoExempleUpdateRequestDto;
import com.aumones.tools.communs.tests.service.AbstractCRUDAndSearchServiceUnitTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
public class MongoExempleServiceImplUnitTest extends AbstractCRUDAndSearchServiceUnitTest<String, MongoExempleModel,
    ExempleSearchRequestDto, MongoExempleCreateRequestDto, MongoExempleUpdateRequestDto> {

  @Mock
  private MongoExempleRepository repository;

  @InjectMocks
  private MongoExempleServiceImpl service;

  private final List<MongoExempleModel> models = Arrays.asList(
      new MongoExempleModel("123L", "John Doe", 40),
      new MongoExempleModel("456L", "Jane Smith", 35)
  );

  @Override
  public MongoExempleRepository getRepository() {
    return repository;
  }

  @Override
  public MongoExempleServiceImpl getService() {
    return service;
  }

  @Override
  public List<MongoExempleModel> getModels() {
    return this.models;
  }

  @Override
  @BeforeEach
  public void setup() {}

  @Override
  public Class<MongoExempleModel> getClassName() {
    return MongoExempleModel.class;
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
  public ExempleSearchRequestDto buildSearchRequest() {
    return new ExempleSearchRequestDto();
  }

  @Override
  public void assertModelsEquals(MongoExempleModel expected, MongoExempleModel actual) {
    assertEquals(expected.getId(), actual.getId());
    assertEquals(expected.getName(), actual.getName());
    assertEquals(expected.getAge(), actual.getAge());
  }

  @Test
  @Override
  public void testListWithSearch() {
    super.testListWithSearch();
  }

  @Test
  @Override
  public void testListWithSearchAndPageable() {
    super.testListWithSearchAndPageable();
  }

  @Test
  @Override
  public void testList() {
    super.testList();
  }

  @Test
  @Override
  public void testListWithPageable() {
    super.testListWithPageable();
  }

  @Test
  @Override
  public void testGet() {
    super.testGet();
  }

  @Test
  @Override
  public void testGetNull() {
    super.testGetNull();
  }

  @Test
  @Override
  public void testCreate() {
    super.testCreate();
  }

  @Test
  @Override
  public void testUpdate() {
    super.testUpdate();
  }
}
