package database;

import java.sql.*;

public class DataBaseManager {
    private static final String URL = "jdbc:mysql://localhost:3307/pizzadb";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    private Connection connection;

    public DataBaseManager() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexion establecida con exito");
        } catch (SQLException ex) {
            System.out.println("Error al establecer conexion: " + ex.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException ex) {
            System.out.println("Error al cerrar conexion: " + ex.getMessage());
        }
    }
}