package com.bajno.damian.project.logic;

import com.bajno.damian.project.config.TaskConfigurationProperties;
import com.bajno.damian.project.model.TaskGroup;
import com.bajno.damian.project.model.projection.GroupReadModel;
import com.bajno.damian.project.model.projection.GroupWriteModel;
import com.bajno.damian.project.repository.TaskGroupRepository;
import com.bajno.damian.project.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskGroupService {
    private TaskGroupRepository repository;
    private TaskRepository taskRepository;

    public TaskGroupService(TaskGroupRepository repository, TaskRepository taskRepository) {
        this.repository = repository;
        this.taskRepository = taskRepository;
    }

    public GroupReadModel createGroup(GroupWriteModel source) {
        TaskGroup result = repository.save(source.toGroup());
        return new GroupReadModel(result);
    }


    public List<GroupReadModel> readAll() {
        return repository.findAll()
                         .stream()
                         .map(GroupReadModel::new)
                         .collect(Collectors.toList());
    }

    public void toggleGroup(Long groupId) {
        if (taskRepository.existsByDoneIsFalseAndGroup_Id(groupId)) {
            throw new IllegalStateException("Group has undone tasks. Done all the tasks first");
        }

        TaskGroup result = repository.findById(groupId)
                                     .orElseThrow(() -> new IllegalArgumentException("TaskGroup with given id not found"));
        result.setDone(!result.isDone());

    }
}
