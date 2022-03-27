package com.roy.explore.core.service;

import com.roy.explore.openapi.model.EnterpriseApplication;

import java.util.List;
import java.util.Optional;

public interface ApplicationsRepository {
    EnterpriseApplication createEnterpriseApplication(EnterpriseApplication application);

    String deleteEnterpriseApplicationById(String applicationId);

    List<EnterpriseApplication> getEnterpriseApplications(Integer limit, Integer offset);

    EnterpriseApplication getEnterpriseApplicationsById(String applicationId);
}
