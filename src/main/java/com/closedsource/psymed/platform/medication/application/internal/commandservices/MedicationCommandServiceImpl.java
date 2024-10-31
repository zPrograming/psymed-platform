package com.closedsource.psymed.platform.medication.application.internal.commandservices;

import com.closedsource.psymed.platform.medication.domain.model.aggregates.Medication;
import com.closedsource.psymed.platform.medication.domain.model.commands.CreateMedicationCommand;
import com.closedsource.psymed.platform.medication.domain.model.commands.DeleteMedicationCommand;
import com.closedsource.psymed.platform.medication.domain.services.MedicationCommandService;
import com.closedsource.psymed.platform.medication.infrastructure.persistence.jpa.repositories.MedicationRepository;
import org.springframework.stereotype.Service;

@Service
public class MedicationCommandServiceImpl implements MedicationCommandService {

    private final MedicationRepository medicationRepository;

    public MedicationCommandServiceImpl(MedicationRepository medicationRepository){
        this.medicationRepository = medicationRepository;
    }
    @Override
    public Long handle(CreateMedicationCommand command) {
        if (medicationRepository.existsByName(command.name()))
            throw new IllegalArgumentException("There is a medication with the same name");
        var medication = new Medication(command);

        try {
            medicationRepository.save(medication);
        }catch(Exception e){
            throw new IllegalArgumentException(String.format("Error creating the medication %s", e.getMessage()));
        }
        return medication.getId();
    }

    @Override
    public void handle(DeleteMedicationCommand command) {
        if(!medicationRepository.existsById(command.medicationId()))
            throw new IllegalStateException("The medication doesn't exist");
        try {
            medicationRepository.deleteById(command.medicationId());
        } catch(Exception e)
        {
            throw new IllegalArgumentException("An error occurred during delete: %s".formatted(e.getMessage()));
        }
    }
}
