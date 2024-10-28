package com.closedsource.psymed.platform.appointmentandadministration.interfaces.rest.resources;

/**
 * A resource class representing the details of a session in the Psymed platform.
 *
 * @param id              The unique identifier of the session.
 * @param patientId       The ID of the patient associated with the session.
 * @param professionalId  The ID of the professional conducting the session.
 * @param appointmentDate The date of the appointment in string format.
 * @param sessionTime     The duration of the session in hours.
 * @param noteId          The ID of the note associated with the session (if any). Can be null.
 */
public record SessionResource(
        Long id,
        String patientId,
        String professionalId,
        String appointmentDate,
        double sessionTime,
        String noteId) {
}
