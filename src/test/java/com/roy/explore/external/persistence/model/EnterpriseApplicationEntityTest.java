package com.roy.explore.external.persistence.model;

import com.roy.explore.external.persistence.model.EnterpriseApplicationEntity;
import com.roy.explore.testUtil.TestSuite;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import javax.persistence.Column;
import javax.persistence.Table;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Unit Testing - EnterpriseApplicationEntity")
class EnterpriseApplicationEntityTest {

  @Test
  @Tag(TestSuite.UNIT_TEST)
  @DisplayName("Should return non-null object")
  void shouldAnnotatedWithTableOfNameTenterpriseApplication() {
    assertEquals("T_ENTERPRISE_APPLICATION", EnterpriseApplicationEntity.class.getAnnotation(Table.class).name());
  }

  @ParameterizedTest
  @CsvSource({"id,ID", "applicationId,APP_ID", "applicationName,APP_NAME", "applicationUrl,APP_URL",
    "applicationAuthenticationType,APP_AUTH_TYPE", "upStreamApplication,APP_UP_STREAM",
    "downStreamApplication,APP_DOWN_STREAM"})
  @Tag(TestSuite.UNIT_TEST)
  @DisplayName("Should return non-null object")
  void shouldMapWithRightColumnInTableTenterpriseApplication(String fieldName, String columnName)
    throws NoSuchFieldException{

    //When
    Column column = EnterpriseApplicationEntity.class.getDeclaredField(fieldName).getAnnotation(Column.class);
    //Then
    assertNotNull(column);
    assertEquals(columnName, column.name());
  }
}
