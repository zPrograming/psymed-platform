package com.closedsource.psymed.platform.patientreport.domain.model.entities;

import com.closedsource.psymed.platform.patientreport.domain.model.valueobjects.MoodStatus;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

public class MoodState {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    MoodStatus moodStatus;

    @NotNull
    private Long patientId;

    public MoodState(Long patientId) {
        this.patientId = patientId;
    }

    public boolean hasAnState() {
        return this.moodStatus != null;
    }

    public void soSad() {
        if(hasAnState())
            throw new IllegalArgumentException("The patient already has a mood state");
        this.moodStatus = MoodStatus.SOSAD;

    }

    public void sad() {
        if(hasAnState())
            throw new IllegalArgumentException("The patient already has a mood state");
        this.moodStatus = MoodStatus.SAD;
    }

    public void normal() {
        if(hasAnState())
            throw new IllegalArgumentException("The patient already has a mood state");
        this.moodStatus = MoodStatus.NORMAL;
    }

    public void happy() {
        if(hasAnState())
            throw new IllegalArgumentException("The patient already has a mood state");
        this.moodStatus = MoodStatus.HAPPY;
    }

    public void soHappy() {
        if(hasAnState())
            throw new IllegalArgumentException("The patient already has a mood state");
        this.moodStatus = MoodStatus.SOHAPPY;
    }



}
