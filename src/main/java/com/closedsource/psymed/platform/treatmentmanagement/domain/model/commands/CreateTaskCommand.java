package com.closedsource.psymed.platform.treatmentmanagement.domain.model.commands;

public record CreateTaskCommand(String title, String description, boolean completionStatus) {
}
