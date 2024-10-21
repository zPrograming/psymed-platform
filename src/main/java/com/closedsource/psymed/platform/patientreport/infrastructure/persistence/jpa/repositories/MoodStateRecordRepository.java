package com.closedsource.psymed.platform.patientreport.infrastructure.persistence.jpa.repositories;

import com.closedsource.psymed.platform.patientreport.domain.model.aggregates.BiologicalFunctionRecord;
import com.closedsource.psymed.platform.patientreport.domain.model.aggregates.MoodStateRecord;
import com.closedsource.psymed.platform.patientreport.domain.model.valueobjects.PatientId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MoodStateRecordRepository extends JpaRepository<MoodStateRecord, Long> {
    List<MoodStateRecord> findAllByPatientId(PatientId patientId);
    boolean existsByPatientId(PatientId patientId);
}
