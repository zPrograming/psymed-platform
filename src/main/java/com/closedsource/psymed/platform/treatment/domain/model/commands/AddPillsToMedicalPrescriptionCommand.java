package com.closedsource.psymed.platform.treatment.domain.model.commands;

import com.closedsource.psymed.platform.treatment.domain.model.entities.Pill;

import java.util.List;

public record AddPillsToMedicalPrescriptionCommand(List<Pill> pills) {


}
