/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Model;

/**
 *
 * @author Admin
 */
public class ModelDAO {

    public static Model getModelByModelId(int modelid) {
        DBContext db = DBContext.getInstance();
        ArrayList<Model> models = new ArrayList<Model>();

        try {
            String sql = """
                         select * from Model
                         where ModelID = ?
                         """;
            PreparedStatement statment = db.getConnection().prepareStatement(sql);
            statment.setInt(1, modelid);
            ResultSet rs = statment.executeQuery();
            while (rs.next()) {
                Model model = new Model(
                        rs.getInt("ModelID"),
                        rs.getString("ModelName"));
                models.add(model);
            }
        } catch (Exception e) {
            return null;
        }

        if (models.isEmpty()) {
            return null;
        } else {
            return models.get(0);
        }
    }
    
    public static ArrayList<Model> getModels() {
        DBContext db = DBContext.getInstance();
        ArrayList<Model> models = new ArrayList<Model>();

        try {
            String sql = """
                         select * from Model
                         """;
            PreparedStatement statment = db.getConnection().prepareStatement(sql);
            ResultSet rs = statment.executeQuery();
            while (rs.next()) {
                Model model = new Model(
                        rs.getInt("ModelID"),
                        rs.getString("ModelName"));
                models.add(model);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        if (models.isEmpty()) {
            return null;
        } else {
            return models;
        }
    }
    
}
