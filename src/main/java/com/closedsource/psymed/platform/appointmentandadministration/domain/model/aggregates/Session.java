package com.closedsource.psymed.platform.appointmentandadministration.domain.model.aggregates;

import com.closedsource.psymed.platform.appointmentandadministration.domain.model.valueobjects.PatientId;
import com.closedsource.psymed.platform.appointmentandadministration.domain.model.valueobjects.ProfessionalId;
import com.closedsource.psymed.platform.appointmentandadministration.domain.model.valueobjects.NoteId;
import com.closedsource.psymed.platform.appointmentandadministration.domain.model.valueobjects.AppointmentDate;
import com.closedsource.psymed.platform.appointmentandadministration.domain.model.valueobjects.SessionTime;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

/**
 * Session aggregate that represents a session between a patient and a professional.
 * This class encapsulates the main logic related to managing a session.
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Session extends AbstractAggregateRoot<Session> {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Id
    private Long id;

    @Embedded
    @Getter
    private PatientId patientId;

    @Embedded
    @Getter
    private ProfessionalId professionalId;

    @Embedded
    @Getter
    private NoteId noteId;

    @Embedded
    @Getter
    private AppointmentDate appointmentDate;

    @Embedded
    @Getter
    private SessionTime sessionTime;

    @Column(nullable = false, updatable = false)
    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private Date updatedAt;

    protected Session() {
    }

    /**
     * Creates a new session.
     *
     * @param patientId        The unique identifier of the patient.
     * @param professionalId   The unique identifier of the professional.
     * @param noteId           The unique identifier of the note associated with the session.
     * @param appointmentDate  The date and time of the session.
     * @param sessionTime      The duration of the session in hours.
     */
    public Session(PatientId patientId, ProfessionalId professionalId, NoteId noteId, AppointmentDate appointmentDate, SessionTime sessionTime) {
        this.patientId = patientId;
        this.professionalId = professionalId;
        this.noteId = noteId;
        this.appointmentDate = appointmentDate;
        this.sessionTime = sessionTime;
    }

    /**
     * Updates the appointment date for the session.
     *
     * @param newAppointmentDate The new date and time of the session.
     */
    public void updateAppointmentDate(AppointmentDate newAppointmentDate) {
        if (newAppointmentDate != null && newAppointmentDate.isValidAppointment()) {
            this.appointmentDate = newAppointmentDate;
        }
    }

    /**
     * Updates the session time (duration).
     *
     * @param newSessionTime The new duration of the session.
     */
    public void updateSessionTime(SessionTime newSessionTime) {
        if (newSessionTime != null && newSessionTime.isValidDuration()) {
            this.sessionTime = newSessionTime;
        }
    }

    /**
     * Updates the note associated with the session.
     *
     * @param newNoteId The new note ID.
     */
    public void updateNoteId(NoteId newNoteId) {
        if (newNoteId != null && newNoteId.isValid()) {
            this.noteId = newNoteId;
        }
    }
}
