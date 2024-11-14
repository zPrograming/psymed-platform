package com.closedsource.psymed.platform.sessionnotes.domain.model.entities;

import com.closedsource.psymed.platform.appointmentandadministration.domain.model.aggregates.Session;
import com.closedsource.psymed.platform.sessionnotes.domain.model.commands.CreateNoteCommand;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Note extends AbstractAggregateRoot<Note> {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(length = 200)
    private String description;

    @Column(length = 40)
    private String symptoms;

    @ManyToOne
    @JoinColumn(name = "sessions")
    private Session session;


    public Note(String symptoms, String description) {
        this.symptoms = symptoms;
        this.description = description;
    }

    public Note(CreateNoteCommand createNoteCommand) {
        this.symptoms = createNoteCommand.symptom();
        this.description = createNoteCommand.description();
    }

    protected Note(){

    }
}
