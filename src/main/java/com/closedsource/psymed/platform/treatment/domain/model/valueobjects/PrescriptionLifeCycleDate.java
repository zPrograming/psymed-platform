package com.closedsource.psymed.platform.treatment.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

import java.util.Date;

@Embeddable
public record PrescriptionLifeCycleDate(Date startedDate, Date endedDate) {

    public PrescriptionLifeCycleDate {
        if(startedDate == null)
            throw new IllegalArgumentException("Started date cannot be null");

        if(endedDate == null)
            throw new IllegalArgumentException("Ended date cannot be null");
    }
}
