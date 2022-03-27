package com.roy.explore.core.service;

import com.roy.explore.core.util.ApplicationConstants;
import com.roy.explore.external.feign.ActuatorHealthCheckClient;
import com.roy.explore.openapi.model.ApplicationStatus;
import com.roy.explore.openapi.model.EnterpriseApplication;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ApplicationsManager implements ApplicationsService {

    private final ApplicationsRepository applicationsRepository;

    private final ActuatorHealthCheckClient actuatorHealthCheckClient;

    @Override
    public EnterpriseApplication createEnterpriseApplication(EnterpriseApplication application) {
        if(Objects.isNull(application)){
            return null;
        }
        return applicationsRepository.createEnterpriseApplication(application);
    }

    @Override
    public String deleteEnterpriseApplicationById(String applicationId) {
        if(Objects.isNull(applicationId) || StringUtils.isBlank(applicationId)){
            return ApplicationConstants.INVALID;
        }
        return applicationsRepository.deleteEnterpriseApplicationById(applicationId);
    }

    @Override
    public List<EnterpriseApplication> getEnterpriseApplications(Integer limit, Integer offset) {
        return applicationsRepository.getEnterpriseApplications(limit, offset);
    }

    @Override
    public EnterpriseApplication getEnterpriseApplicationsById(String applicationId) {
        if(Objects.isNull(applicationId) || StringUtils.isBlank(applicationId)){
            return null;
        }
        return applicationsRepository.getEnterpriseApplicationsById(applicationId);
    }

    @Override
    public ApplicationStatus getHealthCheck(String applicationId) {
        //TODO retrieve the application url using applicationId and check the health status

        ApplicationStatus applicationStatus = new ApplicationStatus();
        try {
            applicationStatus.setStatus(actuatorHealthCheckClient.getHealthStatus().getStatus());
        } catch (RuntimeException e) {
            applicationStatus.setStatus("DOWN");
        }

        return applicationStatus;
    }
}
