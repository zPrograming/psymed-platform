package com.closedsource.psymed.platform.appointmentandadministration.interfaces.rest.transform;

import com.closedsource.psymed.platform.appointmentandadministration.domain.model.commands.UpdateSessionTaskCommand;
import com.closedsource.psymed.platform.sessionnotes.domain.model.entities.Task;
import com.closedsource.psymed.platform.sessionnotes.interfaces.rest.resources.CreateTaskResource;

public class UpdateSessionTaskCommandFromResourceAssembler {

    public static UpdateSessionTaskCommand toCommandFromResource(Long sessionId, int taskId, CreateTaskResource task) {
        return new UpdateSessionTaskCommand(sessionId, taskId, task);
    }
}
