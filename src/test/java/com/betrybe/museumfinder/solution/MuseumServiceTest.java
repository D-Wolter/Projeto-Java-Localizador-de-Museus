package com.betrybe.museumfinder.solution;

import static com.betrybe.museumfinder.evaluation.utils.TestHelpers.createMockMuseum;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

import com.betrybe.museumfinder.database.MuseumFakeDatabase;
import com.betrybe.museumfinder.exception.MuseumNotFoundException;
import com.betrybe.museumfinder.model.Museum;
import com.betrybe.museumfinder.service.MuseumServiceInterface;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("MuseumServiceTest")
public class MuseumServiceTest {
  @MockBean
  MuseumFakeDatabase museumFakeDatabase;

  @Autowired
  MuseumServiceInterface museumService;

  @Test
  @DisplayName("GET/museums/{id}")
  void testGetMuseumById() throws Exception {
    Museum mockMuseum = createMockMuseum(8L);
    Mockito
        .when(museumFakeDatabase.getMuseum(any()))
        .thenReturn(Optional.of(mockMuseum));

    assertEquals(mockMuseum.getId(), museumService.getMuseum(8L).getId());
    assertEquals(mockMuseum.getName(), museumService.getMuseum(8L).getName());
    assertEquals(mockMuseum.getUrl(), museumService.getMuseum(8L).getUrl());
    assertEquals(mockMuseum.getAddress(), museumService.getMuseum(8L).getAddress());
    assertEquals(mockMuseum.getDescription(), museumService.getMuseum(8L).getDescription());
    assertEquals(mockMuseum.getCoordinate(), museumService.getMuseum(8L).getCoordinate());
    assertEquals(mockMuseum.getCollectionType(), museumService.getMuseum(8L).getCollectionType());
    assertEquals(mockMuseum.getSubject(), museumService.getMuseum(8L).getSubject());

  }

  @Test
  @DisplayName("Not Found Exception MuseumService by id")
  void testGetMuseumByIdError() throws Exception {
    Mockito
        .when(museumFakeDatabase.getMuseum(any()))
        .thenReturn(Optional.empty());

    assertThrows(MuseumNotFoundException.class, () -> {
      museumService.getMuseum(9L);
    });
  }
}