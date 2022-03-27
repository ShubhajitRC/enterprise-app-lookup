package com.roy.explore.external.persistence.repository;


import com.roy.explore.core.util.ApplicationConstants;
import com.roy.explore.external.persistence.ApplicationsJpaRepository;
import com.roy.explore.external.persistence.model.EnterpriseApplicationEntity;
import com.roy.explore.openapi.model.EnterpriseApplication;
import com.roy.explore.testUtil.TestSuite;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@DisplayName("Unit Testing - ApplicationsJpaRepository")
@ExtendWith(MockitoExtension.class)
class ApplicationsJpaRepositoryTest {

  private ApplicationsJpaRepository applicationsJpaRepository;

  @Mock
  private EnterpriseApplicationEntityRepository enterpriseApplicationEntityRepositoryMock;

  @BeforeEach
  @DisplayName("Setup")
  void setup() {
    this.applicationsJpaRepository = new ApplicationsJpaRepository(enterpriseApplicationEntityRepositoryMock);
  }

  @Test
  @Tag(TestSuite.UNIT_TEST)
  @DisplayName("Should return non-null object")
  void shouldReturnNonNullObject() {
    assertNotNull(this.applicationsJpaRepository);
  }

  @Nested
  @DisplayName("Test Method - createEnterpriseApplication")
  class TestCreateEnterpriseApplication {

    @Test
    @Tag(TestSuite.UNIT_TEST)
    @DisplayName("Should return null if input parameter is null")
    void shouldReturnNullIfInputParameterIsNull() {
      //Given
      EnterpriseApplication application = null;
      //When
      EnterpriseApplication response = applicationsJpaRepository.createEnterpriseApplication(application);
      //Then
      assertNull(response);
    }

    @Test
    @Tag(TestSuite.UNIT_TEST)
    @DisplayName("Should return not null if input parameter is valid")
    void shouldReturnNotNullIfInputParameterIsValid() {
      //Given
      Mockito
        .when(enterpriseApplicationEntityRepositoryMock.save(any(EnterpriseApplicationEntity.class)))
        .thenReturn(
          EnterpriseApplicationEntity.builder()
            .applicationId("APP_TEST_01")
            .applicationName("APP_TEST")
            .applicationUrl("http://test/")
            .applicationAuthenticationType("TEST")
            .upStreamApplication("UP_TEST")
            .downStreamApplication("DOWN_TEST")
            .build()
        );

      //When
      EnterpriseApplication response = applicationsJpaRepository.createEnterpriseApplication(new EnterpriseApplication());

      //Then
      assertNotNull(response);
      assertEquals("APP_TEST_01", response.getApplicationId());
      assertEquals("APP_TEST", response.getApplicationName());
      assertEquals("http://test/", response.getApplicationUrl());
      assertEquals("TEST", response.getApplicationAuthenticationType());
      assertEquals("UP_TEST", response.getUpStreamApplication());
      assertEquals("DOWN_TEST", response.getDownStreamApplication());
    }
  }

  @Nested
  @DisplayName("Test Method - deleteEnterpriseApplicationById")
  class TestDeleteEnterpriseApplicationById {

    @Test
    @Tag(TestSuite.UNIT_TEST)
    @DisplayName("Should return invalid if input parameter is null")
    void shouldReturnInvalidIfInputParameterIsNull() {
      //Given
      String applicationId = null;
      //When
      String response = applicationsJpaRepository.deleteEnterpriseApplicationById(applicationId);
      //Then
      assertNotNull(response);
      assertEquals(ApplicationConstants.INVALID, response);
    }

    @Test
    @Tag(TestSuite.UNIT_TEST)
    @DisplayName("Should return invalid if input parameter is empty")
    void shouldReturnInvalidIfInputParameterIsEmpty() {
      //Given
      String applicationId = "";
      //When
      String response = applicationsJpaRepository.deleteEnterpriseApplicationById(applicationId);
      //Then
      assertNotNull(response);
      assertEquals(ApplicationConstants.INVALID, response);
    }

    @Test
    @Tag(TestSuite.UNIT_TEST)
    @DisplayName("Should return not found if input parameter is not found")
    void shouldReturnNotFoundIfInputParameterIsNotFound() {
      //Given
      String applicationId = "APP_TEST_01";

      Mockito
        .when(enterpriseApplicationEntityRepositoryMock.findIdByApplicationId(applicationId))
        .thenReturn(null);

      //When
      String response = applicationsJpaRepository.deleteEnterpriseApplicationById(applicationId);

      //Then
      assertNotNull(response);
      assertEquals(ApplicationConstants.NOT_FOUND, response);
    }

