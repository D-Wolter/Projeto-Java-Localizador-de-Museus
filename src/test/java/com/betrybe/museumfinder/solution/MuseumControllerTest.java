package com.betrybe.museumfinder.solution;

import static com.betrybe.museumfinder.evaluation.utils.TestHelpers.createMockMuseum;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.betrybe.museumfinder.model.Museum;
import com.betrybe.museumfinder.service.MuseumServiceInterface;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("MuseumControllerTest")
public class MuseumControllerTest {
  @MockBean
  private MuseumServiceInterface museumService;
  @Autowired
  private MockMvc mockMvc;

  @Test
  @DisplayName("GET/museums/{id}")
  void testGetMuseum() throws Exception {
    // Arrange
    Long museumId = 1L;
    Museum museum = createMockMuseum(museumId);
    Mockito.when(museumService.getMuseum(any()))
        .thenReturn(museum);

    // Act
    ResultActions result = mockMvc.perform(get("/museums/{id}", museumId));

    // Assert
    result.andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.id").value(museum.getId()))
        .andExpect(jsonPath("$.name").value(museum.getName()))
        .andExpect(jsonPath("$.address").value(museum.getAddress()))
        .andExpect(jsonPath("$.description").value(museum.getDescription()))
        .andExpect(jsonPath("$.collectionType").value(museum.getCollectionType()))
        .andExpect(jsonPath("$.subject").value(museum.getSubject()))
        .andExpect(jsonPath("$.url").value(museum.getUrl()))
        .andExpect(jsonPath("$.coordinate.latitude").value(museum.getCoordinate().latitude()))
        .andExpect(jsonPath("$.coordinate.longitude").value(museum.getCoordinate().longitude()));

    Mockito.verify(museumService).getMuseum(any());
  }
}
