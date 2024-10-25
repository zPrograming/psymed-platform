package com.closedsource.psymed.platform.treatment.domain.model.valueobjects;

import com.closedsource.psymed.platform.treatment.domain.model.entities.Pill;
import jakarta.persistence.Embeddable;

@Embeddable
public record PillId(Long pillId) {
    public PillId{
        if(pillId<0){
            throw new IllegalStateException("Pills Id cannot be less than zero");
        }
    }


    public PillId(){
        this(0L);
    }

}
