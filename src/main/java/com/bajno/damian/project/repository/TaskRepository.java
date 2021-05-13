package com.bajno.damian.project.repository;

import com.bajno.damian.project.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Long, Task> {


}
