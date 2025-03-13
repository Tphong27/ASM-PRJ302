package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Roles;

public class RoleDAO {

    public static Roles getRoleByRoleId(int roleid) {
        DBContext db = DBContext.getInstance();
        ArrayList<Roles> roles = new ArrayList<Roles>();

        try {
            String sql = """
                         select * from Roles
                         where RoleID = ?
                         """;
            PreparedStatement statment = db.getConnection().prepareStatement(sql);
            statment.setInt(1, roleid);
            ResultSet rs = statment.executeQuery();
            while (rs.next()) {
                Roles role = new Roles(
                        rs.getInt("RoleID"),
                        rs.getString("RoleName"));
                roles.add(role);
            }
        } catch (Exception e) {
            return null;
        }

        if (roles.isEmpty()) {
            return null;
        } else {
            return roles.get(0);
        }
    }

    public static ArrayList<Roles> getRoles() {
        DBContext db = DBContext.getInstance();
        ArrayList<Roles> roles = new ArrayList<Roles>();

        try {
            String sql = """
                         select * from Roles
                         """;
            PreparedStatement statment = db.getConnection().prepareStatement(sql);
            ResultSet rs = statment.executeQuery();
            while (rs.next()) {
                Roles role = new Roles(
                        rs.getInt("RoleID"),
                        rs.getString("RoleName"));
                roles.add(role);
            }
        } catch (Exception e) {
            return null;
        }

        if (roles.isEmpty()) {
            return null;
        } else {
            return roles;
        }
    }
}
