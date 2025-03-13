package model;

import dao.RoleDAO;

public class Users {

    private int UserId;
    private String Fullname;
    private String Email;
    private String Password;
    private int RoleID;
    private String Phone;
    private String Address;
    
    private Roles Roles;

    public Users() {
    }
    

    public Users(int UserId, String Fullname, String Email, String Password, int RoleID, String Phone, String Address) {
        this.UserId = UserId;
        this.Fullname = Fullname;
        this.Email = Email;
        this.Password = Password;
        this.RoleID = RoleID;
        this.Phone = Phone;
        this.Address = Address;
    }

    public Users(int UserId, String Fullname, String Email, String Password, String Phone, String Address) {
        this.UserId = UserId;
        this.Fullname = Fullname;
        this.Email = Email;
        this.Password = Password;
        this.Phone = Phone;
        this.Address = Address;
    }

    public Users(String Fullname, String Email, String Password, int RoleID, String Phone, String Address) {
        this.Fullname = Fullname;
        this.Email = Email;
        this.Password = Password;
        this.RoleID = RoleID;
        this.Phone = Phone;
        this.Address = Address;
    }

    public Users(String Email, String Password) {
        this.Email = Email;
        this.Password = Password;
    }

    public Roles getRoles() {
        return Roles;
    }

    public void includeRoles() {
        this.Roles = RoleDAO.getRoleByRoleId(RoleID);
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int UserId) {
        this.UserId = UserId;
    }

    public String getFullname() {
        return Fullname;
    }

    public void setFullname(String Fullname) {
        this.Fullname = Fullname;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public int getRoleID() {
        return RoleID;
    }

    public void setRole(int RoleID) {
        this.RoleID= RoleID;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    @Override
    public String toString() {
        return "Users{" + "UserId=" + UserId + ", Fullname=" + Fullname + ", Email=" + Email + ", Password=" + Password + ", Role=" + RoleID + ", Phone=" + Phone + ", Address=" + Address + '}';
    }

}
