package com.closedsource.psymed.platform.treatment.domain.model.aggregates;


import com.closedsource.psymed.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.closedsource.psymed.platform.treatment.domain.model.entities.Pill;
import com.closedsource.psymed.platform.treatment.domain.model.valueobjects.MedicalPrescription;
import io.jsonwebtoken.lang.Strings;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.Getter;


@Getter
@Entity
public class Medication extends AuditableAbstractAggregateRoot<Medication> {

    private String title;
    private String description;

    @Embedded
    private MedicalPrescription medicalPrescription = new MedicalPrescription();

    public Medication(String title, String description){
        this.title = title;
        this.description = description;
    }

    public Medication(){this(Strings.EMPTY, Strings.EMPTY);}

    public void addMedication(Pill pill){
        this.medicalPrescription.addPill(pill);
    }

    public Medication updateMedication(String title, String description){
        this.title = title;
        this.description = description;
        return this;
    }

}
