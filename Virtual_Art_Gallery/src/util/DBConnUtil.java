package src.util;
import java.sql.Connection;
import java.sql.DriverManager;
public class DBConnUtil {
    public static Connection getConnection(String fileName) {
        try {
            String url = DBPropertyUtil.getPropertyString(fileName);
            return DriverManager.getConnection(url);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
