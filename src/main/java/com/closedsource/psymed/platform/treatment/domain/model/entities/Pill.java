package com.closedsource.psymed.platform.treatment.domain.model.entities;

import com.closedsource.psymed.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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


    public Pill(String name, String description, String frequency, Integer quantity){
        this.name = name;
        this.description = description;
        this.frequency = frequency;
        this.quantity = quantity;

    }

    public Pill() {

    }
}
