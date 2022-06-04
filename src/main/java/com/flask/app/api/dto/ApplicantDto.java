package com.flask.app.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApplicantDto {
    private Integer id;
    private String workEmailAddress;
    private String name;
    private String githubUser;
    private List<ProjectDto> projects;
}
