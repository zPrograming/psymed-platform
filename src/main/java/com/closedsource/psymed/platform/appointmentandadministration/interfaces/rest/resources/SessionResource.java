package com.closedsource.psymed.platform.appointmentandadministration.interfaces.rest.resources;

public record SessionResource(
        Long id,
        String patientId,
        String professionalId,
        String appointmentDate,
        double sessionTime,
        String noteId) {
}
