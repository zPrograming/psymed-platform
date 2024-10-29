package com.closedsource.psymed.platform.treatment.domain.model.entities;


import com.closedsource.psymed.platform.treatment.domain.model.aggregates.MedicalPrescription;
import com.closedsource.psymed.platform.treatment.domain.model.valueobjects.PillId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class PrescriptionPill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private PillId pillId;

    private int quantity;
    private String frequency;

    @ManyToOne
    @JoinColumn(name = "medical_prescription_item_id")
    private MedicalPrescription medicalPrescription;

    public PrescriptionPill(PillId pillId, int quantity, String frequency) {
        this.pillId = pillId;
        this.quantity = quantity;
        this.frequency = frequency;
    }

    public void assignMedicalPrescription(MedicalPrescription medicalPrescription){
        this.medicalPrescription = medicalPrescription;
    }


}
