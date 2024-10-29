package com.closedsource.psymed.platform.treatmentmanagement.domain.services;

import com.closedsource.psymed.platform.treatmentmanagement.domain.model.commands.CreateTaskCommand;
import com.closedsource.psymed.platform.treatmentmanagement.domain.model.entities.Task;
import com.closedsource.psymed.platform.treatmentmanagement.domain.model.queries.GetAllTaskQuery;
import com.closedsource.psymed.platform.treatmentmanagement.domain.model.queries.GetTaskByIdQuery;

import java.util.Optional;

public interface TaskCommandService {

    Optional<Task> handle(CreateTaskCommand command);

}
