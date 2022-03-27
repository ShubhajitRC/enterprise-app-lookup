package com.roy.explore.core.service;

import com.roy.explore.openapi.model.ApplicationStatus;
import com.roy.explore.openapi.model.EnterpriseApplication;

import java.util.List;

public interface ApplicationsService {
    EnterpriseApplication createEnterpriseApplication(EnterpriseApplication application);

    String deleteEnterpriseApplicationById(String applicationId);

    List<EnterpriseApplication> getEnterpriseApplications(Integer limit, Integer offset);

    EnterpriseApplication getEnterpriseApplicationsById(String applicationId);

    ApplicationStatus getHealthCheck(String applicationId);
}
