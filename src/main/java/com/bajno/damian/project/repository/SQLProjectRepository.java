package com.bajno.damian.project.repository;

import com.bajno.damian.project.model.Project;
import com.bajno.damian.project.model.TaskGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SQLProjectRepository extends ProjectRepository, JpaRepository<Project, Long> {
    @Override
    @Query("select distinct p from Project p join fetch p.steps")
    List<Project> findAll();
}
