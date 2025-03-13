package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Brand;

/**
 *
 * @author Admin
 */
public class BrandDAO {

    public static Brand getBrandByBrandId(int brandid) {
        DBContext db = DBContext.getInstance();
        ArrayList<Brand> brands = new ArrayList<Brand>();

        try {
            String sql = """
                         select * from Brand
                         where BrandID = ?
                         """;
            PreparedStatement statment = db.getConnection().prepareStatement(sql);
            statment.setInt(1, brandid);
            ResultSet rs = statment.executeQuery();
            while (rs.next()) {
                Brand brand = new Brand(
                        rs.getInt("BrandID"),
                        rs.getString("BrandName"));
                brands.add(brand);
            }
        } catch (Exception e) {
            return null;
        }

        if (brands.isEmpty()) {
            return null;
        } else {
            return brands.get(0);
        }
    }

    public static ArrayList<Brand> getBrands() {
        DBContext db = DBContext.getInstance();
        ArrayList<Brand> brands = new ArrayList<Brand>();

        try {
            String sql = """
                         select * from Brand
                         """;
            PreparedStatement statment = db.getConnection().prepareStatement(sql);
            ResultSet rs = statment.executeQuery();
            while (rs.next()) {
                Brand brand = new Brand(
                        rs.getInt("BrandID"),
                        rs.getString("BrandName"));
                brands.add(brand);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        if (brands.isEmpty()) {
            return null;
        } else {
            return brands;
        }
    }
    
}
