package com.closedsource.psymed.platform.appointmentandadministration.interfaces.rest.transform;

import com.closedsource.psymed.platform.appointmentandadministration.domain.model.commands.CreateSessionNoteCommand;
import com.closedsource.psymed.platform.sessionnotes.domain.model.entities.Note;
import com.closedsource.psymed.platform.sessionnotes.interfaces.rest.resources.CreateNoteResource;

public class CreateSessionNoteCommandFromResourceAssembler {

    public static CreateSessionNoteCommand toCommandFromResource(Long sessionId, CreateNoteResource resource) {

        return new CreateSessionNoteCommand(sessionId,
                new Note(
                        resource.description(),
                        resource.symptom()));
    }
}
