package com.closedsource.psymed.platform.medication.domain.services;

import com.closedsource.psymed.platform.medication.domain.model.aggregates.Medication;
import com.closedsource.psymed.platform.medication.domain.model.commands.CreateMedicationCommand;
import com.closedsource.psymed.platform.medication.domain.model.commands.DeleteMedicationCommand;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface MedicationCommandService {
    Long handle (CreateMedicationCommand command);
    void handle (DeleteMedicationCommand command);


}
