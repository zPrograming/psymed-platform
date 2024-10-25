package com.closedsource.psymed.platform.treatment.domain.model.aggregates;

import com.closedsource.psymed.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Entity;
import lombok.Getter;

@Getter
@Entity
public class Treatment extends AuditableAbstractAggregateRoot<Treatment> {

    private String title;
    private String description;

    public Treatment(String title, String description){
        this.title = title;
        this.description = description;
    }

    public Treatment() {

    }
}
