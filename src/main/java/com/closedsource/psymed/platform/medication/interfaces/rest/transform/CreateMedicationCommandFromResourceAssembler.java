package com.closedsource.psymed.platform.medication.interfaces.rest.transform;

import com.closedsource.psymed.platform.medication.domain.model.commands.CreateMedicationCommand;
import com.closedsource.psymed.platform.medication.interfaces.rest.resources.CreateMedicationResource;

public class CreateMedicationCommandFromResourceAssembler {
    public static CreateMedicationCommand toCommandFromResource (CreateMedicationResource resource)
    {
        return new CreateMedicationCommand(resource.name(), resource.description());
    }
}
