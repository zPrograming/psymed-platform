package com.closedsource.psymed.platform.appointmentandadministration.application.internal.commandservices;

import com.closedsource.psymed.platform.appointmentandadministration.application.internal.outboundservices.acl.AppointmentVersionOfExternalProfileService;
import com.closedsource.psymed.platform.appointmentandadministration.domain.exceptions.PatientNotFoundException;
import com.closedsource.psymed.platform.appointmentandadministration.domain.exceptions.ProfessionalNotFoundException;
import com.closedsource.psymed.platform.appointmentandadministration.domain.model.aggregates.Session;
import com.closedsource.psymed.platform.appointmentandadministration.domain.model.commands.*;
import com.closedsource.psymed.platform.appointmentandadministration.domain.services.SessionCommandService;
import com.closedsource.psymed.platform.appointmentandadministration.infrastructure.persistence.jpa.repositories.SessionRepository;
import com.closedsource.psymed.platform.sessionnotes.domain.model.entities.Note;
import com.closedsource.psymed.platform.sessionnotes.domain.model.entities.Task;
import com.closedsource.psymed.platform.sessionnotes.interfaces.rest.resources.CreateNoteResource;
import com.closedsource.psymed.platform.sessionnotes.interfaces.rest.resources.CreateTaskResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SessionCommandServiceImpl implements SessionCommandService {
    private final SessionRepository sessionRepository;
    private final AppointmentVersionOfExternalProfileService externalProfileService; // Service to check patient and professional existence

    public SessionCommandServiceImpl(SessionRepository sessionRepository, AppointmentVersionOfExternalProfileService externalProfileService) {
        this.sessionRepository = sessionRepository;
        this.externalProfileService = externalProfileService;
    }

    /** {@inheritDoc} */
    @Override
    @Transactional
    public Optional<Session> handle(CreateSessionCommand command) {
        // Check if the patient and professional exist
        boolean patientExists = externalProfileService.existsPatientById(command.patientId().patientId());
        boolean professionalExists = externalProfileService.existsProfessionalById(command.professionalId().professionalId());

        if (!patientExists) {
            throw new PatientNotFoundException(command.patientId().patientId()); // Custom exception for patient not found
        }
        if (!professionalExists) {
            throw new ProfessionalNotFoundException(command.professionalId().professionalId()); // Custom exception for professional not found
        }

        // Create a new session based on the command
        var session = new Session(command);
        var createdSession = sessionRepository.save(session);
        return Optional.of(createdSession);
    }

    @Override
    public Optional<Note> handle(CreateSessionNoteCommand command) {
        Optional<Session> session = sessionRepository.findById(command.id());

        List<Note> notes = session.get().getNotes();
        notes.add(command.note());

        return Optional.of(command.note());
    }

    @Override
    public Optional<Task> handle(CreateSessionTaskCommand command) {

        Optional<Session> session = sessionRepository.findById(command.id());

        List<Task> tasks = session.get().getTasks();
        tasks.add(command.task());

        return Optional.of(command.task());
    }


    @Override
    public Optional<Note> handle(UpdateSessionNoteCommand command) {
        Optional<Session> session = sessionRepository.findById(command.id());

        List<Note> notes = session.get().getNotes();

        CreateNoteResource requestedNote = command.note();
        Note oldNote = notes.get(command.noteId());

        oldNote.setDescription(requestedNote.description());
        oldNote.setSymptoms(requestedNote.symptom());

        return Optional.of(oldNote);
    }

    @Override
    public Optional<Task> handle(UpdateSessionTaskCommand command) {
        Optional<Session> session = sessionRepository.findById(command.id());

        List<Task> tasks = session.get().getTasks();

        CreateTaskResource requestedTask = command.task();
        Task oldTask = tasks.get(command.taskId());

        oldTask.setDescription(requestedTask.description());
        oldTask.setTitle(requestedTask.title());
        oldTask.setCompletionStatus(requestedTask.completionStatus());

        return Optional.of(oldTask);
    }

    @Override
    public void handle(DeleteSessionNoteCommand command) {
        Optional<Session> session = sessionRepository.findById(command.id());

        List<Task> tasks = session.get().getTasks();
        tasks.remove(command.noteId());
    }

    @Override
    public void handle(DeleteSessionTaskCommand command) {
        Optional<Session> session = sessionRepository.findById(command.id());

        List<Task> tasks = session.get().getTasks();
        tasks.remove(command.taskId());
    }


}
