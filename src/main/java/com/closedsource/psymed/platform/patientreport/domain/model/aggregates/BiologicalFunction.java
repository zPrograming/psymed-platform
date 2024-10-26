package com.closedsource.psymed.platform.patientreport.domain.model.aggregates;

import com.closedsource.psymed.platform.patientreport.domain.model.valueobjects.BiologicalFunctionStatus;
import com.closedsource.psymed.platform.patientreport.domain.model.valueobjects.PatientId;
import com.closedsource.psymed.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.closedsource.psymed.platform.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class BiologicalFunction extends AuditableAbstractAggregateRoot<BiologicalFunction> {

    @Getter
    @NotNull
    @Embedded
    BiologicalFunctionStatus status;

    @Getter
    @Embedded
    private PatientId patientId;


    public BiologicalFunction() {
        this.patientId = null;
        this.status = null;
    }

    public BiologicalFunction(Integer hunger, Integer hydration,
                              Integer sleep, Integer energy, Long patientId) {
        this.status = new BiologicalFunctionStatus(hunger, hydration, sleep, energy);
        this.patientId = new PatientId(patientId);
    }

    public Long getLongPatientId() {
        return patientId.patientId();
    }

    public Integer getHunger() {
        return status.hunger();
    }

    public Integer getHydration() {
        return status.hydration();
    }

    public Integer getSleep() {
        return status.sleep();
    }

    public Integer getEnergy() {
        return status.energy();
    }
}
