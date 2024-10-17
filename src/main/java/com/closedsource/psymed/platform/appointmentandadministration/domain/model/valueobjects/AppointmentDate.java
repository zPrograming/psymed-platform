package com.closedsource.psymed.platform.appointmentandadministration.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
import java.util.Date;

/**
 * Value Object representing the appointment date and time.
 * This object encapsulates the logic to manage and validate the appointment date.
 */
@Embeddable
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
     * Updates the appointment date with a new date and time.
     *
     * @param newAppointmentDate The new appointment date and time.
     *                           It must be a future date.
     * @throws IllegalArgumentException if the new date is in the past.
     */
    public void updateAppointmentDate(Date newAppointmentDate) {
        if (newAppointmentDate.before(new Date())) {
            throw new IllegalArgumentException("New appointment date must be in the future.");
        }
        this.appointmentDate = newAppointmentDate;
    }

    /**
     * Returns the appointment date and time.
     *
     * @return The appointment date and time.
     */
    public Date getAppointmentDate() {
        return appointmentDate;
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
