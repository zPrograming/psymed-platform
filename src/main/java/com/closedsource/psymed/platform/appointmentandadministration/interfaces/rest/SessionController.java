package com.closedsource.psymed.platform.appointmentandadministration.interfaces.rest;

import com.closedsource.psymed.platform.appointmentandadministration.domain.model.aggregates.Session;
import com.closedsource.psymed.platform.appointmentandadministration.domain.model.commands.CreateSessionNoteCommand;
import com.closedsource.psymed.platform.appointmentandadministration.domain.model.commands.UpdateSessionTaskCommand;
import com.closedsource.psymed.platform.appointmentandadministration.domain.model.queries.*;
import com.closedsource.psymed.platform.appointmentandadministration.domain.services.SessionCommandService;
import com.closedsource.psymed.platform.appointmentandadministration.domain.services.SessionQueryService;
import com.closedsource.psymed.platform.appointmentandadministration.interfaces.rest.resources.CreateSessionResource;
import com.closedsource.psymed.platform.appointmentandadministration.interfaces.rest.resources.SessionResource;
import com.closedsource.psymed.platform.appointmentandadministration.interfaces.rest.transform.*;
import com.closedsource.psymed.platform.sessionnotes.domain.model.entities.Note;
import com.closedsource.psymed.platform.sessionnotes.domain.model.entities.Task;
import com.closedsource.psymed.platform.sessionnotes.domain.service.NoteCommandService;
import com.closedsource.psymed.platform.sessionnotes.domain.service.NoteQueryService;
import com.closedsource.psymed.platform.sessionnotes.interfaces.rest.resources.CreateNoteResource;
import com.closedsource.psymed.platform.sessionnotes.interfaces.rest.resources.CreateTaskResource;
import com.closedsource.psymed.platform.sessionnotes.interfaces.rest.resources.NoteResource;
import com.closedsource.psymed.platform.sessionnotes.interfaces.rest.resources.TaskResource;
import com.closedsource.psymed.platform.sessionnotes.interfaces.rest.transform.NoteFromEntityAssembler;
import com.closedsource.psymed.platform.sessionnotes.interfaces.rest.transform.TaskFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * REST controller responsible for handling session-related operations between patients and professionals.
 */
