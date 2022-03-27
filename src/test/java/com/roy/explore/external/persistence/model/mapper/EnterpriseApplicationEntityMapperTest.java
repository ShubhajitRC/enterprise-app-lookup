package com.roy.explore.external.persistence.model.mapper;

import com.roy.explore.external.persistence.model.EnterpriseApplicationEntity;
import com.roy.explore.openapi.model.EnterpriseApplication;
import com.roy.explore.testUtil.TestSuite;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("Unit Testing - EnterpriseApplicationEntityMapper")
class EnterpriseApplicationEntityMapperTest {

  @Nested
  @DisplayName("Map from enterprise application entity to enterprise application")
  class TestMapFromEnterpriseApplicationEntityToEnterpriseApplication {

    @Test
    @Tag(TestSuite.UNIT_TEST)
    @DisplayName("Should map each field correctly")
    void shouldMapEachFieldCorrectly() {
      //Given
      EnterpriseApplicationEntity entity =
        EnterpriseApplicationEntity.builder()
          .applicationId("APP_TEST_01")
          .applicationName("APP_TEST")
          .applicationUrl("http://test/")
          .applicationAuthenticationType("TEST")
          .upStreamApplication("UP_TEST")
          .downStreamApplication("DOWN_TEST")
          .build();

      //When
      EnterpriseApplication domain = EnterpriseApplicationEntityMapper.get().toEnterpriseApplication(entity);

      //Then
      assertNotNull(domain);
      assertEquals("APP_TEST_01", domain.getApplicationId());
      assertEquals("APP_TEST", domain.getApplicationName());
      assertEquals("http://test/", domain.getApplicationUrl());
      assertEquals("TEST", domain.getApplicationAuthenticationType());
      assertEquals("UP_TEST", domain.getUpStreamApplication());
      assertEquals("DOWN_TEST", domain.getDownStreamApplication());
    }
  }

  @Nested
  @DisplayName("Map from enterprise application to enterprise application entity")
  class TestMapFromEnterpriseApplicationToEnterpriseApplicationEntity {

    @Test
    @Tag(TestSuite.UNIT_TEST)
    @DisplayName("Should map each field correctly")
    void shouldMapEachFieldCorrectly() {
      //Given
      EnterpriseApplication domain =
        new EnterpriseApplication()
          .applicationId("APP_TEST_01")
          .applicationName("APP_TEST")
          .applicationUrl("http://test/")
          .applicationAuthenticationType("TEST")
          .upStreamApplication("UP_TEST")
          .downStreamApplication("DOWN_TEST");

      //When
      EnterpriseApplicationEntity entity = EnterpriseApplicationEntityMapper.get().toEnterpriseApplicationEntity(domain);

      //Then
      assertNotNull(entity);
      assertEquals("APP_TEST_01", entity.getApplicationId());
      assertEquals("APP_TEST", entity.getApplicationName());
      assertEquals("http://test/", entity.getApplicationUrl());
      assertEquals("TEST", entity.getApplicationAuthenticationType());
      assertEquals("UP_TEST", entity.getUpStreamApplication());
      assertEquals("DOWN_TEST", entity.getDownStreamApplication());
    }
  }
}
