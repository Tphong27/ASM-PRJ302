/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;
import model.InspectionStations;
import dao.*;

/**
 *
 * @author Admin
 */
public class InspectionRecords {

    private int RecordID;
    private int VehicleID;
    private int StationID;
    private int InspectorID;
    private Date RegistrationDate;
    private Date InspectionDate;
    private String Result;
    private double CO2Emission;
    private double HCEmission;
    private String Comments;

    private InspectionStations InspectionStations;
    private Vehicles Vehicles;
    private Users Users;

    public InspectionRecords() {
    }

    public InspectionRecords(int RecordID, int VehicleID, int StationID, int InspectorID, Date RegistrationDate, Date InspectionDate, String Result, double CO2Emission, double HCEmission, String Comments) {
        this.RecordID = RecordID;
        this.VehicleID = VehicleID;
        this.StationID = StationID;
        this.InspectorID = InspectorID;
        this.RegistrationDate = RegistrationDate;
        this.InspectionDate = InspectionDate;
        this.Result = Result;
        this.CO2Emission = CO2Emission;
        this.HCEmission = HCEmission;
        this.Comments = Comments;
    }

    public InspectionRecords(int RecordID, int VehicleID, int StationID, int InspectorID, Date InspectionDate, String Result, double CO2Emission, double HCEmission) {
        this.RecordID = RecordID;
        this.VehicleID = VehicleID;
        this.StationID = StationID;
        this.InspectorID = InspectorID;
        this.InspectionDate = InspectionDate;
        this.Result = Result;
        this.CO2Emission = CO2Emission;
        this.HCEmission = HCEmission;
    }

    public InspectionRecords(int VehicleID, int StationID, int InspectorID, Date RegistrationDate, Date InspectionDate, String Result, double CO2Emission, double HCEmission, String Comments) {
        this.VehicleID = VehicleID;
        this.StationID = StationID;
        this.InspectorID = InspectorID;
        this.RegistrationDate = RegistrationDate;
        this.InspectionDate = InspectionDate;
        this.Result = Result;
        this.CO2Emission = CO2Emission;
        this.HCEmission = HCEmission;
        this.Comments = Comments;
    }

    public InspectionRecords(int VehicleID, int StationID, int InspectorID, Date RegistrationDate, String Result, double CO2Emission, double HCEmission) {
        this.VehicleID = VehicleID;
        this.StationID = StationID;
        this.InspectorID = InspectorID;
        this.RegistrationDate = RegistrationDate;
        this.Result = Result;
        this.CO2Emission = CO2Emission;
        this.HCEmission = HCEmission;
    }

    public InspectionRecords(int VehicleID, int StationID, Date RegistrationDate, String Result, double CO2Emission, double HCEmission) {
        this.VehicleID = VehicleID;
        this.StationID = StationID;
        this.RegistrationDate = RegistrationDate;
        this.Result = Result;
        this.CO2Emission = CO2Emission;
        this.HCEmission = HCEmission;
    }

    public Users getUsers() {
        return Users;
    }

    public void includeUsers() {
        this.Users = UsersDAO.getUserByID(InspectorID);
    }

    public InspectionStations getInspectionStations() {
        return InspectionStations;
    }

    public void includeInspectionStations() {
        this.InspectionStations = InspectionStationsDAO.getInspectionStationsByStationId(this.StationID);
    }

    public Vehicles getVehicles() {
        return Vehicles;
    }

    public void includeVehicles() {
        this.Vehicles = VehiclesDAO.getVehicleByVehicleId(this.VehicleID);
        if (this.Vehicles != null) {
            this.Vehicles.includeBrand();
            this.Vehicles.includeModel();
        }
    }

    public Date getRegistrationDate() {
        return RegistrationDate;
    }

    public void setRegistrationDate(Date RegistrationDate) {
        this.RegistrationDate = RegistrationDate;
    }

    public int getRecordID() {
        return RecordID;
    }

    public void setRecordID(int RecordID) {
        this.RecordID = RecordID;
    }

    public int getVehicleID() {
        return VehicleID;
    }

    public void setVehicleID(int VehicleID) {
        this.VehicleID = VehicleID;
    }

    public int getStationID() {
        return StationID;
    }

    public void setStationID(int StationID) {
        this.StationID = StationID;
    }

    public int getInspectorID() {
        return InspectorID;
    }

    public void setInspectorID(int InspectorID) {
        this.InspectorID = InspectorID;
    }

    public Date getInspectionDate() {
        return InspectionDate;
    }

    public void setInspectionDate(Date InspectionDate) {
        this.InspectionDate = InspectionDate;
    }

    public String getResult() {
        return Result;
    }

    public void setResult(String Result) {
        this.Result = Result;
    }

    public double getCO2Emission() {
        return CO2Emission;
    }

    public void setCO2Emission(double CO2Emission) {
        this.CO2Emission = CO2Emission;
    }

    public double getHCEmission() {
        return HCEmission;
    }

    public void setHCEmission(double HCEmission) {
        this.HCEmission = HCEmission;
    }

    public String getComments() {
        return Comments;
    }

    public void setComments(String Comments) {
        this.Comments = Comments;
    }

    @Override
    public String toString() {
        return "InspectionRecords{" + "RecordID=" + RecordID + ", VehicleID=" + VehicleID + ", StationID=" + StationID + ", InspectorID=" + InspectorID + ", RegistrationDate=" + RegistrationDate + ", InspectionDate=" + InspectionDate + ", Result=" + Result + ", CO2Emission=" + CO2Emission + ", HCEmission=" + HCEmission + ", Comments=" + Comments + '}';
    }

}
