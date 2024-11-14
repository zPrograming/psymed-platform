package com.closedsource.psymed.platform.appointmentandadministration.interfaces.rest.transform;

import com.closedsource.psymed.platform.appointmentandadministration.domain.model.commands.DeleteSessionNoteCommand;

public class DeleteSessionNoteCommandFromResourceAssembler {

    public static DeleteSessionNoteCommand toCommandFromResource(Long sessionId, int noteId) {

        return new DeleteSessionNoteCommand(sessionId, noteId);
    }
}
