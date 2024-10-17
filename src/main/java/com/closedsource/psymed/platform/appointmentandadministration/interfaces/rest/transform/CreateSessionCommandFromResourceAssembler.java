package com.closedsource.psymed.platform.appointmentandadministration.interfaces.rest.transform;

import com.closedsource.psymed.platform.appointmentandadministration.domain.model.commands.CreateSessionCommand;
import com.closedsource.psymed.platform.appointmentandadministration.interfaces.rest.resources.CreateSessionResource;
import com.closedsource.psymed.platform.appointmentandadministration.domain.model.valueobjects.PatientId;
import com.closedsource.psymed.platform.appointmentandadministration.domain.model.valueobjects.ProfessionalId;
import com.closedsource.psymed.platform.appointmentandadministration.domain.model.valueobjects.AppointmentDate;
import com.closedsource.psymed.platform.appointmentandadministration.domain.model.valueobjects.SessionTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateSessionCommandFromResourceAssembler {

    public static CreateSessionCommand toCommandFromResource(CreateSessionResource resource) throws ParseException {
        Long patientId = Long.parseLong(resource.patientId()); // Convert String to Long
        Long professionalId = Long.parseLong(resource.professionalId()); // Convert String to Long

        // Convert String to Date (assuming the date is in "yyyy-MM-dd" format)
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date appointmentDate = formatter.parse(resource.appointmentDate());

        return new CreateSessionCommand(
                new PatientId(patientId),
                new ProfessionalId(professionalId),
                new AppointmentDate(appointmentDate),
                new SessionTime(resource.sessionTime())
        );
    }
}
