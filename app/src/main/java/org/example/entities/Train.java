package org.example.entities;

import java.sql.Time;
import java.util.List;
import java.util.Map;

public class Train {
    private String trainId;

    public String trainNo;

    private List<List<Integer>> seats;

    private Map<String, String> stationTimes;

    private List<String> stations;

    public Train(String trainId , String trainNo , List<List<Integer>> seats , Map<String , String> stationTimes , List<String> stations){
        this.trainId=trainId;
        this.trainNo=trainNo;
        this.seats=seats;
        this.stationTimes=stationTimes;
        this.stations=stations;
    }

    public List<String> getStations(){
        return stations;
    }

    public List<List<Integer>> getSeats() {
        return seats;
    }

    public String getTrainId() {
        return trainId;
    }

    public Map<String, String> getStationTimes() {
        return stationTimes;
    }

    public String getTrainNo() {
        return trainNo;
    }

    public void setTrainNo(String trainNo) {
        this.trainNo = trainNo;
    }

    public void setTrainId(String trainId) {
        this.trainId = trainId;
    }

    public void setStationTimes(Map<String, String> stationTimes) {
        this.stationTimes = stationTimes;
    }

    public void setStations(List<String> stations) {
        this.stations = stations;
    }
    public void setSeats(List<List<Integer>> seats) {
        this.seats = seats;
    }

    //info method
    public String getTrainInf0(){
        return String.format("Train ID :%s Train No : %s",trainId,trainNo);
    }

}
