package com.bajno.damian.project.repository;

import com.bajno.damian.project.model.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {
    List<Task> findAll();

    Page<Task> findAll(Pageable page);

    Optional<Task> findById(Long id);

    Task save(Task task);

    boolean existsById(Long id);

    boolean existsByDoneIsFalseAndGroupId(Long groupId);

    List<Task> findByDone(@Param("state") boolean done);

}
