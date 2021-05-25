package com.bajno.damian.project.logic;

import com.bajno.damian.project.config.TaskConfigurationProperties;
import com.bajno.damian.project.model.Project;
import com.bajno.damian.project.model.Task;
import com.bajno.damian.project.model.TaskGroup;
import com.bajno.damian.project.model.projection.GroupReadModel;
import com.bajno.damian.project.repository.ProjectRepository;
import com.bajno.damian.project.repository.TaskGroupRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService {
    private ProjectRepository projectRepository;
    private TaskGroupRepository taskGroupRepository;
    private TaskConfigurationProperties taskConfigurationProperties;

    public ProjectService(ProjectRepository projectRepository, TaskGroupRepository taskGroupRepository, TaskConfigurationProperties taskConfigurationProperties) {
        this.projectRepository = projectRepository;
        this.taskGroupRepository = taskGroupRepository;
        this.taskConfigurationProperties = taskConfigurationProperties;
    }

    public List<Project> readAll() {
        return projectRepository.findAll();
    }

    public Project save(Project toSave) {
        return projectRepository.save(toSave);
    }

    public GroupReadModel createGroup(LocalDateTime deadline, Long projectId) {
        if (!taskConfigurationProperties.getTemplate()
                                       .isAllowMultipleTask() && taskGroupRepository.existsByDoneIsFalseAndProject_Id(
                projectId)) {
            throw new IllegalStateException("Only one undone group from project is allowed");
    }

        TaskGroup result = projectRepository.findById(projectId).map(project -> {
            var targetGroup = new TaskGroup();
            targetGroup.setDescription(project.getDescription());
            targetGroup.setTasks(project.getSteps().stream()
                                        .map(step -> new Task(step.getDescription(),
                                                deadline.plusDays(step.getDaysToDeadline()))).collect(Collectors.toSet()));
            targetGroup.setProject(project);
            return taskGroupRepository.save(targetGroup);
        }).orElseThrow(() -> new IllegalArgumentException("Project with given id not found"));
        return new GroupReadModel(result);
    }

}
