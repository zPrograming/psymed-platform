package com.closedsource.psymed.platform.appointmentandadministration.interfaces.rest.transform;

import com.closedsource.psymed.platform.appointmentandadministration.domain.model.entities.Note;
import com.closedsource.psymed.platform.appointmentandadministration.interfaces.rest.resources.NoteResource;

public class NoteFromEntityAssembler {

    public static NoteResource toResourceFromEntity(Note entity) {
        return new NoteResource(
                entity.getId(),
                entity.getDescription(),
                entity.getSymptoms());
    }
}
