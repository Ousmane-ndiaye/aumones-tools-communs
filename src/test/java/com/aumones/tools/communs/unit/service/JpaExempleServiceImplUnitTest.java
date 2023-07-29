package com.aumones.tools.communs.unit.service;

import com.aumones.tools.communs.exemple.data.jpa.model.JpaExempleModel;
import com.aumones.tools.communs.exemple.data.jpa.repository.JpaExempleRepository;
import com.aumones.tools.communs.exemple.service.jpa.JpaExempleServiceImpl;
import com.aumones.tools.communs.exemple.web.dto.request.ExempleSearchRequestDto;
import com.aumones.tools.communs.exemple.web.dto.request.JpaExempleCreateRequestDto;
import com.aumones.tools.communs.exemple.web.dto.request.JpaExempleUpdateRequestDto;
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
public class JpaExempleServiceImplUnitTest extends AbstractCRUDAndSearchServiceUnitTest<Long, JpaExempleModel,
    ExempleSearchRequestDto, JpaExempleCreateRequestDto, JpaExempleUpdateRequestDto> {

  @Mock
  private JpaExempleRepository repository;

  @InjectMocks
  private JpaExempleServiceImpl service;

  private final List<JpaExempleModel> models = Arrays.asList(
      new JpaExempleModel(123L, "John Doe", 40),
      new JpaExempleModel(456L, "Jane Smith", 35)
  );

  @Override
  public JpaExempleRepository getRepository() {
    return repository;
  }

  @Override
  public JpaExempleServiceImpl getService() {
    return service;
  }

  @Override
  public List<JpaExempleModel> getModels() {
    return this.models;
  }

  @BeforeEach
  public void setup() {}

  @Override
  public Class<JpaExempleModel> getClassName() {
    return JpaExempleModel.class;
  }

  @Override
  public void assertModelsEquals(JpaExempleModel expected, JpaExempleModel actual) {
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
    JpaExempleCreateRequestDto createRequest = new JpaExempleCreateRequestDto(models.get(0).getName(),
        models.get(0).getAge());
    super.testCreate(createRequest, models.get(0));
  }

  @Test
  public void testUpdate() {
    JpaExempleUpdateRequestDto updateRequest = new JpaExempleUpdateRequestDto("Peter Jefferson", 25);
    super.testUpdate(models.get(0).getId(), updateRequest, new JpaExempleModel(models.get(0).getId(),
        "Peter Jefferson", 25));
  }
}
