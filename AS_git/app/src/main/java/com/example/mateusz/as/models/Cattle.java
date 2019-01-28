package com.example.mateusz.as.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    @SerializedName("teamList")
    private List<Integer> teamList = new ArrayList<>();
    @SerializedName("inseminationList")
    private List<Integer> inseminationList = new ArrayList<>();
    @SerializedName("idMother")
    private long idMother;
    @SerializedName("calvingList")
    private List<Integer> calvingList = new ArrayList<>();
    @SerializedName("statsDailyList")
    private List<Integer> statsDailyList = new ArrayList<>();
    @SerializedName("treatmentList")
    private List<Integer> treatmentList = new ArrayList<>();
    @SerializedName("statsMonthlyList")
    private List<Integer> statsMonthlyList = new ArrayList<>();

    public Cattle() {

    }

    public Cattle(long idCattle, String name, String earring, String sex, Integer cowshedNumber, Date birthDate, Date joinDate, Date leaveDate, String leaveReason, String notes, List<Integer> teamList, List<Integer> inseminationList, long idMother, List<Integer> calvingList, List<Integer> statsDailyList, List<Integer> treatmentList, List<Integer> statsMonthlyList) {
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
        this.teamList = teamList;
        this.inseminationList = inseminationList;
        this.idMother = idMother;
        this.calvingList = calvingList;
        this.statsDailyList = statsDailyList;
        this.treatmentList = treatmentList;
        this.statsMonthlyList = statsMonthlyList;
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

    public List<Integer> getTeamList() {
        return teamList;
    }

    public void setTeamList(List<Integer> teamList) {
        this.teamList = teamList;
    }

    public List<Integer> getInseminationList() {
        return inseminationList;
    }

    public void setInseminationList(List<Integer> inseminationList) {
        this.inseminationList = inseminationList;
    }

    public long getIdMother() {
        return idMother;
    }

    public void setIdMother(long idMother) {
        this.idMother = idMother;
    }

    public List<Integer> getCalvingList() {
        return calvingList;
    }

    public void setCalvingList(List<Integer> calvingList) {
        this.calvingList = calvingList;
    }

    public List<Integer> getStatsDailyList() {
        return statsDailyList;
    }

    public void setStatsDailyList(List<Integer> statsDailyList) {
        this.statsDailyList = statsDailyList;
    }

    public List<Integer> getTreatmentList() {
        return treatmentList;
    }

    public void setTreatmentList(List<Integer> treatmentList) {
        this.treatmentList = treatmentList;
    }

    public List<Integer> getStatsMonthlyList() {
        return statsMonthlyList;
    }

    public void setStatsMonthlyList(List<Integer> statsMonthlyList) {
        this.statsMonthlyList = statsMonthlyList;
    }
}
