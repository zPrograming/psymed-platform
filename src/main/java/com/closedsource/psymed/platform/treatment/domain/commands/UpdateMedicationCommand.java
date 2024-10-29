package com.closedsource.psymed.platform.treatment.domain.commands;

public record UpdateMedicationCommand(Long medicationId, String title, String description) {
    public UpdateMedicationCommand{
        if(medicationId == null || medicationId<=0)
            throw new IllegalArgumentException("Medication Id cannot be 0 or less");

        if(title == null || title.isBlank())
            throw new IllegalArgumentException("Title cannot be null or blank");

        if(description == null || description.isBlank())
            throw new IllegalArgumentException("Description cannot be null or blank");
    }
}
