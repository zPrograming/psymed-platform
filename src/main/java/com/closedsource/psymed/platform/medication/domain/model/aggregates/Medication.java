package com.closedsource.psymed.platform.medication.domain.model.aggregates;

import com.closedsource.psymed.platform.medication.domain.model.commands.CreateMedicationCommand;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Medication {
    private Long Id;
    private String name;
    private String description;

    public Medication (String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Medication(CreateMedicationCommand command) {
        this.name = command.name();
        this.description = command.description();
    }
}
