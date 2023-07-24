package com.betrybe.museumfinder.solution;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc

class CollectionTypeControllerTest {

  @Autowired
  private MockMvc mockMvn;

  @Test
  void testSucessCaseGetCollectioCount() throws Exception {
    mockMvn.perform(MockMvcRequestBuilders.get("/collections/count/arte"))
        .andExpect(MockMvcResultMatchers.status().isOk());
  }

  @Test
  void testSucessCaseCheckPathParams() throws Exception {
    mockMvn.perform(MockMvcRequestBuilders.get("/collections/count/arte,tecnologia"))
        .andExpect(MockMvcResultMatchers.status().isOk());
  }

  @Test
  void testSucessCaseGetAllCollectioCount() throws Exception {
    mockMvn.perform(MockMvcRequestBuilders.get("/collections/count/arte,tecnologia"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.collectionTypes").isArray())
        .andExpect(MockMvcResultMatchers.jsonPath("$.count").value("172"));
  }
}