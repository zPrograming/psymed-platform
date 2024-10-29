package com.closedsource.psymed.platform.treatment.domain.model.commands;

public record CreateMedicationCommand(String title, String description) {

    public CreateMedicationCommand{
        if(title == null || title.isBlank())
            throw new IllegalArgumentException("Title cannot be null or blank");

        if(description==null || description.isBlank())
            throw new IllegalArgumentException("Description cannot be null or blank");
    }
}
