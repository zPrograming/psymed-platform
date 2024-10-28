package com.closedsource.psymed.platform.appointmentandadministration.interfaces.rest;

import com.closedsource.psymed.platform.appointmentandadministration.domain.model.entities.Note;
import com.closedsource.psymed.platform.appointmentandadministration.domain.model.queries.GetAllNotesQuery;
import com.closedsource.psymed.platform.appointmentandadministration.domain.model.queries.GetNoteByIdQuery;
import com.closedsource.psymed.platform.appointmentandadministration.domain.services.NoteCommandService;
import com.closedsource.psymed.platform.appointmentandadministration.domain.services.NoteQueryService;
import com.closedsource.psymed.platform.appointmentandadministration.interfaces.rest.resources.CreateNoteResource;
import com.closedsource.psymed.platform.appointmentandadministration.interfaces.rest.resources.NoteResource;
import com.closedsource.psymed.platform.appointmentandadministration.interfaces.rest.transform.CreateNoteCommandFromResourceAssembler;
import com.closedsource.psymed.platform.appointmentandadministration.interfaces.rest.transform.NoteFromEntityAssembler;
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

@RestController
@RequestMapping(value = "/api/v1/notes", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Notes", description = "Operations related to notes explaining the description and symptoms")
public class NoteController {

    private final NoteCommandService noteCommandService;
    private final NoteQueryService noteQueryService;

    /**
     * Constructor to inject services required for note operations.
     *
     * @param noteCommandService The service responsible for handling note commands.
     */
    public NoteController(NoteCommandService noteCommandService, NoteQueryService noteQueryService) {
        this.noteCommandService = noteCommandService;
        this.noteQueryService = noteQueryService;
    }

    @Operation(summary = "Get all notes",
            description = "Retrieve all notes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Notes found"),})

    @GetMapping
    public ResponseEntity<List<Note>> getAllNotes() {

        List<Note> notes = noteQueryService.handle(new GetAllNotesQuery());

        if (notes.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(notes);
    }

    @Operation(summary = "Get note by ID",
            description = "Retrieve a Note using its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Note found"),
            @ApiResponse(responseCode = "404", description = "Note not found")
    })

    @GetMapping("/{id}")
    public ResponseEntity<NoteResource> getNoteById(@PathVariable Long id) {
        Optional<Note> note = noteQueryService.handle(new GetNoteByIdQuery(id));
        return note
                .map(s -> ResponseEntity.ok(NoteFromEntityAssembler.toResourceFromEntity(s)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    /**
     *  Creates a note with details about description and symptoms
     *
     * @param resource The resource containing note creation details.
     * @return A ResponseEntity containing the created note or a bad request status.
     */
    @Operation(summary = "Create a note",
            description = "Create a note with a description and symptom")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Note created"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping
    public ResponseEntity<NoteResource> createNote(@RequestBody CreateNoteResource resource) {

        Optional<Note> note = noteCommandService.handle(
                CreateNoteCommandFromResourceAssembler.toCommandFromResource(resource));
        return note
                .map(s -> new ResponseEntity<>(NoteFromEntityAssembler.toResourceFromEntity(s), CREATED))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

}
