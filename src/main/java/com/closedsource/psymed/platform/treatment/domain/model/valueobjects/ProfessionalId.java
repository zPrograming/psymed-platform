package com.closedsource.psymed.platform.treatment.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record ProfessionalId(Long professionalId) {

    public ProfessionalId{
        if(professionalId == null|| professionalId <=0 )
            throw new IllegalArgumentException("Professional Id cannot be 0 or less");
    }

}
