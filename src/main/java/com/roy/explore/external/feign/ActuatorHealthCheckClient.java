package com.roy.explore.external.feign;

import com.roy.explore.external.feign.model.HealthResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "health-check-client", url = "http://localhost:8080")
public interface ActuatorHealthCheckClient {

  @GetMapping(value="/actuator/health", consumes= MediaType.APPLICATION_JSON_VALUE)
  HealthResponse getHealthStatus();
}
