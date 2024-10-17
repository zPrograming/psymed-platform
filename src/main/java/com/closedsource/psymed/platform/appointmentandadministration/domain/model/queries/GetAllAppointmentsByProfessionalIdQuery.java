package com.closedsource.psymed.platform.appointmentandadministration.domain.model.queries;

/**
 * Query to get all appointments for a specific professional.
 * @param professionalId The unique ID of the professional.
 */
public record GetAllAppointmentsByProfessionalIdQuery(Long professionalId) {
    public GetAllAppointmentsByProfessionalIdQuery {
        if (professionalId == null || professionalId <= 0) {
            throw new IllegalArgumentException("professionalId cannot be null or less than or equal to 0");
        }
    }
}
