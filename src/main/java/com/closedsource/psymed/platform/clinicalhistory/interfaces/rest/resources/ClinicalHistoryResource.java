package com.closedsource.psymed.platform.clinicalhistory.interfaces.rest.resources;

import java.time.LocalDate;

public record ClinicalHistoryResource(
        Long clinicalHistoryId,
        Long patientId,
        String background,
        String consultationReason,
        LocalDate consultationDate
) {
}
