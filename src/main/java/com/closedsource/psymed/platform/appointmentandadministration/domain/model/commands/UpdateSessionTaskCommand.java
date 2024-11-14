package com.closedsource.psymed.platform.appointmentandadministration.domain.model.commands;

import com.closedsource.psymed.platform.sessionnotes.domain.model.entities.Task;
import com.closedsource.psymed.platform.sessionnotes.interfaces.rest.resources.CreateNoteResource;
import com.closedsource.psymed.platform.sessionnotes.interfaces.rest.resources.CreateTaskResource;

public record UpdateSessionTaskCommand(Long id, int taskId, CreateTaskResource task) {
}
