/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import dao.UsersDAO;
import java.sql.Date;

/**
 *
 * @author Admin
 */
public class Logs {

    private int LogID;
    private int UserID;
    private String Action;
    private Date Timestamp;

    private Users Users;

    public Logs() {
    }

    public Logs(int LogID, int UserID, String Action, Date Timestamp) {
        this.LogID = LogID;
        this.UserID = UserID;
        this.Action = Action;
        this.Timestamp = Timestamp;
    }
    public Logs( int UserID, String Action) {
        this.UserID = UserID;
        this.Action = Action;
    }

    public Users getUsers() {
        return Users;
    }

    public void includeUsers() {
        this.Users = UsersDAO.getUserByID(this.UserID);
        if (this.Users != null) {
            this.Users.includeRoles();  //lay role
        }
    }

    public int getLogID() {
        return LogID;
    }

    public void setLogID(int LogID) {
        this.LogID = LogID;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }

    public String getAction() {
        return Action;
    }

    public void setAction(String Action) {
        this.Action = Action;
    }

    public Date getTimestamp() {
        return Timestamp;
    }

    public void setTimestamp(Date Timestamp) {
        this.Timestamp = Timestamp;
    }

    @Override
    public String toString() {
        return "Logs{" + "LogID=" + LogID + ", UserID=" + UserID + ", Action=" + Action + ", Timestamp=" + Timestamp + '}';
    }
}
