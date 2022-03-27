package com.roy.explore.external.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "T_ENTERPRISE_APPLICATION")
public class EnterpriseApplicationEntity {

    @Id
    @Column(name="ID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name = "APP_ID")
    private String applicationId;

    @Column(name = "APP_NAME")
    private String applicationName;

    @Column(name = "APP_URL")
    private String applicationUrl;

    @Column(name = "APP_AUTH_TYPE")
    private String applicationAuthenticationType;

    @Column(name = "APP_UP_STREAM")
    private String upStreamApplication;

    @Column(name = "APP_DOWN_STREAM")
    private String downStreamApplication;
}
