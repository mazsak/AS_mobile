package com.example.mateusz.as.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Team {

    @SerializedName("idGroup")
    private long idGroup;
    @SerializedName("name")
    private String name;
    @SerializedName("type")
    private String type;
    @SerializedName("cattleList")
    private List<Integer> cattleList = new ArrayList<>();
    @SerializedName("idCowshed")
    private long idCowshed;

    public Team() {

    }

    public Team(Integer idGroup, String name, String type, List<Integer> cattleList, Integer idCowshed) {
        this.idGroup = idGroup;
        this.name = name;
        this.type = type;
        this.cattleList = cattleList;
        this.idCowshed = idCowshed;
    }

    public long getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(Integer idGroup) {
        this.idGroup = idGroup;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Integer> getCattleList() {
        return cattleList;
    }

    public void setCattleList(List<Integer> cattleList) {
        this.cattleList = cattleList;
    }

    public long getIdCowshed() {
        return idCowshed;
    }

    public void setIdCowshed(Integer idCowshed) {
        this.idCowshed = idCowshed;
    }
}
