/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Set;
import model.Users;

public class UsersDAO {

    public static ArrayList<Users> getUsers() {
        DBContext db = DBContext.getInstance();
        ArrayList<Users> users = new ArrayList<Users>();
        try {
            String sql = """
                         select * from Users
                         """;
            PreparedStatement statment = db.getConnection().prepareStatement(sql);
            ResultSet rs = statment.executeQuery();
            while (rs.next()) {
                Users u = new Users(
                        rs.getInt("UserId"),
                        rs.getString("Fullname"),
                        rs.getString("Email"),
                        rs.getString("Password"),
                        rs.getInt("RoleID"),
                        rs.getString("Phone"),
                        rs.getString("Address"));
                users.add(u);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        if (users.isEmpty()) {
            return null;
        } else {
            return users;
        }
    }

    public static boolean checkLogin(Users account) {
        DBContext db = DBContext.getInstance();
        String Email = account.getEmail();
        String password = account.getPassword();
        String query = "select * from Users\n"
                + "where Email=? and Password = ? ";
        try (PreparedStatement ps = db.getConnection().prepareStatement(query)) {
            ps.setString(1, Email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Users getUser(String email) {
        DBContext db = DBContext.getInstance();
        Users account = new Users();
        String query = " select* from Users\n"
                + "where Email = ? ";
        try (PreparedStatement ps = db.getConnection().prepareStatement(query)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    account.setUserId(rs.getInt("UserId"));
                    account.setFullname(rs.getString("FullName"));
                    account.setEmail(rs.getString("Email"));
                    account.setPassword(rs.getString("Password"));
                    account.setRole(rs.getInt("RoleID"));
                    account.setPhone(rs.getString("Phone"));
                    account.setAddress(rs.getString("Address"));
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account;
    }

    public static Users getUserByID(int id) {
        DBContext db = DBContext.getInstance();
        Users account = new Users();
        String query = " select * from users where UserId = ? ";
        try (PreparedStatement ps = db.getConnection().prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    account.setUserId(rs.getInt("UserId"));
                    account.setFullname(rs.getString("FullName"));
                    account.setEmail(rs.getString("Email"));
                    account.setPassword(rs.getString("Password"));
                    account.setRole(rs.getInt("RoleID"));
                    account.setPhone(rs.getString("Phone"));
                    account.setAddress(rs.getString("Address"));
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account;
    }

    public static ArrayList<Users> getUserByRole(int roleid) {
        DBContext db = DBContext.getInstance();
        ArrayList<Users> users = new ArrayList<Users>();
        try {
            String sql = """
                         select * from Users
                         where RoleID = ?
                         """;
            PreparedStatement statment = db.getConnection().prepareStatement(sql);
            statment.setInt(1, roleid);
            ResultSet rs = statment.executeQuery();
            while (rs.next()) {
                Users u = new Users(
                        rs.getInt("UserId"),
                        rs.getString("Fullname"),
                        rs.getString("Email"),
                        rs.getString("Password"),
                        rs.getInt("RoleID"),
                        rs.getString("Phone"),
                        rs.getString("Address"));
                users.add(u);
            }
        } catch (Exception e) {
            return users;
        }

        return users;
    }

    public static Users updateUser(Users user) {
        DBContext db = DBContext.getInstance();
        int rs = 0;

        String query = "UPDATE Users SET FullName = ?\n"
                + "      ,Email = ?\n"
                + "      ,Password = ?\n"
                + "      ,Phone = ?\n"
                + "      ,Address =?\n"
                + " WHERE UserId =?";
        try (PreparedStatement statment = db.getConnection().prepareStatement(query)) {
            statment.setString(1, user.getFullname());
            statment.setString(2, user.getEmail());
            statment.setString(3, user.getPassword());
            statment.setString(4, user.getPhone());
            statment.setString(5, user.getAddress());
            statment.setInt(6, user.getUserId());
            rs = statment.executeUpdate();
        } catch (SQLException e) {
            return null;
        }
        if (rs == 0) {
            return null;
        } else {
            return user;
        }
    }

    public static boolean checkUserExist(String Email) {
        DBContext db = DBContext.getInstance();
        String query = "SELECT COUNT(*) FROM users WHERE Email = ?";
        try (PreparedStatement statement = db.getConnection().prepareStatement(query)) {
            statement.setString(1, Email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0; //exist
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; //
    }

    public static boolean registerAcc(Users userAccount) {
        DBContext db = DBContext.getInstance();
        try {
            String query = "INSERT INTO Users (FullName, Email, Password, RoleID, Phone, Address) VALUES\n"
                    + "(?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = db.getConnection().prepareStatement(query);
            ps.setString(1, userAccount.getFullname());
            ps.setString(2, userAccount.getEmail());
            ps.setString(3, userAccount.getPassword());
            ps.setInt(4, 1);
            ps.setString(5, userAccount.getPhone());
            ps.setString(6, userAccount.getAddress());

            int rowAffected = ps.executeUpdate();
            return rowAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isValidPassword(String password) {
        if (password == null || password.length() < 8) {
            return false;
        }

        boolean hasUpper = false; //kiểm tra xem mk có chữ cái viết hoa
        boolean hasLower = false; //kiểm tra xem mk có chữ cái thường
        boolean hasDigit = false; //kiểm tra xem mk có chữ số
        boolean hasSpecial = false; //kiểm tra xem mk có kí tự đặc biệt

        String specialCharacters = "@$!%*?&._";
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasUpper = true;
            }
            if (Character.isLowerCase(c)) {
                hasLower = true;
            }
            if (Character.isDigit(c)) {
                hasDigit = true;
            }
            if (specialCharacters.indexOf(c) != -1) {
                hasSpecial = true;
            }
        }
        return hasUpper && hasLower && hasDigit && hasSpecial;
    }

    public static boolean isValidEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        
        int atIndex = email.indexOf('@');
        int dotIndex = email.lastIndexOf('.');

        // Kiểm tra định dạng cơ bản của email
        if (atIndex < 1 //Kiểm tra dấu @ có tồn tại và không phải ở vị trí đầu tiên.
            || dotIndex < atIndex + 2 //Kiểm tra có .  và phải xuất hiện sau @ ít nhất 2 ký tự.
            || dotIndex >= email.length() - 1) {//Kiểm tra dấu . không phải là ký tự cuối cùng.
            return false;
        }
        
        String domain = email.substring(atIndex + 1);
        
        Set<String> validDomains = Set.of(
           "gmail.com", "edu.vn",
           "gov.vn", "police.gov"  
        );
        
        return validDomains.contains(domain);
    }

//    public static void main(String[] args) {
//        ArrayList<Users> l = getUsers();
//        for (Users u : l) {
//            System.out.println(u.toString());
//        }
//        String name = "John Smith";
//        Users u = getUser(name);
//        System.out.println(u.toString());
//
//        Users u1 = getUserByID(2);
//        System.out.println(u1.toString());
//    }
}
