package com.closedsource.psymed.platform.profiles.infrastructure.persistence.jpa.repositories;

import com.closedsource.psymed.platform.profiles.domain.model.aggregates.ProfessionalProfile;
import com.closedsource.psymed.platform.profiles.domain.model.valueobjects.Email;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessionalProfileRepository extends JpaRepository<ProfessionalProfile, Long> {
    boolean existsByEmail(Email email);
}
