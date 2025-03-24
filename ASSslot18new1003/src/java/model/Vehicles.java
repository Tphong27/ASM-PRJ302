/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import dao.*;

/**
 *
 * @author Admin
 */
public class Vehicles {

    private int VehicleID;
    private int OwnerID;
    private String PlateNumber;
    private int BrandID;
    private int ModelID;
    private int ManufactureYear;
    private String EngineNumber;

    private InspectionRecords InspectionRecords;
    private Brand Brand;
    private Model Model;
    private Users Users;

    public Vehicles() {
    }

    public Vehicles(int VehicleID, int OwnerID, String PlateNumber, int BrandID, int ModelID, int ManufactureYear, String EngineNumber) {
        this.VehicleID = VehicleID;
        this.OwnerID = OwnerID;
        this.PlateNumber = PlateNumber;
        this.BrandID = BrandID;
        this.ModelID = ModelID;
        this.ManufactureYear = ManufactureYear;
        this.EngineNumber = EngineNumber;
    }

    public Vehicles(int OwnerID, String PlateNumber, int BrandID, int ModelID, int ManufactureYear, String EngineNumber) {
        this.OwnerID = OwnerID;
        this.PlateNumber = PlateNumber;
        this.BrandID = BrandID;
        this.ModelID = ModelID;
        this.ManufactureYear = ManufactureYear;
        this.EngineNumber = EngineNumber;
    }

    public Users getUsers() {
        return Users;
    }

    public void includeUsers() {
        this.Users = UsersDAO.getUserByID(OwnerID);
    }

    public Brand getBrand() {
        return Brand;
    }

    public void includeBrand() {
        this.Brand = BrandDAO.getBrandByBrandId(BrandID);
    }

    public Model getModel() {
        return Model;
    }

    public void includeModel() {
        this.Model = ModelDAO.getModelByModelId(ModelID);
    }

    public InspectionRecords getInspectionRecords() {
        return InspectionRecords;
    }

    public void includenspectionRecords() { //setInspectionRecords()
        this.InspectionRecords = InspectionRecordsDAO.getInspectionRecordsByVehicleId(this.VehicleID);
        if (this.InspectionRecords == null) {
            this.InspectionRecords = new InspectionRecords();
            this.InspectionRecords.setResult("Not registered for emissions");
        } else {
            this.InspectionRecords.includeInspectionStations();
        }
    }

    public int getVehicleID() {
        return VehicleID;
    }

    public void setVehicleID(int VehicleID) {
        this.VehicleID = VehicleID;
    }

    public int getOwnerID() {
        return OwnerID;
    }

    public void setOwnerID(int OwnerID) {
        this.OwnerID = OwnerID;
    }

    public String getPlateNumber() {
        return PlateNumber;
    }

    public void setPlateNumber(String PlateNumber) {
        this.PlateNumber = PlateNumber;
    }

    public int getBrandID() {
        return BrandID;
    }

    public void setBrandID(int BrandID) {
        this.BrandID = BrandID;
    }

    public int getModelID() {
        return ModelID;
    }

    public void setModelID(int ModelID) {
        this.ModelID = ModelID;
    }

    public int getManufactureYear() {
        return ManufactureYear;
    }

    public void setManufactureYear(int ManufactureYear) {
        this.ManufactureYear = ManufactureYear;
    }

    public String getEngineNumber() {
        return EngineNumber;
    }

    public void setEngineNumber(String EngineNumber) {
        this.EngineNumber = EngineNumber;
    }

    @Override
    public String toString() {
        return "Vehicles{" + "VehicleID=" + VehicleID + ", OwnerID=" + OwnerID + ", PlateNumber=" + PlateNumber + ", Brand=" + BrandID + ", Model=" + ModelID + ", ManufactureYear=" + ManufactureYear + ", EngineNumber=" + EngineNumber + '}';
    }

}
