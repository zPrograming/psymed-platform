package com.closedsource.psymed.platform.appointmentandadministration.domain.model.commands;

import com.closedsource.psymed.platform.sessionnotes.domain.model.entities.Note;

public record CreateSessionNoteCommand(Long id, Note note) {
}
