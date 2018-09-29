package com.learning.emrez.meditime;

import java.util.Date;

public class Medicine {
    private Date date;
    private String medName;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMedName() {
        return medName;
    }

    public void setMedName(String medName) {
        this.medName = medName;
    }

    public Medicine(String theMedName){
        medName = theMedName;
    }
}
