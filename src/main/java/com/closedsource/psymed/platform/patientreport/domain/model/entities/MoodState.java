package com.closedsource.psymed.platform.patientreport.domain.model.entities;

import com.closedsource.psymed.platform.patientreport.domain.model.valueobjects.MoodStatus;
import com.closedsource.psymed.platform.patientreport.domain.model.valueobjects.PatientId;
import com.closedsource.psymed.platform.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

public class MoodState extends AuditableModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    MoodStatus moodStatus;

    @NotNull
    private PatientId patientId;

    public MoodState() {
        this.patientId = null;
        this.moodStatus = null;
    }
    public MoodState(Long patientId, Integer moodStatus) {
        this.patientId = new PatientId(patientId);
        validateMoodStatus(moodStatus);
        switch(moodStatus) {
            case 0:
                this.moodStatus = MoodStatus.SOSAD;
                break;
            case 1:
                this.moodStatus = MoodStatus.SAD;
                break;
            case 2:
                this.moodStatus = MoodStatus.NORMAL;
                break;
            case 3:
                this.moodStatus = MoodStatus.HAPPY;
                break;
            case 4:
                this.moodStatus = MoodStatus.SOHAPPY;
                break;
            default: throw new IllegalArgumentException("Invalid mood status");
        }
    }

    private void validateMoodStatus(Integer moodStatus) {
        if(moodStatus == null || moodStatus < 0 || moodStatus > 4)
            throw new IllegalArgumentException("Invalid mood status");
    }


}
