package com.flask.app.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.flask.app.enums.EmploymentCapacityEnum;
import com.flask.app.enums.EmploymentModeEnum;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProjectDto {
    private Integer id;
    private String name;
    @Enumerated(EnumType.STRING)
    private EmploymentModeEnum employmentMode;
    @Enumerated(EnumType.STRING)
    private EmploymentCapacityEnum employmentCapacity;
    private Integer durationInDays;
    private Integer startYear;
    private String role;
    private Integer teamSize;
    private String repositoryLink;
    private String liveUrl;
    @JsonIgnore
    private ApplicantDto applicant;
}
