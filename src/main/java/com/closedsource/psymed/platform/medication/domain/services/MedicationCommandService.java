package com.closedsource.psymed.platform.medication.domain.services;

import com.closedsource.psymed.platform.medication.domain.model.commands.CreateMedicationCommand;
import com.closedsource.psymed.platform.medication.domain.model.commands.DeleteMedicationCommand;

public interface MedicationCommandService {
    Long handle (CreateMedicationCommand command);
    void handle (DeleteMedicationCommand command);
}
