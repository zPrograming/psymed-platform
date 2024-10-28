package com.closedsource.psymed.platform.appointmentandadministration.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

/**
 * Value Object representing the Note ID.
 * Encapsulates logic to validate the note identifier.
 */
@Embeddable
public record NoteId(Long noteId) {

    /**
     * Compact constructor with validation to ensure a valid Note ID.
     *
     * @param noteId The unique identifier of the note.
     *               It must be a positive number.
     * @throws IllegalArgumentException if the ID is null or less than 0.
     */
    public NoteId {
        if (noteId == null || noteId < 0) {
            throw new IllegalArgumentException("Note ID cannot be null or negative.");
        }
    }

    /**
     * Default constructor.
     * Initializes the Note ID to 0.
     */
    public NoteId() {
        this(0L);
    }
}
