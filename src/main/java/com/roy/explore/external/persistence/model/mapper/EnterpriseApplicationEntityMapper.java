package com.roy.explore.external.persistence.model.mapper;

import com.roy.explore.external.persistence.model.EnterpriseApplicationEntity;
import com.roy.explore.openapi.model.EnterpriseApplication;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface EnterpriseApplicationEntityMapper {
    static EnterpriseApplicationEntityMapper get() {
        return ModelMapperInstance.INSTANCE;
    }

    final class ModelMapperInstance {
        private static final EnterpriseApplicationEntityMapper INSTANCE = Mappers.getMapper(EnterpriseApplicationEntityMapper.class);
        private ModelMapperInstance() {}
    }

    @Mapping(target = "applicationId", source = "entity.applicationId")
    @Mapping(target = "applicationName", source = "entity.applicationName")
    @Mapping(target = "applicationUrl", source = "entity.applicationUrl")
    @Mapping(target = "applicationAuthenticationType", source = "entity.applicationAuthenticationType")
    @Mapping(target = "upStreamApplication", source = "entity.upStreamApplication")
    @Mapping(target = "downStreamApplication", source = "entity.downStreamApplication")
    EnterpriseApplication toEnterpriseApplication(EnterpriseApplicationEntity entity);

    List<EnterpriseApplication> toEnterpriseApplicationList(List<EnterpriseApplicationEntity> entityList);

    @Mapping(target = "applicationId", source = "domain.applicationId")
    @Mapping(target = "applicationName", source = "domain.applicationName")
    @Mapping(target = "applicationUrl", source = "domain.applicationUrl")
    @Mapping(target = "applicationAuthenticationType", source = "domain.applicationAuthenticationType")
    @Mapping(target = "upStreamApplication", source = "domain.upStreamApplication")
    @Mapping(target = "downStreamApplication", source = "domain.downStreamApplication")
    EnterpriseApplicationEntity toEnterpriseApplicationEntity(EnterpriseApplication domain);
}
