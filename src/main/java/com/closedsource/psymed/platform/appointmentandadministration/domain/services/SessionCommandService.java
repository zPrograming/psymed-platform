package com.closedsource.psymed.platform.appointmentandadministration.domain.services;

import com.closedsource.psymed.platform.appointmentandadministration.domain.model.aggregates.Session;
import com.closedsource.psymed.platform.appointmentandadministration.domain.model.commands.*;
import com.closedsource.psymed.platform.sessionnotes.domain.model.entities.Note;
import com.closedsource.psymed.platform.sessionnotes.domain.model.entities.Task;

import java.util.Optional;

/**
 * Service to handle commands related to appointments.
 * @summary
 * This service is responsible for handling commands related to creating, updating, and deleting appointments.
 */
public interface SessionCommandService {

    /**
     * Handle the command to create an appointment.
     *
     * @param command the command to create an appointment
     * @return the created appointment
     * @throws IllegalArgumentException if the command is invalid
     * @see CreateSessionCommand
     */
    Optional<Session> handle(CreateSessionCommand command);

    /**
     * Handles the creation of a new note within a session.
     *
     * @param command the command containing the details required to create the note.
     * @return an Optional containing the newly created note if successful, or an empty Optional if the creation fails.
     */
    Optional<Note> handle(CreateSessionNoteCommand command);


    /**
     * Handles the creation of a new task within a session.
     *
     * @param command the command containing the details required to create the task.
     * @return an Optional containing the newly created task if successful, or an empty Optional if the creation fails.
     */
    Optional<Task> handle(CreateSessionTaskCommand command);

    /**
     * Handles updating an existing note within a session.
     *
     * @param command the command containing the updated details for the note.
     * @return an Optional containing the updated task if successful, or an empty Optional if the update fails.
     */
    Optional<Note> handle(UpdateSessionNoteCommand command);

    /**
     * Handles updating an existing task within a session.
     *
     * @param command the command containing the updated details for the task.
     * @return an Optional containing the updated task if successful, or an empty Optional if the update fails.
     */
    Optional<Task> handle(UpdateSessionTaskCommand command);

    /**
     * Handles the deletion of an existing note within a session.
     *
     * @param command the command containing the details required to delete the note.
     * This method does not return a value, but throws an exception if the deletion fails.
     */
    void handle(DeleteSessionNoteCommand command);

    /**
     * Handles the deletion of an existing task within a session.
     *
     * @param command the command containing the details required to delete the task.
     * This method does not return a value, but throws an exception if the deletion fails.
     */
    void handle(DeleteSessionTaskCommand command);


}
