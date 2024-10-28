package com.closedsource.psymed.platform.appointmentandadministration.infrastructure.persistence.jpa.repositories;

import com.closedsource.psymed.platform.appointmentandadministration.domain.model.entities.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
}
