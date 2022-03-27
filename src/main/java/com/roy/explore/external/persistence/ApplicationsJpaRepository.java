package com.roy.explore.external.persistence;

import com.roy.explore.core.service.ApplicationsRepository;
import com.roy.explore.core.util.ApplicationConstants;
import com.roy.explore.external.persistence.model.EnterpriseApplicationEntity;
import com.roy.explore.external.persistence.model.mapper.EnterpriseApplicationEntityMapper;
import com.roy.explore.external.persistence.repository.EnterpriseApplicationEntityRepository;
import com.roy.explore.openapi.model.EnterpriseApplication;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ApplicationsJpaRepository implements ApplicationsRepository {

  private final EnterpriseApplicationEntityRepository enterpriseApplicationEntityRepository;

  @Override
  public EnterpriseApplication createEnterpriseApplication(EnterpriseApplication application) {
    return EnterpriseApplicationEntityMapper.get().toEnterpriseApplication(
      enterpriseApplicationEntityRepository.save(
        EnterpriseApplicationEntityMapper.get().toEnterpriseApplicationEntity(application)
      )
    );
  }

  @Override
  public String deleteEnterpriseApplicationById(String applicationId) {
    if (Objects.isNull(applicationId) || StringUtils.isBlank(applicationId)) {
      return ApplicationConstants.INVALID;
    }

    Long e = enterpriseApplicationEntityRepository.findIdByApplicationId(applicationId);
    if(e != null) {
      enterpriseApplicationEntityRepository.deleteById(e);
      return ApplicationConstants.SUCCESS;
    } else {
      return ApplicationConstants.NOT_FOUND;
    }
  }

  @Override
  public List<EnterpriseApplication> getEnterpriseApplications(Integer limit, Integer offset) {
    return enterpriseApplicationEntityRepository.findAll()
      .stream()
      .map(entity -> EnterpriseApplicationEntityMapper.get().toEnterpriseApplication(entity))
      .collect(Collectors.toList());
  }

  @Override
  public EnterpriseApplication getEnterpriseApplicationsById(String applicationId) {
    if (Objects.isNull(applicationId) || StringUtils.isBlank(applicationId)) {
      return null;
    }
    Long e = enterpriseApplicationEntityRepository.findIdByApplicationId(applicationId);
    Optional<EnterpriseApplicationEntity> entity = enterpriseApplicationEntityRepository.findById(e);

    return entity.map(
      enterpriseApplicationEntity ->
        EnterpriseApplicationEntityMapper.get().toEnterpriseApplication(enterpriseApplicationEntity)
    ).orElse(null);
  }
}
