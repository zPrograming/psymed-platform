package com.closedsource.psymed.platform.appointmentandadministration.interfaces.rest.resources;

/**
 * A resource class representing the data needed to create a new session.
 *
 * @param patientId      The ID of the patient. Cannot be null or blank.
 * @param professionalId The ID of the professional. Cannot be null or blank.
 * @param appointmentDate The date of the appointment. Cannot be null or blank.
 * @param sessionTime    The length of the session in hours. Must be greater than 0.
 *
 * @throws IllegalArgumentException if any of the parameters are invalid.
 */
public record CreateSessionResource(
        String patientId,
        String professionalId,
        String appointmentDate,
        double sessionTime) {

    /**
     * Constructor for CreateSessionResource.
     *
     * @param patientId      The ID of the patient. Cannot be null or blank.
     * @param professionalId The ID of the professional. Cannot be null or blank.
     * @param appointmentDate The date of the appointment. Cannot be null or blank.
     * @param sessionTime    The length of the session in hours. Must be greater than 0.
     *
     * @throws IllegalArgumentException if any of the provided arguments are invalid.
     */
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
