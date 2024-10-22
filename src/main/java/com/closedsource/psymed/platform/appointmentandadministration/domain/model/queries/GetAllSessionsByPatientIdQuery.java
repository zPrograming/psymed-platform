package com.closedsource.psymed.platform.appointmentandadministration.domain.model.queries;

/**
 * Query to get all appointments for a specific patient.
 * @param patientId The unique ID of the patient.
 */
public record GetAllSessionsByPatientIdQuery(String patientId) {
    public GetAllSessionsByPatientIdQuery {
        if (patientId == null || patientId.isBlank()) {
            throw new IllegalArgumentException("patientId cannot be null or empty");
        }
    }
}