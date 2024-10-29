package com.closedsource.psymed.platform.treatment.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record PatientId(Long patientId) {
    public PatientId{
        if(patientId == null || patientId <=0 )
            throw new IllegalArgumentException("Patient id cannot be 0 ore less");
    }



}
