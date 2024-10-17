package com.closedsource.psymed.platform.appointmentandadministration.domain.model.queries;

/**
 * Query to get all appointments for a specific patient.
 * @param patientId The unique ID of the patient.
 */
public record GetAllAppointmentsByPatientIdQuery(Long patientId) {
    public GetAllAppointmentsByPatientIdQuery {
        if (patientId == null || patientId <= 0) {
            throw new IllegalArgumentException("patientId cannot be null or less than or equal to 0");
        }
    }
}
