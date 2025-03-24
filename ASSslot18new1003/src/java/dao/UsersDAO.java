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

    public static Users addAccount(Users account) {
        DBContext db = DBContext.getInstance();
        int rs = 0;

        try {
            String sql = "INSERT INTO Users (FullName, Email, Password, RoleID, Phone, Address) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = db.getConnection().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            statement.setString(1, account.getFullname());
            statement.setString(2, account.getEmail());
            statement.setString(3, account.getPassword());
            statement.setInt(4, account.getRoleID());
            statement.setString(5, account.getPhone());
            statement.setString(6, account.getAddress());

            rs = statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                account.setUserId(generatedKeys.getInt(1)); // Gán UserID mới
            } else {
                return null;
            }

        } catch (Exception e) {
            return null;
        }

        return rs == 0 ? null : account;
    }

    public static Users deleteAccount(Users account) {
        DBContext db = DBContext.getInstance();
        int rs = 0;
        try {
            String sql = """
                         delete from Users
                         where UserId = ?
                         """;
            PreparedStatement statment = db.getConnection().prepareStatement(sql);
            statment.setInt(1, account.getUserId());
            rs = statment.executeUpdate();
        } catch (Exception e) {
            return null;
        }

        if (rs == 0) {
            return null;
        } else {
            return account;
        }
    }

    public static boolean isValidPassword(String password) {
        if (password == null || password.length() < 8) {
            return false;
        }

        boolean hasUpper = false; 
        boolean hasLower = false; 
        boolean hasDigit = false; 
        boolean hasSpecial = false; 

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

        
        if (atIndex < 1 
                || dotIndex < atIndex + 2 
                || dotIndex >= email.length() - 1) {
            return false;
        }

        String domain = email.substring(atIndex + 1);

        Set<String> validDomains = Set.of(
                "gmail.com", "edu.vn",
                "gov.vn", "police.gov"
        );

        return validDomains.contains(domain);
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            return false;
        }

        return phoneNumber.length() == 10 && phoneNumber.matches("\\d+");
    }

}
