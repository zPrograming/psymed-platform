package com.closedsource.psymed.platform.medication.application.internal.querieservices;

import com.closedsource.psymed.platform.medication.domain.model.aggregates.Medication;
import com.closedsource.psymed.platform.medication.domain.model.queries.GetAllMedicationsQuery;
import com.closedsource.psymed.platform.medication.domain.model.queries.GetMedicationByIdQuery;
import com.closedsource.psymed.platform.medication.domain.model.queries.GetMedicationByNameQuery;
import com.closedsource.psymed.platform.medication.domain.services.MedicationQueryService;
import com.closedsource.psymed.platform.medication.infrastructure.persistence.jpa.repositories.MedicationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicationQueryServiceImpl implements MedicationQueryService {

    private final MedicationRepository medicationRepository;

    public MedicationQueryServiceImpl(MedicationRepository medicationRepository) {
        this.medicationRepository = medicationRepository;
    }
    @Override
    public Optional<Medication> handle(GetMedicationByNameQuery query) {

        return medicationRepository.findByName(query.name());
    }

    @Override
    public List<Medication> handle(GetAllMedicationsQuery query) {

        return medicationRepository.findAll();
    }

    @Override
    public Optional<Medication> handle(GetMedicationByIdQuery query) {

        return medicationRepository.findById(query.medicationId());
    }

    @Override
    public Optional<Medication> handle(GetMedicationByIdQuery getMedicationByIdQuery) {
        return Optional.empty();
    }
}
