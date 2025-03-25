package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Util {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/testdb";
    private static final String USER = "root";
    private static final String PASSWORD = "5854732sirenaA!";
    private static final Logger logger = Logger.getLogger(Util.class.getName());

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error getting database connection", e);
            throw new RuntimeException("Error connecting to the database", e);
        }
    }
}

