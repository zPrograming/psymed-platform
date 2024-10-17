package com.closedsource.psymed.platform.appointmentandadministration.interfaces.rest.transform;

import com.closedsource.psymed.platform.appointmentandadministration.domain.model.commands.CreateSessionCommand;
import com.closedsource.psymed.platform.appointmentandadministration.interfaces.rest.resources.CreateSessionResource;
import com.closedsource.psymed.platform.appointmentandadministration.domain.model.valueobjects.PatientId;
import com.closedsource.psymed.platform.appointmentandadministration.domain.model.valueobjects.ProfessionalId;
import com.closedsource.psymed.platform.appointmentandadministration.domain.model.valueobjects.AppointmentDate;
import com.closedsource.psymed.platform.appointmentandadministration.domain.model.valueobjects.SessionTime;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A utility class that converts a {@link CreateSessionResource} object into a {@link CreateSessionCommand} object.
 */
public class CreateSessionCommandFromResourceAssembler {

    private static final String DATE_FORMAT = "yyyy-MM-dd";

    /**
     * Converts the {@link CreateSessionResource} to a {@link CreateSessionCommand}.
     *
     * @param resource The {@link CreateSessionResource} containing the session details.
     * @return A {@link CreateSessionCommand} object populated with the converted data from the resource.
     * @throws IllegalArgumentException If the date format is invalid or any conversion fails.
     */
    public static CreateSessionCommand toCommandFromResource(CreateSessionResource resource) {
        Long patientId = Long.parseLong(resource.patientId()); // Convert String to Long
        Long professionalId = Long.parseLong(resource.professionalId()); // Convert String to Long

        // Parse the appointmentDate String into Date, handle the exception internally
        Date appointmentDate = parseDate(resource.appointmentDate());

        return new CreateSessionCommand(
                new PatientId(patientId),
                new ProfessionalId(professionalId),
                new AppointmentDate(appointmentDate),
                new SessionTime(resource.sessionTime())
        );
    }

    /**
     * Parses the appointment date string into a {@link Date} object using the specified date format.
     *
     * @param dateString The appointment date in string format.
     * @return A {@link Date} object representing the appointment date.
     * @throws IllegalArgumentException If the provided date string is not in the expected format.
     */
    private static Date parseDate(String dateString) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
            return formatter.parse(dateString); // Parses the date in the specified format
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid date format, expected format is " + DATE_FORMAT);
        }
    }
}
