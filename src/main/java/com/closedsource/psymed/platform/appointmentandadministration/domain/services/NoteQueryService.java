package com.closedsource.psymed.platform.appointmentandadministration.domain.services;

import com.closedsource.psymed.platform.appointmentandadministration.domain.model.entities.Note;
import com.closedsource.psymed.platform.appointmentandadministration.domain.model.queries.GetAllNotesQuery;
import com.closedsource.psymed.platform.appointmentandadministration.domain.model.queries.GetNoteByIdQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface NoteQueryService {

    Optional<Note> handle(GetNoteByIdQuery query);

    List<Note> handle(GetAllNotesQuery query);

}
