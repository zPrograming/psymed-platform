package com.closedsource.psymed.platform.appointmentandadministration.infrastructure.persistence.jpa.repositories;

import com.closedsource.psymed.platform.appointmentandadministration.domain.model.aggregates.Session;
import com.closedsource.psymed.platform.appointmentandadministration.domain.model.valueobjects.PatientId;
import com.closedsource.psymed.platform.appointmentandadministration.domain.model.valueobjects.ProfessionalId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
    /**
     * Find all sessions by patient ID.
     *
     * @param patientId The unique ID of the patient
     * @return a list of sessions for the specified patient
     */
    List<Session> findAllByPatientId(PatientId patientId);

    /**
     * Find all sessions by professional ID.
     *
     * @param professionalId The unique ID of the professional
     * @return a list of sessions for the specified professional
     */
    List<Session> findAllByProfessionalId(ProfessionalId professionalId);

    /**
     * Find a specific session by patient ID and session ID.
     *
     * @param patientId The unique ID of the patient
     * @param id The unique ID of the session
     * @return an optional session if found
     */
    Optional<Session> findByPatientIdAndId(PatientId patientId, Long id);
}
