package com.aumones.tools.communs.integration;


import com.aumones.tools.communs.exemple.data.mongo.model.MongoExempleModel;
import com.aumones.tools.communs.exemple.data.mongo.repository.MongoExempleRepository;
import com.aumones.tools.communs.exemple.service.mongo.MongoExempleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MongoApiIntegrationTest {

  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate restTemplate;

  @Autowired
  private MongoExempleRepository repository;

  @Autowired
  private MongoExempleService service;

  private static HttpHeaders headers;

  private final ObjectMapper objectMapper = new ObjectMapper();
  private List<MongoExempleModel> models;

  @BeforeAll
  public static void init() {
    headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
  }

  @BeforeEach
  public void setup() {
    this.models = Arrays.asList(
        new MongoExempleModel("123ID", "John Doe", 40),
        new MongoExempleModel("456ID", "Jane Smith", 35)
    );
  }

  private String createURLWithPort() {
    return "http://localhost:" + port + "/api/exemple-mongo";
  }

  @Test
  public void testList() throws Exception {
    // Étape 1 : Préparation des données de test

    // Étape 2 : Exécution de l'api à tester
    HttpEntity<String> requestEntity = new HttpEntity<>(null, headers);
    ResponseEntity<List<MongoExempleModel>> response = restTemplate.exchange(
        createURLWithPort() + "/list",
        HttpMethod.GET,
        requestEntity,
        new ParameterizedTypeReference<List<MongoExempleModel>>() {}
    );

    // Étape 3 : Vérification des résultats
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    List<MongoExempleModel> responseModels = response.getBody();
    assertThat(responseModels).isNotNull().hasSize(0);
  }
}
