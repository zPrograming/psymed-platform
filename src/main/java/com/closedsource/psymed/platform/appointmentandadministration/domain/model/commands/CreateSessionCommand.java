package com.closedsource.psymed.platform.appointmentandadministration.domain.model.commands;

import com.closedsource.psymed.platform.appointmentandadministration.domain.model.valueobjects.AppointmentDate;
import com.closedsource.psymed.platform.appointmentandadministration.domain.model.valueobjects.SessionTime;

/**
 * Command to create a new session between a patient and a professional.
 * Requires patient ID, professional ID, appointment date, and session time.
 */
public record CreateSessionCommand(String patientId, String professionalId,
                                   AppointmentDate appointmentDate, SessionTime sessionTime) {
    public CreateSessionCommand {
        if (patientId == null || patientId.isBlank()) {
            throw new IllegalArgumentException("patientId cannot be null or empty");
        }
        if (professionalId == null || professionalId.isBlank()) {
            throw new IllegalArgumentException("professionalId cannot be null or empty");
        }
        if (appointmentDate == null || !appointmentDate.isValidAppointment()) {
            throw new IllegalArgumentException("Invalid or null appointmentDate");
        }
        if (sessionTime == null || !sessionTime.isValidDuration()) {
            throw new IllegalArgumentException("Invalid or null sessionTime");
        }
    }
}
