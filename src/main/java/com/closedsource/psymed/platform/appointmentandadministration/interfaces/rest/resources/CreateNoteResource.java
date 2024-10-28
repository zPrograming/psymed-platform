package com.closedsource.psymed.platform.appointmentandadministration.interfaces.rest.resources;

public record CreateNoteResource(
        String description,
        String symptom) {

    public CreateNoteResource{

        if (description == null || description.isBlank()){
            throw new IllegalArgumentException("description cannot be null or empty");
        }

        if (symptom == null || symptom.isBlank()){
            throw new IllegalArgumentException("symptom cannot be null or empty");
        }
    }
}
