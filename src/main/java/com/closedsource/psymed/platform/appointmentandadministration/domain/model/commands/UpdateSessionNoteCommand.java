package com.closedsource.psymed.platform.appointmentandadministration.domain.model.commands;

import com.closedsource.psymed.platform.sessionnotes.domain.model.entities.Note;
import com.closedsource.psymed.platform.sessionnotes.interfaces.rest.resources.CreateNoteResource;
import com.closedsource.psymed.platform.sessionnotes.interfaces.rest.resources.NoteResource;

public record UpdateSessionNoteCommand(Long id, int noteId, CreateNoteResource note) {

}
