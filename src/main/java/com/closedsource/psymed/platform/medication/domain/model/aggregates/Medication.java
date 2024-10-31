package com.closedsource.psymed.platform.medication.domain.model.aggregates;

import com.closedsource.psymed.platform.medication.domain.model.commands.CreateMedicationCommand;
import com.closedsource.psymed.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Entity
//@NoArgsConstructor
public class Medication extends AuditableAbstractAggregateRoot<Medication> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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


    public Medication() {

    }
}
