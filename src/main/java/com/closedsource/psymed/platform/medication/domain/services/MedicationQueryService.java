package com.closedsource.psymed.platform.medication.domain.services;

import com.closedsource.psymed.platform.medication.domain.model.aggregates.Medication;
import com.closedsource.psymed.platform.medication.domain.model.queries.GetAllMedicationsQuery;
import com.closedsource.psymed.platform.medication.domain.model.queries.GetMedicationByIdQuery;
import com.closedsource.psymed.platform.medication.domain.model.queries.GetMedicationByNameQuery;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface MedicationQueryService {

    Optional<Medication> handle(GetMedicationByNameQuery query);

    List<Medication> handle(GetAllMedicationsQuery query);

    Optional<Medication> handle(GetMedicationByIdQuery getMedicationByIdQuery);
}
