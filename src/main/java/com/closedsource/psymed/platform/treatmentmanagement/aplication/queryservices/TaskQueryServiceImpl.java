package com.closedsource.psymed.platform.treatmentmanagement.aplication.queryservices;

import com.closedsource.psymed.platform.treatmentmanagement.domain.model.entities.Task;
import com.closedsource.psymed.platform.treatmentmanagement.domain.model.queries.GetAllTaskQuery;
import com.closedsource.psymed.platform.treatmentmanagement.domain.model.queries.GetTaskByIdQuery;
import com.closedsource.psymed.platform.treatmentmanagement.domain.services.TaskCommandService;
import com.closedsource.psymed.platform.treatmentmanagement.domain.services.TaskQueryService;
import com.closedsource.psymed.platform.treatmentmanagement.infrastructure.persistance.jpa.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskQueryServiceImpl implements TaskQueryService {

    private final TaskRepository taskRepository;

    public TaskQueryServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> handle(GetAllTaskQuery query) {

        return taskRepository.findAll();
    }

    @Override
    public Optional<Task> handle(GetTaskByIdQuery query) {

        return taskRepository.findById(query.id());
    }
}
