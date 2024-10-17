package com.closedsource.psymed.platform.appointmentandadministration.domain.model.queries;

/**
 * Query to get a specific appointment for a patient by appointment ID.
 * @param patientId The ID of the patient.
 * @param appointmentId The ID of the appointment.
 */
public record GetSessionByPatientIdAndSessionIdQuery(Long patientId, Long appointmentId) {
    public GetSessionByPatientIdAndSessionIdQuery {
        if (patientId == null || patientId <= 0) {
            throw new IllegalArgumentException("patientId cannot be null or less than or equal to 0");
        }
        if (appointmentId == null || appointmentId <= 0) {
            throw new IllegalArgumentException("appointmentId cannot be null or less than or equal to 0");
        }
    }
}
