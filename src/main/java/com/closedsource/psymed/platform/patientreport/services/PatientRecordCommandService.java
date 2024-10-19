package com.closedsource.psymed.platform.patientreport.services;

import com.closedsource.psymed.platform.patientreport.domain.model.commands.CreateMoodStateRecordCommand;

public interface PatientRecordCommandService {
    void recordMoodState(CreateMoodStateRecordCommand command) throws IllegalStateException;

}
