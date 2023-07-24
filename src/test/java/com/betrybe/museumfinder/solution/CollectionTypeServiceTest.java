package com.betrybe.museumfinder.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.betrybe.museumfinder.database.MuseumFakeDatabase;
import com.betrybe.museumfinder.dto.CollectionTypeCount;
import com.betrybe.museumfinder.service.CollectionTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class CollectionTypeServiceTest {

  @Mock
  private MuseumFakeDatabase museumFakeDatabase;

  @InjectMocks
  private CollectionTypeService collectionTypeService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testSucessCaseGetCollectioCount() {
    String collectionType = "arte";
    when(museumFakeDatabase.countByCollectionType(collectionType)).thenReturn(69L);

    CollectionTypeCount result = collectionTypeService.countByCollectionTypes(collectionType);
    assertEquals(1, result.collectionTypes().length);
    assertEquals(collectionType, result.collectionTypes()[0]);
    assertEquals(69L, result.count());
  }

  @Test
  void testSucessCaseGetAllCollectioCount() {
    String[] collectionTypes = {"arte", "tecnologia"};
    when(museumFakeDatabase.countByCollectionType("arte")).thenReturn(69L);
    when(museumFakeDatabase.countByCollectionType("tecnologia")).thenReturn(99L);

    CollectionTypeCount result = collectionTypeService.countByCollectionTypes("arte,tecnologia");
    assertEquals(collectionTypes.length, result.collectionTypes().length);
  }
}