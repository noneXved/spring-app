package com.bajno.damian.project.model.projection;

import com.bajno.damian.project.model.Task;
import com.bajno.damian.project.model.TaskGroup;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
public class GroupReadModel {
    private String description;
    private LocalDateTime deadline;

    private Set<GroupTaskReadModel> tasks;

    public GroupReadModel(TaskGroup source) {
        description = source.getDescription();
        source.getTasks().stream()
              .map(Task::getDeadline)
              .max(LocalDateTime::compareTo)
              .ifPresent(date -> deadline = date);
        tasks = source.getTasks().stream()
                      .map(GroupTaskReadModel::new)
                      .collect(Collectors.toSet());
    }
}
