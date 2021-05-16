package com.bajno.damian.project.repository;


import com.bajno.damian.project.model.TaskGroup;

import java.util.List;
import java.util.Optional;

public interface TaskGroupRepository {
    List<TaskGroup> findAll();

    Optional<TaskGroup> findById(Long id);

    TaskGroup save(TaskGroup entity);
}
