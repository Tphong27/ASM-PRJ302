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

}
