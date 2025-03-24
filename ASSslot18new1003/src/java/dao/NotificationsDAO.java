/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Notifications;

/**
 *
 * @author Admin
 */
public class NotificationsDAO {

    public static ArrayList<Notifications> getNotificationsByUserID(int userId) {
        DBContext db = DBContext.getInstance();
        ArrayList<Notifications> notifications = new ArrayList<Notifications>();
        try {
            String sql = """
                         select * from Notifications
                         where UserID = ?
                         Order by SentDate desc
                         """;
            PreparedStatement statment = db.getConnection().prepareStatement(sql);
            statment.setInt(1, userId);
            ResultSet rs = statment.executeQuery();
            while (rs.next()) {
                Notifications no = new Notifications(
                        rs.getInt("NotificationID"),
                        rs.getInt("UserID"),
                        rs.getString("Message"),
                        rs.getDate("SentDate"),
                        rs.getBoolean("IsRead")
                );
                notifications.add(no);
            }
        } catch (Exception e) {
            return notifications;
        }

        return notifications;
    }

    public static Notifications addNotification(Notifications notification) {
        DBContext db = DBContext.getInstance();
        int rs = 0;
        try {
            String sql = """
                         INSERT INTO Notifications (UserID, Message) VALUES
                         (?, ?)
                         """;
            PreparedStatement statment = db.getConnection().prepareStatement(sql);
            statment.setInt(1, notification.getUserID());
            statment.setString(2, notification.getMessage());
            rs = statment.executeUpdate();
        } catch (Exception e) {
            return null;
        }
        if (rs == 0) {
            return null;
        } else {
            return notification;
        }
    }

}