@RestController
@RequestMapping(value = "/api/v1/sessions", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Sessions", description = "Operations related to sessions between patients and professionals")
public class SessionController {

    private final SessionCommandService sessionCommandService;
    private final SessionQueryService sessionQueryService;

    private final NoteCommandService noteCommandService;
    private final NoteQueryService noteQueryService;


    /**
     * Constructor to inject services required for session operations.
     *
     * @param sessionCommandService The service responsible for handling session commands.
     * @param sessionQueryService   The service responsible for handling session queries.
     */
    public SessionController(SessionCommandService sessionCommandService,
                             SessionQueryService sessionQueryService,
                             NoteCommandService noteCommandService,
                             NoteQueryService noteQueryService) {
        this.sessionCommandService = sessionCommandService;
        this.sessionQueryService = sessionQueryService;
        this.noteCommandService = noteCommandService;
        this.noteQueryService = noteQueryService;
    }

    /**
     * Creates a session between a patient and a professional.
     *
     * @param resource The resource containing session creation details.
     * @return A ResponseEntity containing the created session or a bad request status.
     */
    @Operation(summary = "Create a session",
            description = "Create a session between a patient and a professional")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Session created"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping
    public ResponseEntity<SessionResource> createSession(@RequestBody CreateSessionResource resource) {
        Optional<Session> session = sessionCommandService.handle(
                CreateSessionCommandFromResourceAssembler.toCommandFromResource(resource));
        return session
                .map(s -> new ResponseEntity<>(SessionFromEntityAssembler.toResourceFromEntity(s), CREATED))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    /**
     * Retrieves all sessions.*
     * @return A ResponseEntity containing a list of sessions or a not found status.
     */
    @Operation(summary = "Get all sessions",
            description = "Retrieve all sessions")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sessions found"),
            @ApiResponse(responseCode = "404", description = "No sessions found")
    })
    @GetMapping
    public ResponseEntity<List<SessionResource>> getAllSessions() {
        var sessions = sessionQueryService.handle(); // Fixed method call
        if (sessions.isEmpty()) return ResponseEntity.notFound().build();
        var sessionResources = sessions.stream()
                .map(SessionFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(sessionResources);
    }

    /**
     * Retrieves a session by its ID.
     *
     * @param id The ID of the session.
     * @return A ResponseEntity containing the session or a not found status.
     */
    @Operation(summary = "Get session by ID",
            description = "Retrieve a session using its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Session found"),
            @ApiResponse(responseCode = "404", description = "Session not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<SessionResource> getSessionById(@PathVariable Long id) {
        Optional<Session> session = sessionQueryService.handle(new GetSessionByIdQuery(id));
        return session
                .map(s -> ResponseEntity.ok(SessionFromEntityAssembler.toResourceFromEntity(s)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }



    /**
     * Retrieves all notes associated with a specific session.
     *
     * @return ResponseEntity containing a list of notes if the session is found, or a Bad Request status if the session does not exist.
     */
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Notes found"),
            @ApiResponse(responseCode = "400", description = "Session not found")
    })
    @GetMapping("/{sessionId}/notes")
    public ResponseEntity<List<Note>> getNotesBySessionId(@PathVariable Long sessionId) {

        Optional<Session> sessionResource = sessionQueryService.handle(new GetSessionByIdQuery(sessionId));

        if (sessionResource.isEmpty()) return ResponseEntity.badRequest().build();

        List<Note> notes = sessionQueryService.handle(new GetAllSessionNotesByIdQuery(sessionId));

        return ResponseEntity.ok(notes);
    }

    /**
     * Retrieves all tasks associated with a specific session.
     *
     * @return ResponseEntity containing a list of tasks if the session is found, or a Bad Request status if the session does not exist.
     */
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tasks found"),
            @ApiResponse(responseCode = "400", description = "Session not found")
    })
    @GetMapping("/{sessionId}/tasks")
    public ResponseEntity<List<Task>> getTasksById(@PathVariable Long sessionId) {

        Optional<Session> sessionResource = sessionQueryService.handle(new GetSessionByIdQuery(sessionId));

        if (sessionResource.isEmpty()) return ResponseEntity.badRequest().build();

        List<Task> tasks = sessionQueryService.handle(new GetAllSessionTasksByIdQuery(sessionId));

        return ResponseEntity.ok(tasks);
    }

    /**
     * Retrieves a specific note by its ID for a given session.
     *
     * @param sessionId the ID of the session to which the note belongs.
     * @param noteId the ID of the note to be retrieved.
     * @return ResponseEntity containing the NoteResource if the session and note are found,
     *         or a Bad Request status if the session does not exist, or Not Found if the note does not exist.
     */
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Note found"),
            @ApiResponse(responseCode = "400", description = "Session not found"),
            @ApiResponse(responseCode = "404", description = "Note not found")
    })
    @GetMapping("/{sessionId}/note/{noteId}")
    public ResponseEntity<NoteResource> getNoteByNoteIdAndSessionId(@PathVariable Long sessionId, @PathVariable int noteId) {

        Optional<Session> sessionResource = sessionQueryService.handle(new GetSessionByIdQuery(sessionId));

        if (sessionResource.isEmpty()) return ResponseEntity.badRequest().build();

        Optional<Note> note = sessionQueryService.handle(new GetSessionNoteByNoteIdAndSessionIdQuery(sessionId, noteId));

        return note
                .map(n -> ResponseEntity.ok(NoteFromEntityAssembler.toResourceFromEntity(n)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Retrieves a specific task by its ID for a given session.
     *
     * @param sessionId the ID of the session to which the task belongs.
     * @param taskId the ID of the task to be retrieved.
     * @return ResponseEntity containing the TaskResource if the session and task are found,
     *         or a Bad Request status if the session does not exist, or Not Found if the task does not exist.
     */
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task found"),
            @ApiResponse(responseCode = "400", description = "Session not found"),
            @ApiResponse(responseCode = "404", description = "Task not found")
    })
    @GetMapping("/{sessionId}/task/{taskId}")
    public ResponseEntity<TaskResource> getTaskByTaskIdAndSessionId(@PathVariable Long sessionId, @PathVariable int taskId) {

        Optional<Session> sessionResource = sessionQueryService.handle(new GetSessionByIdQuery(sessionId));

        if (sessionResource.isEmpty()) return ResponseEntity.badRequest().build();

        Optional<Task> task = sessionQueryService.handle(new GetSessionTaskByTaskIdAndSessionIdQuery(sessionId, taskId));

        return task
                .map(t -> ResponseEntity.ok(TaskFromEntityAssembler.toResourceFromEntity(t)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    /**
     * Retrieves all sessions for a specific patient by their patient ID.
     *
     * @param patientId The ID of the patient.
     * @return A ResponseEntity containing the list of sessions or a not found status.
     */
    @Operation(summary = "Get all sessions for a patient",
            description = "Retrieve all sessions for a given patient")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sessions found"),
            @ApiResponse(responseCode = "404", description = "No sessions found for the patient")
    })
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<SessionResource>> getAllSessionsByPatientId(@PathVariable String patientId) {
        var query = new GetAllSessionsByPatientIdQuery(patientId);
        var sessions = sessionQueryService.handle(query);
        if (sessions.isEmpty()) return ResponseEntity.notFound().build();
        var sessionResources = sessions.stream()
                .map(SessionFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(sessionResources);
    }


    /**
     * Creates a new note for a specific session.
     *
     * @param sessionId The ID of the session.
     * @param resource The note details to be created.
     * @return A ResponseEntity containing the created note resource or a bad request status if the session does not exist.
     */
    @Operation(summary = "Create a new note for a session",
            description = "Creates a new note associated with the given session ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Note created successfully"),
            @ApiResponse(responseCode = "400", description = "Session not found")
    })
    @PostMapping("/{sessionId}/note")
    public ResponseEntity<NoteResource> createNote(@PathVariable Long sessionId, @RequestBody CreateNoteResource resource) {
        var session = sessionQueryService.handle(new GetSessionByIdQuery(sessionId));
        if (session.isEmpty()) return ResponseEntity.badRequest().build();

        var createSessionNoteCommand = CreateSessionNoteCommandFromResourceAssembler.toCommandFromResource(sessionId, resource);
        var note = sessionCommandService.handle(createSessionNoteCommand);

        return note
                .map(n -> ResponseEntity.ok(NoteFromEntityAssembler.toResourceFromEntity(n)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Deletes a note from a specific session.
     *
     * @param sessionId The ID of the session.
     * @param noteId The ID of the note to be deleted.
     * @return A ResponseEntity indicating the deletion status or a bad request status if the session does not exist.
     */
    @Operation(summary = "Delete a note from a session",
            description = "Deletes a note associated with the given session ID and note ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Note deleted successfully"),
            @ApiResponse(responseCode = "400", description = "Session not found")
    })
    @DeleteMapping("/{sessionId}/note/{noteId}")
    public ResponseEntity<?> deleteNote(@PathVariable Long sessionId, @PathVariable int noteId) {
        Optional<Session> session = sessionQueryService.handle(new GetSessionByIdQuery(sessionId));
        if (session.isEmpty()) return ResponseEntity.badRequest().build();

        var deleteSessionNoteCommand = DeleteSessionNoteCommandFromResourceAssembler.toCommandFromResource(sessionId, noteId);
        sessionCommandService.handle(deleteSessionNoteCommand);

        return ResponseEntity.ok("Deleted note resource from session id");
    }

    /**
     * Updates an existing note for a specific session.
     *
     * @param sessionId The ID of the session.
     * @param noteId The ID of the note to be updated.
     * @param resource The updated note details.
     * @return A ResponseEntity containing the updated note resource or a bad request status if the session does not exist.
     */
    @Operation(summary = "Update a note for a session",
            description = "Updates an existing note associated with the given session ID and note ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Note updated successfully"),
            @ApiResponse(responseCode = "400", description = "Session not found")
    })
    @PutMapping("/{sessionId}/note/{noteId}")
    public ResponseEntity<NoteResource> updateNote(@PathVariable Long sessionId, @PathVariable int noteId, @RequestBody CreateNoteResource resource) {
        Optional<Session> session = sessionQueryService.handle(new GetSessionByIdQuery(sessionId));
        if (session.isEmpty()) return ResponseEntity.badRequest().build();

        var updateSessionNoteCommand = UpdateSessionNoteCommandFromResourceAssembler.toCommandFromResource(sessionId, noteId, resource);
        var note = sessionCommandService.handle(updateSessionNoteCommand);

        return note
                .map(n -> ResponseEntity.ok(NoteFromEntityAssembler.toResourceFromEntity(n)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Creates a new task for a specific session.
     *
     * @param sessionId The ID of the session.
     * @param resource The task details to be created.
     * @return A ResponseEntity containing the created task resource or a bad request status if the session does not exist.
     */
    @Operation(summary = "Create a new task for a session",
            description = "Creates a new task associated with the given session ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task created successfully"),
            @ApiResponse(responseCode = "400", description = "Session not found")
    })
    @PostMapping("/{sessionId}/task")
    public ResponseEntity<TaskResource> createTask(@PathVariable Long sessionId, @RequestBody CreateTaskResource resource) {
        var session = sessionQueryService.handle(new GetSessionByIdQuery(sessionId));
        if (session.isEmpty()) return ResponseEntity.badRequest().build();

        var createSessionTaskCommand = CreateSessionTaskCommandFromResourceAssembler.toCommandFromResource(sessionId, resource);
        var task = sessionCommandService.handle(createSessionTaskCommand);

        return task
                .map(t -> ResponseEntity.ok(TaskFromEntityAssembler.toResourceFromEntity(t)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Deletes a task from a specific session.
     *
     * @param sessionId The ID of the session.
     * @param taskId The ID of the task to be deleted.
     * @return A ResponseEntity indicating the deletion status or a bad request status if the session does not exist.
     */
    @Operation(summary = "Delete a task from a session",
            description = "Deletes a task associated with the given session ID and task ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task deleted successfully"),
            @ApiResponse(responseCode = "400", description = "Session not found")
    })
    @DeleteMapping("/{sessionId}/task/{taskId}")
    public ResponseEntity<?> deleteTask(@PathVariable Long sessionId, @PathVariable int taskId) {
        Optional<Session> session = sessionQueryService.handle(new GetSessionByIdQuery(sessionId));
        if (session.isEmpty()) return ResponseEntity.badRequest().build();

        var deleteSessionTaskCommand = DeleteSessionTaskFromResourceAssembler.toCommandFromResource(sessionId, taskId);
        sessionCommandService.handle(deleteSessionTaskCommand);

        return ResponseEntity.ok("Deleted task resource from session id");
    }

    /**
     * Updates an existing task for a specific session.
     *
     * @param sessionId The ID of the session.
     * @param taskId The ID of the task to be updated.
     * @param resource The updated task details.
     * @return A ResponseEntity containing the updated task resource or a bad request status if the session does not exist.
     */
    @Operation(summary = "Update a task for a session",
            description = "Updates an existing task associated with the given session ID and task ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task updated successfully"),
            @ApiResponse(responseCode = "400", description = "Session not found")
    })
    @PutMapping("/{sessionId}/task/{taskId}")
    public ResponseEntity<TaskResource> updateTask(@PathVariable Long sessionId, @PathVariable int taskId, @RequestBody CreateTaskResource resource) {
        Optional<Session> session = sessionQueryService.handle(new GetSessionByIdQuery(sessionId));
        if (session.isEmpty()) return ResponseEntity.badRequest().build();

        var updateSessionTaskCommand = UpdateSessionTaskCommandFromResourceAssembler.toCommandFromResource(sessionId, taskId, resource);
        var task = sessionCommandService.handle(updateSessionTaskCommand);

        return task
                .map(t -> ResponseEntity.ok(TaskFromEntityAssembler.toResourceFromEntity(t)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    /**
     * Retrieves all sessions for a specific professional by their professional ID.
     *
     * @param professionalId The ID of the professional.
     * @return A ResponseEntity containing the list of sessions or a not found status.
     */
    @Operation(summary = "Get all sessions for a professional",
            description = "Retrieve all sessions for a given professional")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sessions found"),
            @ApiResponse(responseCode = "404", description = "No sessions found for the professional")
    })
    @GetMapping("/professional/{professionalId}")
    public ResponseEntity<List<SessionResource>> getAllSessionsByProfessionalId(@PathVariable String professionalId) {
        var query = new GetAllSessionsByProfessionalIdQuery(professionalId);
        var sessions = sessionQueryService.handle(query);
        if (sessions.isEmpty()) return ResponseEntity.notFound().build();
        var sessionResources = sessions.stream()
                .map(SessionFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(sessionResources);
    }

    /**
     * Retrieves a specific session for a patient using the session ID.
     *
     * @param patientId The ID of the patient.
     * @param id        The ID of the session.
     * @return A ResponseEntity containing the session or a not found status.
     */
    @Operation(summary = "Get session by patient ID and session ID",
            description = "Retrieve a specific session for a patient using the session ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Session found"),
            @ApiResponse(responseCode = "404", description = "Session not found")
    })
    @GetMapping("/patient/{patientId}/session/{id}")
    public ResponseEntity<SessionResource> getSessionByPatientIdAndId(
            @PathVariable String patientId, @PathVariable Long id) {

        var query = new GetSessionByPatientIdAndSessionIdQuery(patientId, id);
        Optional<Session> session = sessionQueryService.handle(query);
        return session
                .map(s -> ResponseEntity.ok(SessionFromEntityAssembler.toResourceFromEntity(s)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
