package manager;

import database.DataBaseManager;

import java.sql.*;

public class Authenticator {
    private final Connection connection;

    public Authenticator(DataBaseManager dbManager) {
        this.connection = dbManager.getConnection();
    }

    public void insertUser(String username, String password) {
        String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.executeUpdate();
            System.out.println("Usuario insertado correctamente.");
        } catch (SQLException ex) {
            System.out.println("Error al insertar usuario:");
            ex.printStackTrace();
        }
    }

    public void deleteUser(int userId) {
        String sql = "DELETE FROM users WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Usuario eliminado correctamente.");
            } else {
                System.out.println("No se encontró el usuario.");
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar usuario:");
            e.printStackTrace();
        }
    }

    public void getUsers() {
        String sql = "SELECT * FROM users";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") +
                        " | Username: " + rs.getString("username"));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener usuarios:");
            e.printStackTrace();
        }
    }
}
