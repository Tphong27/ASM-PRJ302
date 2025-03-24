/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;

/**
 *
 * @author Admin
 */
public class Notifications {

    private int NotificationID;
    private int UserID;
    private String Message;
    private Date SentDate;
    private boolean IsRead;

    public Notifications() {
    }

    public Notifications(int NotificationID, int UserID, String Message, Date SentDate, boolean IsRead) {
        this.NotificationID = NotificationID;
        this.UserID = UserID;
        this.Message = Message;
        this.SentDate = SentDate;
        this.IsRead = IsRead;
    }

    public Notifications(int UserID, String Message) {
        this.UserID = UserID;
        this.Message = Message;
    }

    public int getNotificationID() {
        return NotificationID;
    }

    public void setNotificationID(int NotificationID) {
        this.NotificationID = NotificationID;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public Date getSentDate() {
        return SentDate;
    }

    public void setSentDate(Date SentDate) {
        this.SentDate = SentDate;
    }

    public boolean isIsRead() {
        return IsRead;
    }

    public void setIsRead(boolean IsRead) {
        this.IsRead = IsRead;
    }

    @Override
    public String toString() {
        return "Notifications{" + "NotificationID=" + NotificationID + ", UserID=" + UserID + ", Message=" + Message + ", SentDate=" + SentDate + ", IsRead=" + IsRead + '}';
    }

}
