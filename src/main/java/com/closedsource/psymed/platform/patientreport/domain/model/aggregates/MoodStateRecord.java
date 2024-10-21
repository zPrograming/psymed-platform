package com.closedsource.psymed.platform.patientreport.domain.model.aggregates;

import com.closedsource.psymed.platform.patientreport.domain.model.commands.CreateMoodStateRecordCommand;
import com.closedsource.psymed.platform.patientreport.domain.model.valueobjects.MoodStatus;
import com.closedsource.psymed.platform.patientreport.domain.model.valueobjects.PatientId;
import com.closedsource.psymed.platform.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Entity
@EntityListeners(AuditableModel.class)
public class MoodStateRecord extends AuditableModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    MoodStatus moodStatus;

    @Getter
    @NotNull
    @Embedded
    private PatientId patientId;

    public MoodStateRecord() {
        this.patientId = null;
        this.moodStatus = null;
    }

    public MoodStateRecord(Long patientId, Integer moodStatus) {
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

    public void validateDay(MoodStateRecord lastMoodState) {
        if (lastMoodState.getCreatedAt() == this.getCreatedAt())
            throw new IllegalArgumentException("You can't record mood state twice in the same day");
    }


}
