package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Logs;

public class LogDAO {

    public static ArrayList<Logs> getLogs() {
        DBContext db = DBContext.getInstance();
        ArrayList<Logs> logs = new ArrayList<Logs>();

        try {
            String sql = """
                         select * from Logs
                         order by Timestamp desc
                         """;
            PreparedStatement statment = db.getConnection().prepareStatement(sql);
            ResultSet rs = statment.executeQuery();
            while (rs.next()) {
                Logs log = new Logs(
                        rs.getInt("LogID"),
                        rs.getInt("UserID"),
                        rs.getString("Action"),
                        rs.getDate("Timestamp")
                );
                logs.add(log);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        if (logs.isEmpty()) {
            return null;
        } else {
            return logs;
        }
    }

    public static Logs addLog(Logs log) {
        DBContext db = DBContext.getInstance();
        int rs = 0;
        try {
            String sql = """
                         insert into Logs (UserID, Action) values
                         (?,?)
                         """;
            PreparedStatement statment = db.getConnection().prepareStatement(sql);
            statment.setInt(1, log.getUserID());
            statment.setString(2, log.getAction());
            rs = statment.executeUpdate();
        } catch (Exception e) {
            return null;
        }

        if (rs == 0) {
            return null;
        } else {
            return log;
        }
    }
}
