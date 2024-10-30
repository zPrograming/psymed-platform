package com.closedsource.psymed.platform.appointmentandadministration.interfaces.rest;

import com.closedsource.psymed.platform.appointmentandadministration.domain.model.aggregates.Session;
import com.closedsource.psymed.platform.appointmentandadministration.domain.model.commands.UpdateSessionNoteCommand;
import com.closedsource.psymed.platform.appointmentandadministration.domain.model.entities.Note;
import com.closedsource.psymed.platform.appointmentandadministration.domain.model.queries.*;
import com.closedsource.psymed.platform.appointmentandadministration.domain.services.NoteQueryService;
import com.closedsource.psymed.platform.appointmentandadministration.domain.services.SessionCommandService;
import com.closedsource.psymed.platform.appointmentandadministration.domain.services.SessionQueryService;
import com.closedsource.psymed.platform.appointmentandadministration.interfaces.rest.resources.CreateSessionResource;
import com.closedsource.psymed.platform.appointmentandadministration.interfaces.rest.resources.NoteResource;
import com.closedsource.psymed.platform.appointmentandadministration.interfaces.rest.resources.SessionResource;
import com.closedsource.psymed.platform.appointmentandadministration.interfaces.rest.transform.CreateSessionCommandFromResourceAssembler;
import com.closedsource.psymed.platform.appointmentandadministration.interfaces.rest.transform.SessionFromEntityAssembler;
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

    private final NoteQueryService noteQueryService;


    /**
     * Constructor to inject services required for session operations.
     *
     * @param sessionCommandService The service responsible for handling session commands.
     * @param sessionQueryService   The service responsible for handling session queries.
     */
    public SessionController(SessionCommandService sessionCommandService,
                             SessionQueryService sessionQueryService, NoteQueryService noteQueryService) {
        this.sessionCommandService = sessionCommandService;
        this.sessionQueryService = sessionQueryService;
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
     * Retrieves all sessions.
     *
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

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Note set"),
            @ApiResponse(responseCode = "400", description = "Session not found"),
            @ApiResponse(responseCode = "404", description = "Note not found")
    })
    @PostMapping("/{sessionId}/setNote/{noteId}")
    public ResponseEntity<SessionResource> setNote(@PathVariable Long noteId, @PathVariable Long sessionId) {

        Optional<Note> note = noteQueryService.handle(new GetNoteByIdQuery(noteId));
        if (note.isEmpty()) return ResponseEntity.notFound().build();

        Optional<Session> sessionData = sessionQueryService.handle(new GetSessionByIdQuery(sessionId));

        if (sessionData.isEmpty()) return ResponseEntity.badRequest().build();

        Optional<Session> sessionResources =  sessionCommandService.handle(new UpdateSessionNoteCommand(sessionId, note.get()));

        return sessionResources
                .map(s -> ResponseEntity.ok(SessionFromEntityAssembler.toResourceFromEntity(s)))
                .orElseGet(() -> ResponseEntity.internalServerError().build());
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Note set"),
            @ApiResponse(responseCode = "400", description = "Session not found"),
            @ApiResponse(responseCode = "404", description = "Note not found")
    })
    @GetMapping("/{sessionId}/note")
    public ResponseEntity<SessionResource> getNoteBySessionId(@PathVariable Long sessionId) {

        Optional<Session> sessionResource = sessionQueryService.handle(new GetSessionByIdQuery(sessionId));

        if (sessionResource.isEmpty()) return ResponseEntity.badRequest().build();

        return sessionResource
                .map(s -> ResponseEntity.ok(SessionFromEntityAssembler.toResourceFromEntity(s)))
                .orElseGet(() -> ResponseEntity.internalServerError().build());
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
