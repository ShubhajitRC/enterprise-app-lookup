package com.roy.explore.delegate;

import com.roy.explore.core.service.ApplicationsService;
import com.roy.explore.core.util.ApplicationConstants;
import com.roy.explore.openapi.model.ApplicationStatus;
import com.roy.explore.openapi.model.EnterpriseApplication;
import com.roy.explore.testUtil.TestSuite;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@DisplayName("Unit Testing - V1ApiDelegateImpl")
@ExtendWith(MockitoExtension.class)
class V1ApiDelegateImplTest {

  private V1ApiDelegateImpl v1ApiDelegate;

  @Mock
  private ApplicationsService applicationsServiceMock;

  @BeforeEach
  @DisplayName("Setup")
  void setup() {
    this.v1ApiDelegate = new V1ApiDelegateImpl(applicationsServiceMock);
  }

  @Test
  @Tag(TestSuite.UNIT_TEST)
  @DisplayName("Should return non-null object")
  void shouldReturnNonNullObject() {
    assertNotNull(this.v1ApiDelegate);
  }

  @Nested
  @DisplayName("Test Method - createEnterpriseApplication")
  class TestCreateEnterpriseApplication{
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
        .when(applicationsServiceMock.createEnterpriseApplication(any(EnterpriseApplication.class)))
        .thenReturn(application);

      //When
      ResponseEntity<EnterpriseApplication> response = v1ApiDelegate.createEnterpriseApplication(application);

      //Then
      assertNotNull(response);
      assertEquals(HttpStatus.OK, response.getStatusCode());
      assertEquals("APP_TEST_01", Objects.requireNonNull(response.getBody()).getApplicationId());
      assertEquals("APP_TEST", Objects.requireNonNull(response.getBody()).getApplicationName());
      assertEquals("http://test/", Objects.requireNonNull(response.getBody()).getApplicationUrl());
      assertEquals("TEST", Objects.requireNonNull(response.getBody()).getApplicationAuthenticationType());
      assertEquals("UP_TEST", Objects.requireNonNull(response.getBody()).getUpStreamApplication());
      assertEquals("DOWN_TEST", Objects.requireNonNull(response.getBody()).getDownStreamApplication());
    }
  }

  @Nested
  @DisplayName("Test Method - deleteEnterpriseApplicationById")
  class TestDeleteEnterpriseApplicationById{
    @Test
    @Tag(TestSuite.UNIT_TEST)
    @DisplayName("Should return Success if input parameter is valid")
    void shouldReturnSuccessIfInputParameterIsValid() {
      //Given
      String applicationId = "APP_TEST_01";

      Mockito
        .when(applicationsServiceMock.deleteEnterpriseApplicationById(applicationId))
        .thenReturn(ApplicationConstants.SUCCESS);

      //When
      ResponseEntity<ApplicationStatus> response = v1ApiDelegate.deleteEnterpriseApplicationById(applicationId);

      //Then
      assertNotNull(response);
      assertEquals(HttpStatus.OK, response.getStatusCode());
      assertEquals(ApplicationConstants.SUCCESS, Objects.requireNonNull(response.getBody()).getStatus());
    }
  }

  @Nested
  @DisplayName("Test Method - getEnterpriseApplications")
  class TestGetEnterpriseApplications{
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
        .when(applicationsServiceMock.getEnterpriseApplications(999, 999))
        .thenReturn(Collections.singletonList(application));

      //When
      ResponseEntity<List<EnterpriseApplication>> response = v1ApiDelegate.getEnterpriseApplications(999,999);

      //Then
      assertNotNull(response);
      assertEquals(HttpStatus.OK, response.getStatusCode());
      assertEquals("APP_TEST_01", Objects.requireNonNull(response.getBody()).get(0).getApplicationId());
      assertEquals("APP_TEST", Objects.requireNonNull(response.getBody()).get(0).getApplicationName());
      assertEquals("http://test/", Objects.requireNonNull(response.getBody()).get(0).getApplicationUrl());
      assertEquals("TEST", Objects.requireNonNull(response.getBody()).get(0).getApplicationAuthenticationType());
      assertEquals("UP_TEST", Objects.requireNonNull(response.getBody()).get(0).getUpStreamApplication());
      assertEquals("DOWN_TEST", Objects.requireNonNull(response.getBody()).get(0).getDownStreamApplication());
    }
  }

  @Nested
  @DisplayName("Test Method - getEnterpriseApplicationsById")
  class TestGetEnterpriseApplicationsById{
    @Test
    @Tag(TestSuite.UNIT_TEST)
    @DisplayName("Should return not null if input parameter is valid")
    void shouldReturnNotNullIfInputParameterIsValid() {
      //Given
      String applicationId = "APP_TEST_01";

      EnterpriseApplication application = new EnterpriseApplication();
      application.setApplicationId("APP_TEST_01");
      application.setApplicationName("APP_TEST");
      application.setApplicationUrl("http://test/");
      application.setApplicationAuthenticationType("TEST");
      application.setUpStreamApplication("UP_TEST");
      application.setDownStreamApplication("DOWN_TEST");

      Mockito
        .when(applicationsServiceMock.getEnterpriseApplicationsById(applicationId))
        .thenReturn(application);

      //When
      ResponseEntity<EnterpriseApplication> response = v1ApiDelegate.getEnterpriseApplicationsById(applicationId);

      //Then
      assertNotNull(response);
      assertEquals(HttpStatus.OK, response.getStatusCode());
      assertEquals("APP_TEST_01", Objects.requireNonNull(response.getBody()).getApplicationId());
      assertEquals("APP_TEST", Objects.requireNonNull(response.getBody()).getApplicationName());
      assertEquals("http://test/", Objects.requireNonNull(response.getBody()).getApplicationUrl());
      assertEquals("TEST", Objects.requireNonNull(response.getBody()).getApplicationAuthenticationType());
      assertEquals("UP_TEST", Objects.requireNonNull(response.getBody()).getUpStreamApplication());
      assertEquals("DOWN_TEST", Objects.requireNonNull(response.getBody()).getDownStreamApplication());
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

      ApplicationStatus applicationStatus = new ApplicationStatus();
      applicationStatus.setStatus("UP");

      Mockito
        .when(applicationsServiceMock.getHealthCheck(applicationId))
        .thenReturn(applicationStatus);

      //When
      ResponseEntity<ApplicationStatus> response = v1ApiDelegate.getHealthCheck(applicationId);

      //Then
      assertNotNull(response);
      assertEquals("UP", Objects.requireNonNull(response.getBody()).getStatus());
    }
  }
}
