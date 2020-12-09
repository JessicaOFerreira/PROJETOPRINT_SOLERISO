/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author johnn
 */
public class SQLConnection {
    private static Connection connection = null;

    private SQLConnection() {}
    
    public static synchronized Connection getConnectionInstance() {
        try {
            if (connection == null || connection.isClosed()) {
                // Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection(DBSettings.DB_URL, DBSettings.DB_USER, DBSettings.DB_PASSWORD);
                System.out.println("Success!");
            } else {
                System.out.println("Connection is null or open!");
            }
        } catch (SQLException exp) {
            System.out.println("Connection error!");
            Logger.getLogger(SQLConnection.class.getName()).log(Level.SEVERE, null, exp);
        }
        
        return connection;
    }
    
}
