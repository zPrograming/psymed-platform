package com.closedsource.psymed.platform.treatment.domain.model.entities;

import com.closedsource.psymed.platform.shared.domain.model.entities.AuditableModel;
import com.closedsource.psymed.platform.treatment.domain.model.aggregates.MedicalPrescription;
import com.closedsource.psymed.platform.treatment.domain.model.valueobjects.PatientId;
import com.closedsource.psymed.platform.treatment.domain.model.valueobjects.PrescriptionLifeCycleDate;
import com.closedsource.psymed.platform.treatment.domain.model.valueobjects.ProfessionalId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.List;


@Getter
@Entity
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class MedicalPrescriptionItem extends AuditableModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @Embedded
    private PrescriptionLifeCycleDate lifeCycleDate;

    @Embedded
    private PatientId patientId;

    @Embedded
    private ProfessionalId professionalId;

    @OneToMany(mappedBy = "medicalPrescriptionItem", cascade = CascadeType.ALL)
    private List<PrescriptionPill> prescriptionPills = new ArrayList<>();


    public MedicalPrescriptionItem(Long id, String name,
                                   String description,
                                   PrescriptionLifeCycleDate lifeCycleDate,
                                   PatientId patientId,
                                   ProfessionalId professionalId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.lifeCycleDate = lifeCycleDate;
        this.patientId = patientId;
        this.professionalId = professionalId;

    }

    public void addPrescriptionPill(PrescriptionPill pill){
        this.prescriptionPills.add(pill);
    }

    public void cancelPrescriptionPill(PrescriptionPill pill){
        this.prescriptionPills.remove(pill);
    }


}
