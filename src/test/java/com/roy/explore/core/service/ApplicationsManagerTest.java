package com.roy.explore.core.service;


import com.roy.explore.core.util.ApplicationConstants;
import com.roy.explore.external.feign.ActuatorHealthCheckClient;
import com.roy.explore.external.feign.model.HealthResponse;
import com.roy.explore.openapi.model.ApplicationStatus;
import com.roy.explore.openapi.model.EnterpriseApplication;
import com.roy.explore.testUtil.TestSuite;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@DisplayName("Unit Testing - ApplicationsManager")
@ExtendWith(MockitoExtension.class)
class ApplicationsManagerTest {

  private ApplicationsManager applicationsManager;

  @Mock
  private ApplicationsRepository applicationsRepositoryMock;

  @Mock
  private ActuatorHealthCheckClient actuatorHealthCheckClientMock;

  @BeforeEach
  @DisplayName("Setup")
  void setup() {
    this.applicationsManager = new ApplicationsManager(applicationsRepositoryMock, actuatorHealthCheckClientMock);
  }

  @Test
  @Tag(TestSuite.UNIT_TEST)
  @DisplayName("Should return non-null object")
  void shouldReturnNonNullObject() {
    assertNotNull(this.applicationsManager);
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
      EnterpriseApplication response = applicationsManager.createEnterpriseApplication(application);
      //Then
      assertNull(response);
    }

    @Test
    @Tag(TestSuite.UNIT_TEST)
    @DisplayName("Should return not null if input parameter is valid")
    void shouldReturnNotNullIfInputParameterIsValid() {
      //Given
      EnterpriseApplication application = new EnterpriseApplication();
      application.setApplicationId("APP_TEST_01");
      application.setApplicationName("APP_TEST");
      application.setApplicationUrl("http://test/");
      application.setApplicationAuthenticationType("TEST");
      application.setUpStreamApplication("UP_TEST");
      application.setDownStreamApplication("DOWN_TEST");

      Mockito
        .when(applicationsRepositoryMock.createEnterpriseApplication(any(EnterpriseApplication.class)))
        .thenReturn(application);

      //When
      EnterpriseApplication response = applicationsManager.createEnterpriseApplication(application);

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
      String response = applicationsManager.deleteEnterpriseApplicationById(applicationId);
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
      String response = applicationsManager.deleteEnterpriseApplicationById(applicationId);
      //Then
      assertNotNull(response);
      assertEquals(ApplicationConstants.INVALID, response);
    }

    @Test
    @Tag(TestSuite.UNIT_TEST)
    @DisplayName("Should return Success if input parameter is valid")
    void shouldReturnSuccessIfInputParameterIsValid() {
      //Given
      String applicationId = "APP_TEST_01";

      Mockito
        .when(applicationsRepositoryMock.deleteEnterpriseApplicationById(applicationId))
        .thenReturn(ApplicationConstants.SUCCESS);

      //When
      String response = applicationsManager.deleteEnterpriseApplicationById(applicationId);

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
        .when(applicationsRepositoryMock.getEnterpriseApplications(999,999))
        .thenReturn(
          Collections.singletonList(
            new EnterpriseApplication()
              .applicationId("APP_TEST_01")
              .applicationName("APP_TEST")
              .applicationUrl("http://test/")
              .applicationAuthenticationType("TEST")
              .upStreamApplication("UP_TEST")
              .downStreamApplication("DOWN_TEST")
          )
        );

      //When
      List<EnterpriseApplication> response = applicationsManager.getEnterpriseApplications(999,999);

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
      EnterpriseApplication response = applicationsManager.getEnterpriseApplicationsById(applicationId);
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
      EnterpriseApplication response = applicationsManager.getEnterpriseApplicationsById(applicationId);
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
        .when(applicationsRepositoryMock.getEnterpriseApplicationsById(applicationId))
        .thenReturn(
          new EnterpriseApplication()
            .applicationId("APP_TEST_01")
            .applicationName("APP_TEST")
            .applicationUrl("http://test/")
            .applicationAuthenticationType("TEST")
            .upStreamApplication("UP_TEST")
            .downStreamApplication("DOWN_TEST")
        );

      //When
      EnterpriseApplication response = applicationsManager.getEnterpriseApplicationsById(applicationId);

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

  @Nested
  @DisplayName("Test Method - getHealthCheck")
  class TestGetHealthCheck{

    @Test
    @Tag(TestSuite.UNIT_TEST)
    @DisplayName("Should return status if client application is up")
    void shouldReturnStatusIfClientApplicationIsUp() {
      //Given
      String applicationId = "APP_TEST_01";

      HealthResponse healthResponse = new HealthResponse();
      healthResponse.setStatus("UP");

      Mockito
        .when(actuatorHealthCheckClientMock.getHealthStatus())
        .thenReturn(healthResponse);

      //When
      ApplicationStatus response = applicationsManager.getHealthCheck(applicationId);

      //Then
      assertNotNull(response);
      assertEquals("UP", response.getStatus());
    }

    @Test
    @Tag(TestSuite.UNIT_TEST)
    @DisplayName("Should return status if client application is down")
    void shouldReturnStatusIfClientApplicationIsDown() {
      //Given
      String applicationId = "APP_TEST_01";

      Mockito
        .when(actuatorHealthCheckClientMock.getHealthStatus())
        .thenThrow(new RuntimeException());

      //When
      ApplicationStatus response = applicationsManager.getHealthCheck(applicationId);

      //Then
      assertNotNull(response);
      assertEquals("DOWN", response.getStatus());
    }
  }
}
