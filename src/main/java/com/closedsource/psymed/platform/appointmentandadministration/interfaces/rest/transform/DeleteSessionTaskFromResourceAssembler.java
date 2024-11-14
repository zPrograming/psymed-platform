package com.closedsource.psymed.platform.appointmentandadministration.interfaces.rest.transform;

import com.closedsource.psymed.platform.appointmentandadministration.domain.model.commands.DeleteSessionTaskCommand;

public class DeleteSessionTaskFromResourceAssembler {

    public static DeleteSessionTaskCommand toCommandFromResource(Long sessionId, int taskId) {
        return new DeleteSessionTaskCommand(sessionId, taskId);
    }
}
