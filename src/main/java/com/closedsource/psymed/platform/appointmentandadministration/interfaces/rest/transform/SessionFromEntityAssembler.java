package com.closedsource.psymed.platform.appointmentandadministration.interfaces.rest.transform;

import com.closedsource.psymed.platform.appointmentandadministration.domain.model.aggregates.Session;
import com.closedsource.psymed.platform.appointmentandadministration.interfaces.rest.resources.SessionResource;

public class SessionFromEntityAssembler {
    public static SessionResource toResourceFromEntity(Session entity){
        return new SessionResource(
                entity.getId(),
                entity.getPatientId().toString(),
                entity.getProfessionalId().toString(),
                entity.getAppointmentDate().toString(),
                entity.getSessionTime().getDurationInHours(),
                entity.getNoteId() != null ? entity.getNoteId().toString() : null
        );
    }
}
