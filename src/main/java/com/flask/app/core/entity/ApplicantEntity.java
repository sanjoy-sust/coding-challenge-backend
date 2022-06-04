package com.flask.app.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "applicant")
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApplicantEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "work_email_address", unique = true, nullable = false)
    private String workEmailAddress;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "github_user", nullable = false)
    private String githubUser;
    @OneToMany(mappedBy="applicant", cascade = CascadeType.ALL)
    private List<ProjectEntity> projects = new ArrayList<>();

}
