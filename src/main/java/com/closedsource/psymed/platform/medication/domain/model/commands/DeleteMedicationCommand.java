package com.closedsource.psymed.platform.medication.domain.model.commands;

public record DeleteMedicationCommand(Long medicationId) {

    public DeleteMedicationCommand{
        if( medicationId == null ||medicationId <= 0)
        {
            throw new IllegalArgumentException("Medication Id cannot be null or less than zero");
        }
    }
}
