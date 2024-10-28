package com.closedsource.psymed.platform.appointmentandadministration.interfaces.rest.transform;

import com.closedsource.psymed.platform.appointmentandadministration.domain.model.commands.CreateNoteCommand;
import com.closedsource.psymed.platform.appointmentandadministration.interfaces.rest.resources.CreateNoteResource;

public class CreateNoteCommandFromResourceAssembler {

    public static CreateNoteCommand toCommandFromResource(CreateNoteResource resource) {
        return new CreateNoteCommand(
                resource.description(),
                resource.symptom());
    }
}
