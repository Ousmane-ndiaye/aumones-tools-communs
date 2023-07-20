package com.aumones.tools.communs.integration;


import com.aumones.tools.communs.data.model.mongo.MongoAbstractModel;
import com.aumones.tools.communs.data.repository.mongo.MongoAbstractRepository;
import com.aumones.tools.communs.service.mongo.MongoAbstractService;
import com.aumones.tools.communs.web.dto.request.AbstractCreateRequestDto;
import com.aumones.tools.communs.web.dto.request.AbstractSearchRequestDto;
import com.aumones.tools.communs.web.dto.request.AbstractUpdateRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MongoApiIntegrationTest<T extends MongoAbstractModel, S extends AbstractSearchRequestDto,
    C extends AbstractCreateRequestDto<T>, U extends AbstractUpdateRequestDto<T>> {

  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate restTemplate;

  @Autowired
  private MongoAbstractRepository<T, S> mongoAbstractRepository;

  @Autowired
  private MongoAbstractService<T, S, C, U> mongoAbstractService;

  private static HttpHeaders headers;

  private final ObjectMapper objectMapper = new ObjectMapper();

  @BeforeAll
  public static void init() {
    headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
  }

  private String createURLWithPort() {
    return "http://localhost:" + port + "/api/mongos";
  }
}
