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

  @BeforeEach
  public void setup() {}

  @Override
  public Class<MongoExempleModel> getClassName() {
    return MongoExempleModel.class;
  }

  @Override
  public void assertModelsEquals(MongoExempleModel expected, MongoExempleModel actual) {
    assertEquals(expected.getId(), actual.getId());
    assertEquals(expected.getName(), actual.getName());
    assertEquals(expected.getAge(), actual.getAge());
  }

  @Test
  public void testListWithSearch() {
    ExempleSearchRequestDto searchRequest = new ExempleSearchRequestDto();
    super.testListWithSearch(searchRequest);
  }

  @Test
  public void testListWithSearchAndPageable() {
    ExempleSearchRequestDto searchRequest = new ExempleSearchRequestDto();
    super.testListWithSearchAndPageable(searchRequest, 10, 0);
  }

  @Test
  public void testList() {
    super.testList();
  }

  @Test
  public void testListWithPageable() {
    super.testListWithPageable(10, 0);
  }

  @Test
  public void testGet() {
    super.testGet(models.get(0).getId());
  }

  @Test
  public void testGetNull() {
    super.testGetNull();
  }

  @Test
  public void testCreate() {
    MongoExempleCreateRequestDto createRequest = new MongoExempleCreateRequestDto(models.get(0).getName(),
        models.get(0).getAge());
    super.testCreate(createRequest, models.get(0));
  }

  @Test
  public void testUpdate() {
    MongoExempleUpdateRequestDto updateRequest = new MongoExempleUpdateRequestDto("Peter Jefferson", 25);
    super.testUpdate(models.get(0).getId(), updateRequest, new MongoExempleModel(models.get(0).getId(),
        "Peter Jefferson", 25));
  }
}
