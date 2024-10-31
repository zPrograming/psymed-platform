package com.closedsource.psymed.platform.medication.domain.model.queries;

public record GetMedicationByNameQuery(String name) {
    public GetMedicationByNameQuery{
        if(name == null || name.isBlank())
            throw new IllegalArgumentException("Name cannot be null or empty");
    }
}
