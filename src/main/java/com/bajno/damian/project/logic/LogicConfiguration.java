package com.bajno.damian.project.logic;

import com.bajno.damian.project.config.TaskConfigurationProperties;
import com.bajno.damian.project.repository.ProjectRepository;
import com.bajno.damian.project.repository.TaskGroupRepository;
import com.bajno.damian.project.repository.TaskRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LogicConfiguration {
    @Bean
    ProjectService projectService(
            final ProjectRepository repository,
            final TaskGroupRepository taskGroupRepository,
            final TaskGroupService taskGroupService,
            final TaskConfigurationProperties config
    ) {
        return new ProjectService(repository, taskGroupRepository, taskGroupService, config);
    }

    @Bean
    TaskGroupService taskGroupService(
            final TaskGroupRepository taskGroupRepository,
            final TaskRepository taskRepository
    ) {
        return new TaskGroupService(taskGroupRepository, taskRepository);
    }
}
