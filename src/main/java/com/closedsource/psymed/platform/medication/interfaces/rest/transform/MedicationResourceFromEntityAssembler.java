package com.closedsource.psymed.platform.medication.interfaces.rest.transform;

import com.closedsource.psymed.platform.medication.domain.model.aggregates.Medication;
import com.closedsource.psymed.platform.medication.interfaces.rest.resources.MedicationResource;

public class MedicationResourceFromEntityAssembler {
    public static MedicationResource toResourceFromEntity(Medication entity){
        return new MedicationResource(entity.getId(), entity.getName(), entity.getDescription());
    }
}
