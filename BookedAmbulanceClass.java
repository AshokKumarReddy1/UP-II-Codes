package com.example.ambulanceservice_app;

public class BookedAmbulanceClass {
    private String Id;
    private String ambulanceName;
    private String vehicleNum;
    private String saetedFor;
    private String price_km;
    private String hospitalName;
    private String areaName;
    private String status;
    private String userId;

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAmbulanceId() {
        return ambulanceId;
    }

    public void setAmbulanceId(String ambulanceId) {
        this.ambulanceId = ambulanceId;
    }

    private String ambulanceId;

    public BookedAmbulanceClass() {
    }

    public BookedAmbulanceClass(String bookingId, String ambulanceName, String vehicleNum, String seatedFor, String price_km, String hospitalName, String status, String userId
            , String ambulanceId, String areaName) {
        this.Id = bookingId;
        this.ambulanceName = ambulanceName;
        this.vehicleNum = vehicleNum;
        this.saetedFor = seatedFor;
        this.price_km = price_km;
        this.hospitalName = hospitalName;
        this.status = status;
        this.userId=userId;
        this.ambulanceId=ambulanceId;
        this.areaName=areaName;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getAmbulanceName() {
        return ambulanceName;
    }

    public void setAmbulanceName(String ambulanceName) {
        this.ambulanceName = ambulanceName;
    }

    public String getVehicleNum() {
        return vehicleNum;
    }

    public void setVehicleNum(String vehicleNum) {
        this.vehicleNum = vehicleNum;
    }

    public String getSaetedFor() {
        return saetedFor;
    }

    public void setSaetedFor(String saetedFor) {
        this.saetedFor = saetedFor;
    }

    public String getPrice_km() {
        return price_km;
    }

    public void setPrice_km(String price_km) {
        this.price_km = price_km;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
