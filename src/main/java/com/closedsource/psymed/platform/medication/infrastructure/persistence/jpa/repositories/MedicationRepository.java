package com.closedsource.psymed.platform.medication.infrastructure.persistence.jpa.repositories;

import com.closedsource.psymed.platform.medication.domain.model.aggregates.Medication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MedicationRepository extends JpaRepository<Medication, Long> {
    Optional<Medication> findByName(String name);
    boolean existsByName(String name);
    //boolean existsById(Long id);
}
