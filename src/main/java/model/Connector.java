package model;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class Connector {
    private static String path;
    private static Connection conn;

    public static Connection getConnection() {
        if(Connector.conn == null) {
            path = Connector.class.getResource("").getPath() + "conf/db.properties";

            File file = new File(path);
            
            try {
                FileInputStream fis = new FileInputStream(file);

                Properties props = new Properties();
                props.load(fis);
        
                fis.close();
        
                String driver = props.getProperty("driver");
                String url = props.getProperty("url");
                String username = props.getProperty("username");
                String password = props.getProperty("password");
        
                Class.forName(driver);
        
                conn = DriverManager.getConnection(url, username, password);

                return conn;
            }
            catch (Exception e) {
                e.printStackTrace();
            }

        return null;
        }

        return Connector.conn;
    }
}