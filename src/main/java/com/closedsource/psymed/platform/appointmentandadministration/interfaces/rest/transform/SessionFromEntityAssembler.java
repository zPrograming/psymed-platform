package com.closedsource.psymed.platform.appointmentandadministration.interfaces.rest.transform;

import com.closedsource.psymed.platform.appointmentandadministration.domain.model.aggregates.Session;
import com.closedsource.psymed.platform.appointmentandadministration.interfaces.rest.resources.SessionResource;

/**
 * A utility class for converting a {@link Session} entity into a {@link SessionResource}.
 */
public class SessionFromEntityAssembler {

    /**
     * Converts a {@link Session} entity to a {@link SessionResource}.
     *
     * @param entity The {@link Session} entity containing the session details.
     * @return A {@link SessionResource} representing the session in a format suitable for REST responses.
     */
    public static SessionResource toResourceFromEntity(Session entity) {
        return new SessionResource(
                entity.getId(),
                entity.getPatientId().toString(),
                entity.getProfessionalId().toString(),
                entity.getAppointmentDate().toString(),
                entity.getSessionTime().getDurationInHours(),
                entity.getNotes(),
                entity.getTasks());
    }
}
