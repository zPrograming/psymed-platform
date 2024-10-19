package com.closedsource.psymed.platform.patientreport.domain.model.entities;

import com.closedsource.psymed.platform.patientreport.domain.model.valueobjects.BiologicalFunctionStatus;
import com.closedsource.psymed.platform.patientreport.domain.model.valueobjects.PatientId;
import com.closedsource.psymed.platform.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

public class BiologicalFunction extends AuditableModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    BiologicalFunctionStatus status;

    @NotNull
    private PatientId patientId;


    public BiologicalFunction(Integer hunger, Integer hydration,
                              Integer sleep, Integer energy, Long patientId) {
        this.status = new BiologicalFunctionStatus(hunger, hydration, sleep, energy);
        this.patientId = new PatientId(patientId);

    }
}
