package com.closedsource.psymed.platform.treatment.domain.model.valueobjects;

import com.closedsource.psymed.platform.treatment.domain.model.entities.Pill;

import java.util.ArrayList;
import java.util.List;

public class MedicalPrescription {

    private final List<Pill> pills;

    public MedicalPrescription(){
        this.pills = new ArrayList<>();
    }

    public void addPill(Pill pill){
        if(pill == null) throw new IllegalStateException("Pill cannot be null");
        this.pills.add(pill);

    }


}
