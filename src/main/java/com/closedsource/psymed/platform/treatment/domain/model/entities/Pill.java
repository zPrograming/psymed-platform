package com.closedsource.psymed.platform.treatment.domain.model.entities;

import com.closedsource.psymed.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.closedsource.psymed.platform.treatment.domain.model.aggregates.Medication;
import com.closedsource.psymed.platform.treatment.domain.model.valueobjects.PillStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
@Entity
public class Pill extends AuditableAbstractAggregateRoot<Pill> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;
    @NotNull
    private String description;
    @NotNull
    private String frequency;
    @NotNull
    private Integer quantity;

    @Enumerated(EnumType.STRING)
    @NotNull
    private PillStatus status;

    public Pill(String name, String description, String frequency, Integer quantity, PillStatus status){
        this.name = name;
        this.description = description;
        this.frequency = frequency;
        this.quantity = quantity;
        this.status = status;
    }

    public Pill(String name, String description, String frequency, Integer quantity){
        this.name = name;
        this.description = description;
        this.frequency = frequency;
        this.quantity = quantity;
        this.status = PillStatus.SCHEDULED;
    }
 

    public Pill() {

    }
}
