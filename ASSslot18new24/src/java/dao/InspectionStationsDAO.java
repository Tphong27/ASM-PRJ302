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

    public static void main(String[] args) {
//        ArrayList<InspectionStations> ls = InspectionStationsDAO.getStations();
//        for (InspectionStations station : ls) {
//            System.out.println(station.toString());
//        }
        
        InspectionStations sta = InspectionStationsDAO.getInspectionStationsByStationId(2);
        System.out.println(sta.toString());
    }
}
