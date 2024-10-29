package com.closedsource.psymed.platform.treatmentmanagement.infrastructure.persistance.jpa.repositories;


import com.closedsource.psymed.platform.treatmentmanagement.domain.model.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
