package com.closedsource.psymed.platform.appointmentandadministration.domain.model.commands;

import com.closedsource.psymed.platform.appointmentandadministration.domain.model.valueobjects.AppointmentDate;
import com.closedsource.psymed.platform.appointmentandadministration.domain.model.valueobjects.PatientId;
import com.closedsource.psymed.platform.appointmentandadministration.domain.model.valueobjects.ProfessionalId;
import com.closedsource.psymed.platform.appointmentandadministration.domain.model.valueobjects.SessionTime;

public record CreateSessionCommand(
        Long patientId,
        Long professionalId,
        AppointmentDate appointmentDate,
        SessionTime sessionTime) {

    public CreateSessionCommand {
        if (patientId == null) {
            throw new IllegalArgumentException("patientId cannot be null");
        }
        if (professionalId == null) {
            throw new IllegalArgumentException("professionalId cannot be null");
        }
        if (appointmentDate == null || !appointmentDate.isValidAppointment()) {
            throw new IllegalArgumentException("Invalid or null appointmentDate");
        }
        if (sessionTime == null || !sessionTime.isValidDuration()) {
            throw new IllegalArgumentException("Invalid or null sessionTime");
        }
    }
}
