package com.closedsource.psymed.platform.treatment.domain.model.aggregates;

import com.closedsource.psymed.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Entity;
import lombok.Getter;
import org.apache.logging.log4j.util.Strings;

@Getter
@Entity
public class Medication extends AuditableAbstractAggregateRoot<Medication> {

    private String title;
    private String description;
    private String frequency;
    private Integer quantity;

    public Medication(String title, String description, String frequency, Integer quantity){
        this.title = title;
        this.description = description;
    }

    public Medication() {

    }
}
