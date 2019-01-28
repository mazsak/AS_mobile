package com.example.mateusz.as.models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class Insemination {
    @SerializedName("idInsemination")
    private long idInsemination;
    @SerializedName("inseminationDate")
    private Date inseminationDate;
    @SerializedName("result")
    private String result;
    @SerializedName("v")
    private String notes;
    @SerializedName("idBull")
    private long idBull;
    @SerializedName("idCattle")
    private long idCattle;

    public Insemination() {

    }

    public Insemination(Integer idInsemination, Date inseminationDate, String result, String notes, long idBull, long idCattle) {
        this.idInsemination = idInsemination;
        this.inseminationDate = inseminationDate;
        this.result = result;
        this.notes = notes;
        this.idBull = idBull;
        this.idCattle = idCattle;
    }

    public long getIdInsemination() {
        return idInsemination;
    }

    public void setIdInsemination(Integer idInsemination) {
        this.idInsemination = idInsemination;
    }

    public Date getInseminationDate() {
        return inseminationDate;
    }

    public void setInseminationDate(Date inseminationDate) {
        this.inseminationDate = inseminationDate;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public long getIdBull() {
        return idBull;
    }

    public void setIdBull(long idBull) {
        this.idBull = idBull;
    }

    public long getIdCattle() {
        return idCattle;
    }

    public void setIdCattle(long idCattle) {
        this.idCattle = idCattle;
    }

}