    @Test
    @Tag(TestSuite.UNIT_TEST)
    @DisplayName("Should return Success if input parameter is valid")
    void shouldReturnSuccessIfInputParameterIsValid() {
      //Given
      String applicationId = "APP_TEST_01";

      Mockito
        .when(enterpriseApplicationEntityRepositoryMock.findIdByApplicationId(applicationId))
        .thenReturn(1001L);

      //When
      String response = applicationsJpaRepository.deleteEnterpriseApplicationById(applicationId);

      //Then
      assertNotNull(response);
      assertEquals(ApplicationConstants.SUCCESS, response);
    }
  }

  @Nested
  @DisplayName("Test Method - getEnterpriseApplications")
  class TestGetEnterpriseApplications {

    @Test
    @Tag(TestSuite.UNIT_TEST)
    @DisplayName("Should return non-null if input parameter is valid")
    void shouldReturnNonNullIfInputParameterIsValid() {
      //Given
      Mockito
        .when(enterpriseApplicationEntityRepositoryMock.findAll())
        .thenReturn(
          Collections.singletonList(
            EnterpriseApplicationEntity.builder()
              .applicationId("APP_TEST_01")
              .applicationName("APP_TEST")
              .applicationUrl("http://test/")
              .applicationAuthenticationType("TEST")
              .upStreamApplication("UP_TEST")
              .downStreamApplication("DOWN_TEST")
              .build()
          )
        );

      //When
      List<EnterpriseApplication> response = applicationsJpaRepository.getEnterpriseApplications(999, 999);

      //Then
      assertNotNull(response);
      assertNotNull(response);
      assertEquals("APP_TEST_01", response.get(0).getApplicationId());
      assertEquals("APP_TEST", response.get(0).getApplicationName());
      assertEquals("http://test/", response.get(0).getApplicationUrl());
      assertEquals("TEST", response.get(0).getApplicationAuthenticationType());
      assertEquals("UP_TEST", response.get(0).getUpStreamApplication());
      assertEquals("DOWN_TEST", response.get(0).getDownStreamApplication());
    }
  }

  @Nested
  @DisplayName("Test Method - getEnterpriseApplicationsById")
  class TestGetEnterpriseApplicationsById {

    @Test
    @Tag(TestSuite.UNIT_TEST)
    @DisplayName("Should return null if input parameter is null")
    void shouldReturnNullIfInputParameterIsNull() {
      //Given
      String applicationId = null;
      //When
      EnterpriseApplication response = applicationsJpaRepository.getEnterpriseApplicationsById(applicationId);
      //Then
      assertNull(response);
    }

    @Test
    @Tag(TestSuite.UNIT_TEST)
    @DisplayName("Should return null if input parameter is empty")
    void shouldReturnNullIfInputParameterIsEmpty() {
      //Given
      String applicationId = "";
      //When
      EnterpriseApplication response = applicationsJpaRepository.getEnterpriseApplicationsById(applicationId);
      //Then
      assertNull(response);
    }

    @Test
    @Tag(TestSuite.UNIT_TEST)
    @DisplayName("Should return non-null if input parameter is valid")
    void shouldReturnNonNullIfInputParameterIsValid() {
      //Given
      String applicationId = "APP_TEST_01";

      Mockito
        .when(enterpriseApplicationEntityRepositoryMock.findIdByApplicationId(applicationId))
        .thenReturn(1001L);

      Mockito
        .when(enterpriseApplicationEntityRepositoryMock.findById(1001L))
        .thenReturn(
          Optional.ofNullable(
            EnterpriseApplicationEntity.builder()
              .applicationId("APP_TEST_01")
              .applicationName("APP_TEST")
              .applicationUrl("http://test/")
              .applicationAuthenticationType("TEST")
              .upStreamApplication("UP_TEST")
              .downStreamApplication("DOWN_TEST")
              .build()
          )
        );

      //When
      EnterpriseApplication response = applicationsJpaRepository.getEnterpriseApplicationsById(applicationId);

      //Then
      assertNotNull(response);
      assertNotNull(response);
      assertEquals("APP_TEST_01", response.getApplicationId());
      assertEquals("APP_TEST", response.getApplicationName());
      assertEquals("http://test/", response.getApplicationUrl());
      assertEquals("TEST", response.getApplicationAuthenticationType());
      assertEquals("UP_TEST", response.getUpStreamApplication());
      assertEquals("DOWN_TEST", response.getDownStreamApplication());
    }
  }
}
