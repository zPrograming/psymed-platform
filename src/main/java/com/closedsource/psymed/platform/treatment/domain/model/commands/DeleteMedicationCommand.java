package com.closedsource.psymed.platform.treatment.domain.model.commands;

public record DeleteMedicationCommand(Long medicationId) {

    public DeleteMedicationCommand{
        if (medicationId ==null || medicationId <=0) throw new IllegalArgumentException("The medication Id cannot be 0 or less");
    }
}
