package com.closedsource.psymed.platform.medication.domain.model.queries;

public record GetMedicationByIdQuery(Long medicationId) {
    public GetMedicationByIdQuery{
        if (medicationId == null || medicationId <= 0) throw new IllegalArgumentException("Medication Id cannot be 0 or less");
    }
}
