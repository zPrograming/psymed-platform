package com.closedsource.psymed.platform.appointmentandadministration.domain.model.queries;

/**
 * Query to get all appointments for a specific patient.
 * @param patientId The unique ID of the patient.
 */
public record GetAllSessionsByPatientIdQuery(Long patientId) {
    public GetAllSessionsByPatientIdQuery {
        if (patientId == null || patientId <= 0) {
            throw new IllegalArgumentException("patientId cannot be null or less than or equal to 0");
        }
    }
}
