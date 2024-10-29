package com.closedsource.psymed.platform.treatmentmanagement.aplication.commandservices;

import com.closedsource.psymed.platform.appointmentandadministration.infrastructure.persistence.jpa.repositories.NoteRepository;
import com.closedsource.psymed.platform.treatmentmanagement.domain.model.commands.CreateTaskCommand;
import com.closedsource.psymed.platform.treatmentmanagement.domain.model.entities.Task;
import com.closedsource.psymed.platform.treatmentmanagement.domain.services.TaskCommandService;
import com.closedsource.psymed.platform.treatmentmanagement.infrastructure.persistance.jpa.repositories.TaskRepository;
import org.springframework.boot.autoconfigure.web.client.RestTemplateAutoConfiguration;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskCommandServiceImpl implements TaskCommandService {

    private final TaskRepository taskRepository;

    public TaskCommandServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Optional<Task> handle(CreateTaskCommand command) {

        var task = new Task(command);
        var taskResult = taskRepository.save(task);

        return Optional.of(taskResult);
    }
}
