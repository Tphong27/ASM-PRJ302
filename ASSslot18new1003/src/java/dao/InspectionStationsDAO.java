/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.InspectionStations;

/**
 *
 * @author Admin
 */
public class InspectionStationsDAO {

    public static InspectionStations getInspectionStationsByStationId(int stationId) {
        DBContext db = DBContext.getInstance();
        ArrayList<InspectionStations> stations = new ArrayList<InspectionStations>();

        try {
            String sql = """
                         select * from InspectionStations
                         where StationID = ?
                         """;
            PreparedStatement statment = db.getConnection().prepareStatement(sql);
            statment.setInt(1, stationId);
            ResultSet rs = statment.executeQuery();
            while (rs.next()) {
                InspectionStations station = new InspectionStations(
                        rs.getInt("StationID"),
                        rs.getString("Name"),
                        rs.getString("Address"),
                        rs.getString("Phone"),
                        rs.getString("Email"),
                        rs.getInt("UserID")
                );
                stations.add(station);
            }
        } catch (Exception e) {
            return null;
        }

        if (stations.isEmpty()) {
            return null;
        } else {
            return stations.get(0);
        }
    }

    public static InspectionStations getInspectionStationsByUserId(int userId) {
        DBContext db = DBContext.getInstance();
        ArrayList<InspectionStations> stations = new ArrayList<InspectionStations>();

        try {
            String sql = """
                         select * from InspectionStations
                         where UserID = ?
                         """;
            PreparedStatement statment = db.getConnection().prepareStatement(sql);
            statment.setInt(1, userId);
            ResultSet rs = statment.executeQuery();
            while (rs.next()) {
                InspectionStations station = new InspectionStations(
                        rs.getInt("StationID"),
                        rs.getString("Name"),
                        rs.getString("Address"),
                        rs.getString("Phone"),
                        rs.getString("Email"),
                        rs.getInt("UserID")
                );
                stations.add(station);
            }
        } catch (Exception e) {
            return null;
        }

        if (stations.isEmpty()) {
            return null;
        } else {
            return stations.get(0);
        }
    }

    public static ArrayList<InspectionStations> getStations() {
        DBContext db = DBContext.getInstance();
        ArrayList<InspectionStations> stations = new ArrayList<InspectionStations>();

        try {
            String sql = """
                         select * from InspectionStations                         
                         """;
            PreparedStatement statment = db.getConnection().prepareStatement(sql);
            ResultSet rs = statment.executeQuery();
            while (rs.next()) {
                InspectionStations station = new InspectionStations(
                        rs.getInt("StationID"),
                        rs.getString("Name"),
                        rs.getString("Address"),
                        rs.getString("Phone"),
                        rs.getString("Email"),
                        rs.getInt("UserID")
                );
                stations.add(station);
            }
        } catch (Exception e) {
            return null;
        }

        if (stations.isEmpty()) {
            return null;
        } else {
            return stations;
        }
    }

    public static InspectionStations addStation(InspectionStations station) {
        DBContext db = DBContext.getInstance();
        int rs = 0;
        try {
            String sql = """
                         INSERT INTO InspectionStations (Name, Address, Phone, Email) VALUES
                         (?, ?, ?, ?)
                         """;
            PreparedStatement statment = db.getConnection().prepareStatement(sql);
            statment.setString(1, station.getName());
            statment.setString(2, station.getAddress());
            statment.setString(3, station.getPhone());
            statment.setString(4, station.getEmail());
            rs = statment.executeUpdate();
        } catch (Exception e) {
            return null;
        }

        if (rs == 0) {
            return null;
        } else {
            return station;
        }
    }

    public static InspectionStations updateStation(InspectionStations station) {
        DBContext db = DBContext.getInstance();
        int rs = 0;
        try {
            String sql = """
                         update InspectionStations
                         set Name = ?, Address =?, Phone=?, Email=?
                         where StationID = ?
                         """;
            PreparedStatement statment = db.getConnection().prepareStatement(sql);
            statment.setString(1, station.getName());
            statment.setString(2, station.getAddress());
            statment.setString(3, station.getPhone());
            statment.setString(4, station.getEmail());
            statment.setInt(5, station.getStationID());

            rs = statment.executeUpdate();
        } catch (Exception e) {
            return null;
        }

        if (rs == 0) {
            return null;
        } else {
            return station;
        }
    }

    public static InspectionStations deleteStation(InspectionStations station) {
        DBContext db = DBContext.getInstance();
        int rs = 0;
        try {
            String sql = """
                         delete from InspectionStations
                         where StationID = ?
                         """;
            PreparedStatement statment = db.getConnection().prepareStatement(sql);
            statment.setInt(1, station.getStationID());
            rs = statment.executeUpdate();
        } catch (Exception e) {
            return null;
        }

        if (rs == 0) {
            return null;
        } else {
            return station;
        }
    }

    public static ArrayList<InspectionStations> getStationsNoStaff() {
        DBContext db = DBContext.getInstance();
        ArrayList<InspectionStations> stations = new ArrayList<InspectionStations>();

        try {
            String sql = """
                         select *from InspectionStations
                         where Userid is null                       
                         """;
            PreparedStatement statment = db.getConnection().prepareStatement(sql);
            ResultSet rs = statment.executeQuery();
            while (rs.next()) {
                InspectionStations station = new InspectionStations(
                        rs.getInt("StationID"),
                        rs.getString("Name"),
                        rs.getString("Address"),
                        rs.getString("Phone"),
                        rs.getString("Email"),
                        rs.getInt("UserID")//
                );
                stations.add(station);
            }
        } catch (Exception e) {
            return null;
        }

        if (stations.isEmpty()) {
            return null;
        } else {
            return stations;
        }
    }

    public static InspectionStations updateStationStaff(InspectionStations station) {
        DBContext db = DBContext.getInstance();
        int rs = 0;
        try {
            String sql = """
                         update InspectionStations
                         set UserID = ?
                         where StationID = ?
                         """;
            PreparedStatement statment = db.getConnection().prepareStatement(sql);
            statment.setInt(1, station.getUserID());
            statment.setInt(2, station.getStationID());
            rs = statment.executeUpdate();
        } catch (Exception e) {
            return null;
        }

        if (rs == 0) {
            return null;
        } else {
            return station;
        }
    }
}
