package com.example.mateusz.as.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Cowshed {

    @SerializedName("idCowshed")
    private long idCowshed;
    @SerializedName("address")
    private String address;
    @SerializedName("name")
    private String name;
    @SerializedName("info")
    private String info;
    @SerializedName("teamList")
    private List<Integer> teamList = new ArrayList<>();

    public Cowshed() {
    }

    public Cowshed(Integer idCowshed, String address, String name, String info, List<Integer> teamList) {
        this.idCowshed = idCowshed;
        this.address = address;
        this.name = name;
        this.info = info;
        this.teamList = teamList;
    }

    public long getIdCowshed() {
        return idCowshed;
    }

    public void setIdCowshed(Integer idCowshed) {
        this.idCowshed = idCowshed;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public List<Integer> getTeamList() {
        return teamList;
    }

    public void setTeamList(List<Integer> teamList) {
        this.teamList = teamList;
    }

}
