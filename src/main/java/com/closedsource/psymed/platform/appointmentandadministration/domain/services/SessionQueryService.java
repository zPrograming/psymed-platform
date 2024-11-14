package com.closedsource.psymed.platform.appointmentandadministration.domain.services;

import com.closedsource.psymed.platform.appointmentandadministration.domain.model.aggregates.Session;
import com.closedsource.psymed.platform.appointmentandadministration.domain.model.queries.*;
import com.closedsource.psymed.platform.sessionnotes.domain.model.entities.Note;
import com.closedsource.psymed.platform.sessionnotes.domain.model.entities.Task;

import java.util.List;
import java.util.Optional;

/**
 * Service to handle queries related to sessions.
 * This service handles fetching session data based on various criteria.
 */
public interface SessionQueryService {

    /**
     * Handle the query to get all sessions for a specific patient.
     *
     * @param query the query object containing the patient ID
     * @return a list of sessions for the patient
     */
    List<Session> handle(GetAllSessionsByPatientIdQuery query);

    /**
     * Handle the query to get all sessions for a specific professional.
     *
     * @param query the query object containing the professional ID
     * @return a list of sessions for the professional
     */
    List<Session> handle(GetAllSessionsByProfessionalIdQuery query);

    /**
     * Handle the query to get a specific session for a patient by session ID.
     *
     * @param query the query object containing the patient ID and session ID
     * @return an optional session, if found
     */
    Optional<Session> handle(GetSessionByPatientIdAndSessionIdQuery query);

    /**
     * Handle the query to get a specific session by its ID.
     *
     * @param query the query object containing the session ID
     * @return an optional session, if found
     */
    Optional<Session> handle(GetSessionByIdQuery query);

    /**
     * Handle the query to get all session notes by its ID.
     * @param query the query object containing the session ID
     * @return all session notes
     */
    List<Note> handle(GetAllSessionNotesByIdQuery query);

    /**
     * Handle the query to get all session tasks by its ID.
     * @param query the query object containing the session ID
     * @return all session tasks
     */
    List<Task> handle(GetAllSessionTasksByIdQuery query);


    /**
     * Handle the query to get a specific note by its note ID and its session ID.
     *
     * @param query the query object containing the session ID and notes ID.
     * @return an optional note, if found
     */
    Optional<Note> handle(GetSessionNoteByNoteIdAndSessionIdQuery query);

    /**
     * Handle the query to get a specific task by its task ID and its session ID.
     *
     * @param query the query object containing the session ID and task ID.
     * @return an optional note, if found
     */
    Optional<Task> handle(GetSessionTaskByTaskIdAndSessionIdQuery query);


    /**
     * Handle the query to get all sessions.
     *
     * @return a list of all sessions
     */
    List<Session> handle();
}
