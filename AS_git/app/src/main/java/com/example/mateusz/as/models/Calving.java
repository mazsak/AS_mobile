package com.example.mateusz.as.models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class Calving {

    @SerializedName("idCalving")
    private long idCalving;
    @SerializedName("calvingDate")
    private Date calvingDate;
    @SerializedName("notes")
    private String notes;
    @SerializedName("idCalf")
    private long idCalf;
    @SerializedName("idCattle")
    private long idCattle;

    public Calving() {

    }

    public Calving(long idCalving, Date calvingDate, String notes, long idCattle, long idCalf) {
        this.idCalving = idCalving;
        this.calvingDate = calvingDate;
        this.notes = notes;
        this.idCattle = idCattle;
        this.idCalf = idCalf;
    }

    public long getIdCalving() {
        return idCalving;
    }

    public void setIdCalving(long idCalving) {
        this.idCalving = idCalving;
    }

    public Date getCalvingDate() {
        return calvingDate;
    }

    public void setCalvingDate(Date calvingDate) {
        this.calvingDate = calvingDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public long getIdCattle() {
        return idCattle;
    }

    public void setIdCattle(long idCattle) {
        this.idCattle = idCattle;
    }

    public long getIdCalf() {
        return idCalf;
    }

    public void setIdCalf(long idCalf) {
        this.idCalf = idCalf;
    }
}
