package com.aumones.tools.communs.unit.controller;

import com.aumones.tools.communs.exemple.mongo.data.model.MongoModel;
import com.aumones.tools.communs.exemple.mongo.service.MongoService;
import com.aumones.tools.communs.exemple.mongo.web.controller.MongoController;
import com.aumones.tools.communs.exemple.mongo.web.dto.request.MongoSearchRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Collections;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(MongoController.class)
public class MongoControllerUnitTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private MongoService mongoService;

  private MongoModel model;

  private final ObjectMapper objectMapper = new ObjectMapper();

  @BeforeEach
  public void setup() {
    this.model = new MongoModel("123ID", "John Doe", 40);
  }

  // @Test
  public void testList() throws Exception {
    // Étape 1 : Préparation des données de test
    MongoSearchRequestDto searchRequest = new MongoSearchRequestDto();
    when(mongoService.list(searchRequest)).thenReturn(Collections.singletonList(model));

    // Étape 2 : Exécution de l'api à tester
    ResultActions result = mockMvc.perform(get("/api/mongos/list"))
        .andDo(print());

    // Étape 3 : Vérification des résultats
    result.andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$", hasSize(1)))
        .andExpect(jsonPath("$[0].id").value("123ID"))
        .andExpect(jsonPath("$[0].name").value("John Doe"))
        .andExpect(jsonPath("$[0].age").value(40));
  }
}
