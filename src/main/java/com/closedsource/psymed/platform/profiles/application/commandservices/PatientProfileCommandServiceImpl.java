package com.closedsource.psymed.platform.profiles.application.commandservices;

import com.closedsource.psymed.platform.profiles.application.outboundservices.acl.ExternalAccountService;
import com.closedsource.psymed.platform.profiles.domain.model.aggregates.PatientProfile;
import com.closedsource.psymed.platform.profiles.domain.model.commands.CheckPatientProfileByIdCommand;
import com.closedsource.psymed.platform.profiles.domain.model.commands.CreatePatientProfileCommand;
import com.closedsource.psymed.platform.profiles.domain.model.commands.DeletePatientProfileCommand;
import com.closedsource.psymed.platform.profiles.domain.model.valueobjects.Email;
import com.closedsource.psymed.platform.profiles.domain.services.PatientProfileCommandService;
import com.closedsource.psymed.platform.profiles.infrastructure.persistence.jpa.repositories.PatientProfileRepository;
import com.closedsource.psymed.platform.profiles.infrastructure.persistence.jpa.repositories.ProfessionalProfileRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PatientProfileCommandServiceImpl implements PatientProfileCommandService {

    private final PatientProfileRepository patientProfileRepository;
    private final ProfessionalProfileRepository professionalProfileRepository;
    private final ExternalAccountService externalAccountService;

    public PatientProfileCommandServiceImpl(PatientProfileRepository patientProfileRepository,
                                            ProfessionalProfileRepository professionalProfileRepository,
                                            ExternalAccountService externalAccountService) {
        this.patientProfileRepository = patientProfileRepository;
        this.professionalProfileRepository = professionalProfileRepository;
        this.externalAccountService = externalAccountService;
    }


    @Override
    @Transactional
    public Optional<PatientProfile> handle(CreatePatientProfileCommand command) {
        var emailAddress = new Email(command.email());

        if(patientProfileRepository.existsByEmail(emailAddress) || professionalProfileRepository.existsByEmail(emailAddress))
            throw new IllegalArgumentException("Email already exists");

        var accountId = externalAccountService.createAccount(command.username(), command.password(), "ROLE_PATIENT");
        var patientProfile = new PatientProfile(command, accountId.get());
        patientProfileRepository.save(patientProfile);

        return Optional.of(patientProfile);
    }

    @Override
    public boolean handle(CheckPatientProfileByIdCommand command) {
        return this.patientProfileRepository.existsById(command.id());
    }

    @Override
    public void handle(DeletePatientProfileCommand command) {
        patientProfileRepository.deleteById(command.id());
    }
}
