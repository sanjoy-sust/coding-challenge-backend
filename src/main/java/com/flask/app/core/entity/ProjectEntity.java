package com.flask.app.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.flask.app.enums.EmploymentCapacityEnum;
import com.flask.app.enums.EmploymentModeEnum;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "project")
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString(exclude = {"applicant"})
public class ProjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Enumerated(EnumType.STRING)
    @Column(name = "employment_mode", nullable = false)
    private EmploymentModeEnum employmentMode;
    @Enumerated(EnumType.STRING)
    @Column(name = "employment_capacity", nullable = false)
    private EmploymentCapacityEnum employmentCapacity;
    @Column(name = "duration_in_days", nullable = false)
    private Integer durationInDays;
    @Column(name = "start_year", nullable = false)
    private Integer startYear;
    @Column(name = "role", nullable = false)
    private String role;
    @Column(name = "team_size", nullable = false)
    private Integer teamSize;
    @Column(name = "repository_link")
    private String repositoryLink;
    @Column(name = "live_url")
    private String liveUrl;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name="applicant_id", nullable = false)
    private ApplicantEntity applicant;
}
