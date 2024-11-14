package com.closedsource.psymed.platform.appointmentandadministration.domain.model.commands;

import com.closedsource.psymed.platform.sessionnotes.domain.model.entities.Task;

public record CreateSessionTaskCommand(Long id, Task task) {
}
