package com.closedsource.psymed.platform.appointmentandadministration.interfaces.rest.transform;

import com.closedsource.psymed.platform.appointmentandadministration.domain.model.commands.UpdateSessionNoteCommand;
import com.closedsource.psymed.platform.sessionnotes.domain.model.entities.Note;
import com.closedsource.psymed.platform.sessionnotes.interfaces.rest.resources.CreateNoteResource;

public class UpdateSessionNoteCommandFromResourceAssembler {

    public static UpdateSessionNoteCommand toCommandFromResource(Long sessionId, int taskId, CreateNoteResource note) {
        return new UpdateSessionNoteCommand(sessionId, taskId, note);
    }
}
