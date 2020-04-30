package com.example.fresh.models;

public class LocationStats {
    private String State;
    private int Confirmed;
    private int Recovered;
    private int Deceased;
    private int Active;

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public int getConfirmed() {
        return Confirmed;
    }

    public void setConfirmed(int confirmed) {
        Confirmed = confirmed;
    }

    public int getRecovered() {
        return Recovered;
    }

    public void setRecovered(int recovered) {
        Recovered = recovered;
    }

    public int getDeceased() {
        return Deceased;
    }

    public void setDeceased(int deceased) {
        Deceased = deceased;
    }

    public int getActive() {
        return Active;
    }

    public void setActive(int active) {
        Active = active;
    }

    @Override
    public String toString() {
        return "LocationStats{" +
                "State='" + State + '\'' +
                ", Confirmed=" + Confirmed +
                ", Recovered=" + Recovered +
                ", Deceased=" + Deceased +
                ", Active=" + Active +
                '}';
    }
}
