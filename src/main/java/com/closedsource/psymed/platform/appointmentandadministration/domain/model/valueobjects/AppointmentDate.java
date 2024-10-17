package com.closedsource.psymed.platform.appointmentandadministration.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
import lombok.Getter;

import java.util.Date;

/**
 * Value Object representing the appointment date and time.
 * This object encapsulates the logic to manage and validate the appointment date.
 */
@Embeddable
@Getter
public class AppointmentDate {
    private Date appointmentDate;

    /**
     * Default constructor that initializes the appointment date to the current date and time.
     * Useful when the appointment date is not known at the moment of creation.
     */
    public AppointmentDate() {
        this.appointmentDate = new Date();  // Default value: current date and time
    }

    /**
     * Constructor with validation to set the appointment date and time.
     *
     * @param appointmentDate The date and time of the appointment.
     *                        It must be a future date.
     * @throws IllegalArgumentException if the date is in the past.
     */
    public AppointmentDate(Date appointmentDate) {
        if (appointmentDate.before(new Date())) {
            throw new IllegalArgumentException("Appointment date must be in the future.");
        }
        this.appointmentDate = appointmentDate;
    }
    /**
     * Checks if the appointment date is valid.
     * The date must be in the future.
     *
     * @return true if the appointment date is in the future, false otherwise.
     */
    public boolean isValidAppointment() {
        return appointmentDate.after(new Date());
    }
}
