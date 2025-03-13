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

    public static Brand addBrand(Brand brand) {
        DBContext db = DBContext.getInstance();
        int rs = 0;
        try {
            String sql = """
                         INSERT INTO Brand (BrandName) VALUES
                         (?)
                         """;
            PreparedStatement statment = db.getConnection().prepareStatement(sql);
            statment.setString(1, brand.getBrandName());
            rs = statment.executeUpdate();
        } catch (Exception e) {
            return null;
        }

        if (rs == 0) {
            return null;
        } else {
            return brand;
        }
    }

    public static Brand updateBrand(Brand brand) {
        DBContext db = DBContext.getInstance();
        int rs = 0;
        try {
            String sql = """
                         update Brand
                         set BrandName = ?
                         where BrandID = ?
                         """;
            PreparedStatement statment = db.getConnection().prepareStatement(sql);
            statment.setString(1, brand.getBrandName());
            statment.setInt(2, brand.getBrandID());
            rs = statment.executeUpdate();
        } catch (Exception e) {
            return null;
        }

        if (rs == 0) {
            return null;
        } else {
            return brand;
        }
    }

    public static Brand deleteBrand(Brand brand) {
        DBContext db = DBContext.getInstance();
        int rs = 0;
        try {
            String sql = """
                         delete from Brand
                         where BrandID = ?
                         """;
            PreparedStatement statment = db.getConnection().prepareStatement(sql);
            statment.setInt(1, brand.getBrandID());
            rs = statment.executeUpdate();
        } catch (Exception e) {
            return null;
        }

        if (rs == 0) {
            return null;
        } else {
            return brand;
        }
    }
}
