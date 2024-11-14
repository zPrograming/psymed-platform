package com.closedsource.psymed.platform.appointmentandadministration.interfaces.rest.transform;

import com.closedsource.psymed.platform.appointmentandadministration.domain.model.commands.CreateSessionTaskCommand;
import com.closedsource.psymed.platform.sessionnotes.domain.model.entities.Task;
import com.closedsource.psymed.platform.sessionnotes.interfaces.rest.resources.CreateTaskResource;

public class CreateSessionTaskCommandFromResourceAssembler {

    public static CreateSessionTaskCommand toCommandFromResource(Long sessionId, CreateTaskResource resource) {

        return new CreateSessionTaskCommand(sessionId,
                new Task(
                        resource.title(),
                        resource.description(),
                        resource.completionStatus()));
    }

}
