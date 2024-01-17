package com.example.ambulanceservice_app;

public class NewAmbulanceClass {
    private String Id;
    private String ambulanceName;
    private String vehicleNum;
    private String saetedFor;
    private String price_km;
    private String hospitalName;
    private String imageId;

    public String getAreaname() {
        return areaname;
    }

    public void setAreaname(String areaname) {
        this.areaname = areaname;
    }

    private String areaname;

    public NewAmbulanceClass() {
    }

    public NewAmbulanceClass(String id, String ambulanceName, String vehicleNum, String saetedFor, String price_km, String hospitalName, String imageId, String areaname) {
        Id = id;
        this.ambulanceName = ambulanceName;
        this.vehicleNum = vehicleNum;
        this.saetedFor = saetedFor;
        this.price_km = price_km;
        this.hospitalName = hospitalName;
        this.imageId = imageId;
        this.areaname = areaname;
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

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }
}
