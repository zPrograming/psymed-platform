package com.closedsource.psymed.platform.profiles.application.commandservices;

import com.closedsource.psymed.platform.profiles.domain.model.aggregates.PatientProfile;
import com.closedsource.psymed.platform.profiles.domain.model.commands.CheckPatientProfileByIdCommand;
import com.closedsource.psymed.platform.profiles.domain.model.commands.CreatePatientProfileCommand;
import com.closedsource.psymed.platform.profiles.domain.model.valueobjects.Email;
import com.closedsource.psymed.platform.profiles.domain.services.PatientProfileCommandService;
import com.closedsource.psymed.platform.profiles.infrastructure.persistence.jpa.repositories.PatientProfileRepository;
import com.closedsource.psymed.platform.profiles.infrastructure.persistence.jpa.repositories.ProfessionalProfileRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PatientProfileCommandServiceImpl implements PatientProfileCommandService {

    private final PatientProfileRepository patientProfileRepository;
    private final ProfessionalProfileRepository professionalProfileRepository;

    public PatientProfileCommandServiceImpl(PatientProfileRepository patientProfileRepository, ProfessionalProfileRepository professionalProfileRepository) {
        this.patientProfileRepository = patientProfileRepository;
        this.professionalProfileRepository = professionalProfileRepository;
    }

    @Override
    public Optional<PatientProfile> handle(CreatePatientProfileCommand command) {
        var emailAddress = new Email(command.email());

        if(patientProfileRepository.existsByEmail(emailAddress) || professionalProfileRepository.existsByEmail(emailAddress))
            throw new IllegalArgumentException("Email already exists");

        var patientProfile = new PatientProfile(command);
        patientProfileRepository.save(patientProfile);

        return Optional.of(patientProfile);
    }

    @Override
    public boolean handle(CheckPatientProfileByIdCommand command) {
        return this.patientProfileRepository.existsById(command.id());
    }
}
