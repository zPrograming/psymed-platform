package com.closedsource.psymed.platform.appointmentandadministration.interfaces.rest.resources;

public record CreateSessionResource(
        String patientId,
        String professionalId,
        String appointmentDate,
        double sessionTime) {

    public CreateSessionResource {
        if (patientId == null || patientId.isBlank()) {
            throw new IllegalArgumentException("patientId cannot be null or empty");
        }
        if (professionalId == null || professionalId.isBlank()) {
            throw new IllegalArgumentException("professionalId cannot be null or empty");
        }
        if (appointmentDate == null || appointmentDate.isBlank()) {
            throw new IllegalArgumentException("appointmentDate cannot be null or empty");
        }
        if (sessionTime <= 0) {
            throw new IllegalArgumentException("sessionTime must be positive and greater than 0");
        }
    }
}
