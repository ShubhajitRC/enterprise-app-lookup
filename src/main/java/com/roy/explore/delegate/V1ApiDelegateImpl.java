package com.roy.explore.delegate;


import com.roy.explore.core.service.ApplicationsService;
import com.roy.explore.core.util.ApplicationConstants;
import com.roy.explore.openapi.api.V1ApiDelegate;
import com.roy.explore.openapi.model.ApplicationStatus;
import com.roy.explore.openapi.model.EnterpriseApplication;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class V1ApiDelegateImpl implements V1ApiDelegate {

  private final ApplicationsService applicationsService;

  @Override
  public ResponseEntity<EnterpriseApplication> createEnterpriseApplication(EnterpriseApplication application) {
    return new ResponseEntity<>(applicationsService.createEnterpriseApplication(application), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<ApplicationStatus> deleteEnterpriseApplicationById(String applicationId) {
    String result = applicationsService.deleteEnterpriseApplicationById(applicationId);
    ApplicationStatus applicationStatus = new ApplicationStatus();

    if(result.equals(ApplicationConstants.SUCCESS)) {
      return new ResponseEntity<>(applicationStatus.status(result), HttpStatus.OK);
    } else if (result.equals(ApplicationConstants.NOT_FOUND)) {
      return new ResponseEntity<>(applicationStatus.status(result), HttpStatus.NOT_ACCEPTABLE);
    } else {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @Override
  public ResponseEntity<List<EnterpriseApplication>> getEnterpriseApplications(Integer limit, Integer offset) {
    return new ResponseEntity<>(applicationsService.getEnterpriseApplications(limit, offset), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<EnterpriseApplication> getEnterpriseApplicationsById(String applicationId) {
    return new ResponseEntity<>(applicationsService.getEnterpriseApplicationsById(applicationId), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<ApplicationStatus> getHealthCheck(String applicationId) {
    return new ResponseEntity<>(applicationsService.getHealthCheck(applicationId), HttpStatus.OK);
  }
}
