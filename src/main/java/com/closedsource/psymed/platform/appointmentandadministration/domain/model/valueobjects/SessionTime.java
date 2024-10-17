package com.closedsource.psymed.platform.appointmentandadministration.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

/**
 * Value Object representing the session duration in hours.
 * This object encapsulates the logic to manage the duration of sessions.
 */
@Embeddable
public class SessionTime {
    private double durationInHours;

    /**
     * Default constructor that initializes the session duration to 0.0 hours.
     * Useful when the session duration is not known at the moment of creation.
     */
    public SessionTime() {
        this.durationInHours = 0.0;  // Default value
    }

    /**
     * Constructor with validation to set the session duration in hours.
     *
     * @param durationInHours The duration of the session in hours.
     *                        It must be positive and greater than 0.
     * @throws IllegalArgumentException if the duration is less than or equal to 0.
     */
    public SessionTime(double durationInHours) {
        if (durationInHours <= 0) {
            throw new IllegalArgumentException("Session time must be positive and greater than 0");
        }
        this.durationInHours = durationInHours;
    }

    /**
     * Updates the session time with a new duration.
     *
     * @param newDurationInHours The new session duration in hours.
     *                           It must be positive and greater than 0.
     * @throws IllegalArgumentException if the new duration is less than or equal to 0.
     */
    public void updateSessionTime(double newDurationInHours) {
        if (newDurationInHours <= 0) {
            throw new IllegalArgumentException("New session time must be positive and greater than 0");
        }
        this.durationInHours = newDurationInHours;
    }

    /**
     * Returns the session duration in hours.
     *
     * @return The session duration in hours.
     */
    public double getDurationInHours() {
        return durationInHours;
    }

    /**
     * Checks if the session duration is valid based on business rules.
     * For example, the minimum valid duration could be 0.5 hours.
     *
     * @return true if the duration is greater than or equal to 0.5 hours, false otherwise.
     */
    public boolean isValidDuration() {
        return durationInHours >= 0.5;
    }
}
