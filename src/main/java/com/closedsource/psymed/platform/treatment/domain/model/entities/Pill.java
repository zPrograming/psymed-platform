package com.closedsource.psymed.platform.treatment.domain.model.entities;

import com.closedsource.psymed.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.closedsource.psymed.platform.treatment.domain.model.valueobjects.PillStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
@Entity
public class Pill{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;
    @NotNull
    private String description;


    //@Enumerated(EnumType.STRING)
    //@NotNull
    //private PillStatus status;

    public Pill(String name, String description){
        this.name = name;
        this.description = description;

    }

    public Pill() {

    }
}
