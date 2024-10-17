package com.closedsource.psymed.platform.appointmentandadministration.application.internal.queryservices;

import com.closedsource.psymed.platform.appointmentandadministration.domain.model.aggregates.Session;
import com.closedsource.psymed.platform.appointmentandadministration.domain.model.queries.*;
import com.closedsource.psymed.platform.appointmentandadministration.domain.model.valueobjects.PatientId;
import com.closedsource.psymed.platform.appointmentandadministration.domain.model.valueobjects.ProfessionalId;
import com.closedsource.psymed.platform.appointmentandadministration.domain.services.SessionQueryService;
import com.closedsource.psymed.platform.appointmentandadministration.infrastructure.persistence.jpa.repositories.SessionRepository;
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
        return sessionRepository.findAllByPatientId(new PatientId(query.patientId()));
    }

    @Override
    public List<Session> handle(GetAllSessionsByProfessionalIdQuery query) {
        return sessionRepository.findAllByProfessionalId(new ProfessionalId(query.professionalId()));
    }

    @Override
    public Optional<Session> handle(GetSessionByPatientIdAndSessionIdQuery query) {
        return sessionRepository.findByPatientIdAndId(new PatientId(query.patientId()), query.id());
    }

}
