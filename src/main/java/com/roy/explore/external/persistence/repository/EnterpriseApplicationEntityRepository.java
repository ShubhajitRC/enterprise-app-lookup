package com.roy.explore.external.persistence.repository;

import com.roy.explore.external.persistence.model.EnterpriseApplicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EnterpriseApplicationEntityRepository extends JpaRepository<EnterpriseApplicationEntity, Long> {
  @Query("select u.id from EnterpriseApplicationEntity u where u.applicationId = ?1")
  Long findIdByApplicationId (String applicationId);
}
