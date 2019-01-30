package com.example.mateusz.as.models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Cattle {

    @SerializedName("idCattle")
    private long idCattle;
    @SerializedName("name")
    private String name;
    @SerializedName("earring")
    private String earring;
    @SerializedName("sex")
    private String sex;
    @SerializedName("cowshedNumber")
    private Integer cowshedNumber;
    @SerializedName("birthDate")
    private Date birthDate;
    @SerializedName("joinDate")
    private Date joinDate;
    @SerializedName("leaveDate")
    private Date leaveDate;
    @SerializedName("leaveReason")
    private String leaveReason;
    @SerializedName("notes")
    private String notes;
    @SerializedName("team")
    private long team;

    public Cattle() {

    }

    public Cattle(long idCattle, String name, String earring, String sex, Integer cowshedNumber, Date birthDate, Date joinDate, Date leaveDate, String leaveReason, String notes, Integer team) {
        this.idCattle = idCattle;
        this.name = name;
        this.earring = earring;
        this.sex = sex;
        this.cowshedNumber = cowshedNumber;
        this.birthDate = birthDate;
        this.joinDate = joinDate;
        this.leaveDate = leaveDate;
        this.leaveReason = leaveReason;
        this.notes = notes;
        this.team = team;

    }

    public long getIdCattle() {
        return idCattle;
    }

    public void setIdCattle(long idCattle) {
        this.idCattle = idCattle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEarring() {
        return earring;
    }

    public void setEarring(String earring) {
        this.earring = earring;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getCowshedNumber() {
        return cowshedNumber;
    }

    public void setCowshedNumber(Integer cowshedNumber) {
        this.cowshedNumber = cowshedNumber;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public Date getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(Date leaveDate) {
        this.leaveDate = leaveDate;
    }

    public String getLeaveReason() {
        return leaveReason;
    }

    public void setLeaveReason(String leaveReason) {
        this.leaveReason = leaveReason;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public long getTeam() {
        return team;
    }

    public void setTeam(Integer team) {
        this.team = team;
    }

}
