package com.flask.app.core.service;

import com.flask.app.core.entity.ProjectEntity;
import com.flask.app.core.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Transactional
    public ProjectEntity saveProject(ProjectEntity projectEntity){
        ProjectEntity savedEntity = projectRepository.save(projectEntity);
        return  savedEntity;
    }

    @Transactional
    public List<ProjectEntity> saveProject(List<ProjectEntity> projectEntities){
        List<ProjectEntity> savedEntityList = projectRepository.saveAll(projectEntities);
        return  savedEntityList;
    }
}
