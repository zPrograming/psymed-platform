package com.closedsource.psymed.platform.sessionnotes.domain.model.entities;


import com.closedsource.psymed.platform.appointmentandadministration.domain.model.aggregates.Session;
import com.closedsource.psymed.platform.sessionnotes.domain.model.commands.CreateTaskCommand;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String description;

    @Column
    private boolean completionStatus;

    @ManyToOne
    @JoinColumn(name = "sessions")
    private Session session;

    protected Task() {
    }


    public Task(String title, String description, boolean completionStatus){
        this.title = title;
        this.description = description;
        this.completionStatus = completionStatus;
    }

    public Task(CreateTaskCommand createTaskCommand){
        this.title = createTaskCommand.title();
        this.description = createTaskCommand.description();
        this.completionStatus = createTaskCommand.completionStatus();
    }


}
