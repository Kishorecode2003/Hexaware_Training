package src.util;
import java.io.FileInputStream;
import java.util.Properties;
public class DBPropertyUtil {
    public static String getPropertyString(String fileName) {
        try {
            Properties props = new Properties();
            props.load(new FileInputStream(fileName));
            return "jdbc:mysql://" + props.getProperty("host") + ":" +
                   props.getProperty("port") + "/" + props.getProperty("dbname") +
                   "?user=" + props.getProperty("username") + "&password=" +
                   props.getProperty("password");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
