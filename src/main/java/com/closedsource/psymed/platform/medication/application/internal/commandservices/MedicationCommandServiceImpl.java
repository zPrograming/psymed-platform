package com.closedsource.psymed.platform.medication.application.internal.commandservices;

import com.closedsource.psymed.platform.medication.domain.model.commands.CreateMedicationCommand;
import com.closedsource.psymed.platform.medication.domain.model.commands.DeleteMedicationCommand;
import com.closedsource.psymed.platform.medication.domain.services.MedicationCommandService;
import org.springframework.stereotype.Service;

@Service
public class MedicationCommandServiceImpl implements MedicationCommandService {
    @Override
    public Long handle(CreateMedicationCommand command) {
    }

    @Override
    public void handle(DeleteMedicationCommand command) {
    }
}
