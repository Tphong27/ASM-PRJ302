/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class InspectionStations {

    private int StationID;
    private String Name;
    private String Address;
    private String Phone;
    private String Email;
    private int UserID;

    public InspectionStations() {
    }

    public InspectionStations(int StationID, String Name, String Address, String Phone, String Email, int UserID) {
        this.StationID = StationID;
        this.Name = Name;
        this.Address = Address;
        this.Phone = Phone;
        this.Email = Email;
        this.UserID = UserID;
    }

    public InspectionStations( String Name, String Address, String Phone, String Email) {
        this.Name = Name;
        this.Address = Address;
        this.Phone = Phone;
        this.Email = Email;
    }
    public InspectionStations(int StationID, int UserID) {
        this.StationID = StationID;
        this.UserID = UserID;
    }
    

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }

    public int getStationID() {
        return StationID;
    }

    public void setStationID(int StationID) {
        this.StationID = StationID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    @Override
    public String toString() {
        return "InspectionStations{" + "StationID=" + StationID + ", Name=" + Name + ", Address=" + Address + ", Phone=" + Phone + ", Email=" + Email + '}';
    }

}
