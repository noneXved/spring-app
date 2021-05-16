package com.bajno.damian.project.repository;

import com.bajno.damian.project.model.TaskGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SQLTaskGroupRepository extends TaskGroupRepository, JpaRepository<TaskGroup, Long> {
    @Override
    @Query("from TaskGroup g join fetch g.tasks")
    List<TaskGroup> findAll();

}
