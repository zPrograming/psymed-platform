package com.closedsource.psymed.platform.appointmentandadministration.domain.services;

import com.closedsource.psymed.platform.appointmentandadministration.domain.model.commands.CreateNoteCommand;
import com.closedsource.psymed.platform.appointmentandadministration.domain.model.entities.Note;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface NoteCommandService {

    Optional<Note> handle(CreateNoteCommand command);
}
