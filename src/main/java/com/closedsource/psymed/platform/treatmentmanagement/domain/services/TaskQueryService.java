package com.closedsource.psymed.platform.treatmentmanagement.domain.services;

import com.closedsource.psymed.platform.appointmentandadministration.domain.model.aggregates.Session;
import com.closedsource.psymed.platform.appointmentandadministration.domain.model.commands.CreateSessionCommand;
import com.closedsource.psymed.platform.treatmentmanagement.domain.model.entities.Task;
import com.closedsource.psymed.platform.treatmentmanagement.domain.model.queries.GetAllTaskQuery;
import com.closedsource.psymed.platform.treatmentmanagement.domain.model.queries.GetTaskByIdQuery;

import java.util.List;
import java.util.Optional;

public interface TaskQueryService {

    List<Task> handle(GetAllTaskQuery query);

    Optional<Task> handle(GetTaskByIdQuery query);
}
