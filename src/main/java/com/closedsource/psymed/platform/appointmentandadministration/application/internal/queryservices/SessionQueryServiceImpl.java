package com.closedsource.psymed.platform.appointmentandadministration.application.internal.queryservices;

import com.closedsource.psymed.platform.appointmentandadministration.domain.model.aggregates.Session;
import com.closedsource.psymed.platform.appointmentandadministration.domain.model.queries.*;
import com.closedsource.psymed.platform.appointmentandadministration.domain.model.valueobjects.PatientId;
import com.closedsource.psymed.platform.appointmentandadministration.domain.model.valueobjects.ProfessionalId;
import com.closedsource.psymed.platform.appointmentandadministration.domain.services.SessionQueryService;
import com.closedsource.psymed.platform.appointmentandadministration.infrastructure.persistence.jpa.repositories.SessionRepository;
import com.closedsource.psymed.platform.sessionnotes.domain.model.entities.Note;
import com.closedsource.psymed.platform.sessionnotes.domain.model.entities.Task;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SessionQueryServiceImpl implements SessionQueryService {

    private final SessionRepository sessionRepository;

    public SessionQueryServiceImpl(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @Override
    public List<Session> handle(GetAllSessionsByPatientIdQuery query) {

        Long patientIdAsLong = Long.parseLong(query.patientId()); // Convert String to Long

        return sessionRepository.findAllByPatientId(new PatientId(patientIdAsLong));
    }

    @Override
    public List<Session> handle(GetAllSessionsByProfessionalIdQuery query) {

        Long professionalIdAsLong = Long.parseLong(query.professionalId()); // Convert String to Long
        return sessionRepository.findAllByProfessionalId(new ProfessionalId(professionalIdAsLong));
    }

    @Override
    public Optional<Session> handle(GetSessionByPatientIdAndSessionIdQuery query) {
        Long patientIdAsLong = Long.parseLong(query.patientId()); // Convert String to Long
        return sessionRepository.findByPatientIdAndId(new PatientId(patientIdAsLong), query.id());
    }

    @Override
    public Optional<Session> handle(GetSessionByIdQuery query) {
        return sessionRepository.findById(query.id());
    }

    @Override
    public List<Note> handle(GetAllSessionNotesByIdQuery query) {

        Optional<Session> session = sessionRepository.findById(query.sessionId());

        return session.get().getNotes();

    }

    @Override
    public List<Task> handle(GetAllSessionTasksByIdQuery query) {
        Optional<Session> session = sessionRepository.findById(query.sessionId());

        return session.get().getTasks();
    }

    @Override
    public Optional<Note> handle(GetSessionNoteByNoteIdAndSessionIdQuery query) {
        Optional<Session> session = sessionRepository.findById(query.sessionId());

        List<Note> notes = session.get().getNotes();

        if (notes.size() <= query.noteId()){
            return Optional.empty();
        }

        return Optional.of(notes.get(query.noteId()));
    }

    @Override
    public Optional<Task> handle(GetSessionTaskByTaskIdAndSessionIdQuery query) {
        Optional<Session> session = sessionRepository.findById(query.sessionId());

        List<Task> notes = session.get().getTasks();

        if (notes.size() <= query.taskId()){
            return Optional.empty();
        }

        return Optional.of(notes.get(query.taskId()));
    }

    @Override
    public List<Session> handle() {
        return sessionRepository.findAll();
    }
}
