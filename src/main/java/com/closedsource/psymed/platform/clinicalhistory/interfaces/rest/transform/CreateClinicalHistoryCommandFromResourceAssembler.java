package com.closedsource.psymed.platform.clinicalhistory.interfaces.rest.transform;

import com.closedsource.psymed.platform.clinicalhistory.domain.model.commands.CreateClinicalHistoryCommand;
import com.closedsource.psymed.platform.clinicalhistory.interfaces.rest.resources.CreateClinicalHistoryResource;

public class CreateClinicalHistoryCommandFromResourceAssembler {
    public static CreateClinicalHistoryCommand toCommandFromResource(CreateClinicalHistoryResource resource) {
        return new CreateClinicalHistoryCommand(
                resource.patientId(),
                resource.background(),
                resource.consultationReason(),
                resource.consultationDate()
        );
    }
}
