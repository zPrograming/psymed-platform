package com.closedsource.psymed.platform.treatment.domain.model.aggregates;


import com.closedsource.psymed.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.closedsource.psymed.platform.treatment.domain.model.valueobjects.MedicationStatus;
import com.closedsource.psymed.platform.treatment.domain.model.valueobjects.PatientId;
import com.closedsource.psymed.platform.treatment.domain.model.valueobjects.PrescriptionLifeCycleDate;
import com.closedsource.psymed.platform.treatment.domain.model.valueobjects.ProfessionalId;
import io.jsonwebtoken.lang.Strings;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;


@Getter
@Entity
@NoArgsConstructor
public class MedicalPrescription extends AuditableAbstractAggregateRoot<MedicalPrescription> {


    private String title;
    private String description;

    @Embedded
    private PrescriptionLifeCycleDate lifeCycleDate;

    @Embedded
    private ProfessionalId professionalId;

    @Embedded
    private PatientId patientId;



    //@Embedded
    //private MedicalPrescription medicalPrescription = new MedicalPrescription();

    public MedicalPrescription(String title, String description,
                               Date start, Date end, Long professionalId, Long patientId){
        this.title = title;
        this.description = description;
        this.lifeCycleDate = new PrescriptionLifeCycleDate(start, end);
        this.professionalId = new ProfessionalId(professionalId);
        this.patientId = new PatientId(patientId);

    }


    /*public void addMedication(Pill pill){
        this.medicalPrescription.addPill(pill);
    }

    public Pill getLastPillInMedicalPrescription() {
        return this.medicalPrescription.getLastPillInMedicalPrescription();
    }


    public void deleteMedication(Pill pill){
        this.medicalPrescription.removePill(pill);
    }

    public Medication updateMedication(String title, String description){
        this.title = title;
        this.description = description;
        return this;
    }*/

}
