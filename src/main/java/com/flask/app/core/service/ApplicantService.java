package com.flask.app.core.service;

import com.flask.app.core.entity.ApplicantEntity;
import com.flask.app.core.entity.ProjectEntity;
import com.flask.app.core.repository.ApplicantRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ApplicantService {
    private final ApplicantRepository applicantRepository;
    private final ProjectService projectService;

    public ApplicantService(ApplicantRepository applicantRepository,
                            ProjectService projectService) {
        this.applicantRepository = applicantRepository;
        this.projectService = projectService;
    }

    @Transactional
    public ApplicantEntity addApplicant(ApplicantEntity applicantEntity) {

        if (Objects.isNull(applicantEntity)){
            return null;
        }
        Optional<ApplicantEntity> entityByEmail = applicantRepository.findByWorkEmailAddress(applicantEntity.getWorkEmailAddress());
        entityByEmail.ifPresent(applicantRepository::delete);
        applicantRepository.flush();
        List<ProjectEntity> projectEntities = applicantEntity.getProjects();
        projectEntities.stream().peek(entity-> entity.setApplicant(applicantEntity));
        for (ProjectEntity projectEntity : projectEntities) {
            projectEntity.setApplicant(applicantEntity);
        }
        return applicantRepository.save(applicantEntity);
    }


    public List<ApplicantEntity> getAllApplicants() {
        List<ApplicantEntity> applicantEntityList = applicantRepository.findAll();
        return applicantEntityList;
    }

    public ApplicantEntity getApplicantsById(Integer id) {
        Optional<ApplicantEntity> applicantEntity = applicantRepository.findById(id);
        return applicantEntity.orElse(null);
    }
    public ApplicantEntity getApplicantsByEmail(String email) {
        Optional<ApplicantEntity> applicantEntity = applicantRepository.findByWorkEmailAddress(email);
        return applicantEntity.orElse(null);
    }

    public ApplicantEntity updateApplicant(ApplicantEntity applicantEntity) {
        ApplicantEntity savedEntity = applicantRepository.save(applicantEntity);

        List<ProjectEntity> projectEntities = applicantEntity.getProjects();

        for (ProjectEntity projectEntity : projectEntities) {
            projectEntity.setApplicant(savedEntity);
        }
        projectService.saveProject(projectEntities);
        return savedEntity;
    }
}
