package com.closedsource.psymed.platform.medication.application.internal.querieservices;

import com.closedsource.psymed.platform.medication.domain.model.aggregates.Medication;
import com.closedsource.psymed.platform.medication.domain.model.queries.GetAllMedicationsQuery;
import com.closedsource.psymed.platform.medication.domain.model.queries.GetMedicationByNameQuery;
import com.closedsource.psymed.platform.medication.domain.services.MedicationQueryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicationQueryServiceImpl implements MedicationQueryService {
    @Override
    public Optional<Medication> handle(GetMedicationByNameQuery query) {
    }

    @Override
    public List<Medication> handle(GetAllMedicationsQuery query) {
    }
}
