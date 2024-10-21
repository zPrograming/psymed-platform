package com.closedsource.psymed.platform.patientreport.domain.model.aggregates;

import com.closedsource.psymed.platform.patientreport.domain.model.valueobjects.BiologicalFunctionStatus;
import com.closedsource.psymed.platform.patientreport.domain.model.valueobjects.PatientId;
import com.closedsource.psymed.platform.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class BiologicalFunctionRecord extends AuditableModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    BiologicalFunctionStatus status;

    @Getter
    @Embedded
    private PatientId patientId;


    public BiologicalFunctionRecord() {
        this.patientId = null;
        this.status = null;
    }

    public BiologicalFunctionRecord(Integer hunger, Integer hydration,
                                    Integer sleep, Integer energy, Long patientId) {
        this.status = new BiologicalFunctionStatus(hunger, hydration, sleep, energy);
        this.patientId = new PatientId(patientId);

    }
}
