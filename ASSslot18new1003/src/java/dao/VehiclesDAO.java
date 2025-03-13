/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Vehicles;

/**
 *
 * @author Admin
 */
public class VehiclesDAO {

    public static ArrayList<Vehicles> getVehicleByUserId(int UserId) {
        DBContext db = DBContext.getInstance();
        ArrayList<Vehicles> vehicleses = new ArrayList<Vehicles>();
        try {
            String sql = " select * from Vehicles where OwnerID = ? ";

            PreparedStatement statment = db.getConnection().prepareStatement(sql);
            statment.setInt(1, UserId);
            ResultSet rs = statment.executeQuery();
            while (rs.next()) {
                Vehicles vehicle = new Vehicles(
                        rs.getInt("VehicleID"),
                        rs.getInt("OwnerID"),
                        rs.getString("PlateNumber"),
                        rs.getInt("BrandID"),
                        rs.getInt("ModelID"),
                        rs.getInt("ManufactureYear"),
                        rs.getString("EngineNumber"));
                vehicleses.add(vehicle);
            }
        } catch (Exception e) {
            return vehicleses;
        }

        return vehicleses;
    }

    public static Vehicles getVehicleByVehicleId(int vehicleid) {
        DBContext db = DBContext.getInstance();
        ArrayList<Vehicles> vehicleses = new ArrayList<Vehicles>();
        try {
            String sql = " select * from Vehicles where VehicleID = ? ";

            PreparedStatement statment = db.getConnection().prepareStatement(sql);
            statment.setInt(1, vehicleid);
            ResultSet rs = statment.executeQuery();
            while (rs.next()) {
                Vehicles vehicle = new Vehicles(
                        rs.getInt("VehicleID"),
                        rs.getInt("OwnerID"),
                        rs.getString("PlateNumber"),
                        rs.getInt("BrandID"),
                        rs.getInt("ModelID"),
                        rs.getInt("ManufactureYear"),
                        rs.getString("EngineNumber"));
                vehicleses.add(vehicle);
            }
        } catch (Exception e) {
            return null;
        }

        if (vehicleses.isEmpty()) {
            return null;
        } else {
            return vehicleses.get(0);
        }
    }

    public static Vehicles addVehicle(Vehicles vehicle) {
        DBContext db = DBContext.getInstance();
        int rs = 0;
        try {
            String sql = """
                         insert into Vehicles values
                         (?,?,?,?,?,?)
                         """;
            PreparedStatement statment = db.getConnection().prepareStatement(sql);
            statment.setInt(1, vehicle.getOwnerID());
            statment.setString(2, vehicle.getPlateNumber());
            statment.setInt(3, vehicle.getBrandID());
            statment.setInt(4, vehicle.getModelID());
            statment.setInt(5, vehicle.getManufactureYear());
            statment.setString(6, vehicle.getEngineNumber());
            rs = statment.executeUpdate();
        } catch (SQLException e) {
            return null;
        }

        if (rs == 0) {
            return null;
        } else {
            return vehicle;
        }
    }

    public static Vehicles updateVehicles(Vehicles vehicle) {
        DBContext db = DBContext.getInstance();
        int rs = 0;
        try {
            String sql = """
                         update Vehicles
                         set PlateNumber = ?, BrandID =?, ModelID =?, ManufactureYear=?, EngineNumber =?
                         where VehicleID = ?
                         """;
            PreparedStatement statment = db.getConnection().prepareStatement(sql);
            statment.setString(1, vehicle.getPlateNumber());
            statment.setInt(2, vehicle.getBrandID());
            statment.setInt(3, vehicle.getModelID());
            statment.setInt(4, vehicle.getManufactureYear());
            statment.setString(5, vehicle.getEngineNumber());
            statment.setInt(6, vehicle.getVehicleID());
            rs = statment.executeUpdate();
        } catch (Exception e) {
            return null;
        }

        if (rs == 0) {
            return null;
        } else {
            return vehicle;
        }
    }

    public static ArrayList<Vehicles> ListAllVehicle() {
        DBContext db = DBContext.getInstance();
        ArrayList<Vehicles> vehicleses = new ArrayList<Vehicles>();

        try {
            String sql = """
                         select * from Vehicles
                         """;
            PreparedStatement statment = db.getConnection().prepareStatement(sql);
            ResultSet rs = statment.executeQuery();
            while (rs.next()) {
                Vehicles vehicle = new Vehicles(
                        rs.getInt("VehicleID"),
                        rs.getInt("OwnerID"),
                        rs.getString("PlateNumber"),
                        rs.getInt("BrandID"),
                        rs.getInt("ModelID"),
                        rs.getInt("ManufactureYear"),
                        rs.getString("EngineNumber"));
                vehicleses.add(vehicle);
            }
        } catch (Exception e) {
            return null;
        }

        if (vehicleses.isEmpty()) {
            return null;
        } else {
            return vehicleses;
        }
    }

    public static ArrayList<Vehicles> getVehiclesByPlateNumber(String pattern) {
        DBContext db = DBContext.getInstance();
        ArrayList<Vehicles> vehicles = new ArrayList<Vehicles>();

        try {
            String sql = """
                         select * from Vehicles
                         where PlateNumber like ?
                         """;
            PreparedStatement statment = db.getConnection().prepareStatement(sql);
            String plateNumber = "%" + pattern + "%";
            statment.setString(1, plateNumber);
            ResultSet rs = statment.executeQuery();
            while (rs.next()) {
                Vehicles vehicle = new Vehicles(
                        rs.getInt("VehicleID"),
                        rs.getInt("OwnerID"),
                        rs.getString("PlateNumber"),
                        rs.getInt("BrandID"),
                        rs.getInt("ModelID"),
                        rs.getInt("ManufactureYear"),
                        rs.getString("EngineNumber"));
                vehicles.add(vehicle);
            }
        } catch (Exception e) {
            return vehicles;
        }
        return vehicles;

    }

    public static boolean isValidPlateNumber(String plateNumber) {
        if (plateNumber == null || plateNumber.isEmpty()) {
            return false;
        }

        return plateNumber.matches("^(\\d{2}[A-Z])-?\\d{4,5}$");
    }

}
