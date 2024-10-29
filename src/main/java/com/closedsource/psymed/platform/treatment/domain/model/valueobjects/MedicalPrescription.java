package com.closedsource.psymed.platform.treatment.domain.model.valueobjects;

import com.closedsource.psymed.platform.treatment.domain.model.entities.Pill;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Embeddable
public class MedicalPrescription {

    @OneToMany(mappedBy = "medication", cascade = CascadeType.ALL)
    @JoinColumn(name = "medication_id")
    private final List<Pill> pills;

    public MedicalPrescription(){
        this.pills = new ArrayList<>();
    }


    public void addPill(Pill pill){
        if(pill == null) throw new IllegalStateException("Pill cannot be null");
        this.pills.add(pill);
    }


    public Pill getLastPillInMedicalPrescription() {
        if (pills.isEmpty()) {
            return null;
        }
        return pills.get(pills.size() - 1);
    }

    public void removePill(Pill pill){
        if(pill == null) throw new IllegalStateException("Pill cannot be null");
        this.pills.remove(pill);
    }



    public boolean isEmpty(){ return pills.isEmpty();}


}
