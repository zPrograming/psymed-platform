package com.closedsource.psymed.platform.appointmentandadministration.domain.model.aggregates;

import com.closedsource.psymed.platform.appointmentandadministration.domain.model.commands.CreateSessionCommand;
import com.closedsource.psymed.platform.appointmentandadministration.domain.model.valueobjects.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    private PatientId patientId;

    @Embedded
    @Getter
    @NotNull
    private ProfessionalId professionalId;

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

    protected Session() {}

    /**
     * Creates a new session from a CreateSessionCommand.
     *
     * @param command The command object containing all the necessary details to create a session.
     */
    public Session(CreateSessionCommand command) {
        // You should now directly use the patientId and professionalId from the command, no need to wrap them again
        this.patientId = new PatientId(command.patientId());
        this.professionalId = new ProfessionalId(command.professionalId());
        this.appointmentDate = command.appointmentDate();
        this.sessionTime = command.sessionTime();
    }

    public Long getPatientIdAsLong() {
        return this.patientId.patientId();
    }

    public Long getProfessionalIdAsLong() {
        return this.professionalId.professionalId();
    }

    public double getDurationAsDouble() {
        return this.sessionTime.getDurationInHours();
    }
}
