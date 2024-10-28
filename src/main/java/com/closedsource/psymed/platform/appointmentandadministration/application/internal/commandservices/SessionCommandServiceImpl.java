package com.closedsource.psymed.platform.appointmentandadministration.application.internal.commandservices;

import com.closedsource.psymed.platform.appointmentandadministration.domain.model.aggregates.Session;
import com.closedsource.psymed.platform.appointmentandadministration.domain.model.commands.CreateSessionCommand;
import com.closedsource.psymed.platform.appointmentandadministration.domain.model.commands.UpdateSessionNoteCommand;
import com.closedsource.psymed.platform.appointmentandadministration.domain.services.SessionCommandService;
import com.closedsource.psymed.platform.appointmentandadministration.infrastructure.persistence.jpa.repositories.SessionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class SessionCommandServiceImpl implements SessionCommandService {
    private final SessionRepository sessionRepository;

    public SessionCommandServiceImpl(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    /** {@inheritDoc} */
    @Override
    @Transactional
    public Optional<Session> handle(CreateSessionCommand command) {
        // Create a new session based on the command
        var session = new Session(command);
        var createdSession = sessionRepository.save(session);
        return Optional.of(createdSession);
    }

    @Override
    public Optional<Session> handle(UpdateSessionNoteCommand command) {

        var session = sessionRepository.findById(command.id()).get();
        session.setNote(command.note());

        var optionalSession = sessionRepository.save(session);

        return Optional.of(optionalSession);
    }

}
