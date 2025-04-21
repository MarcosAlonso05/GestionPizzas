package manager;

import database.DataBaseManager;

import java.sql.*;

public class OrderManager {
    private final Connection connection;

    public OrderManager(DataBaseManager dbManager) {
        this.connection = dbManager.getConnection();
    }

    public void insertOrder(int userId, String pizzaType, String status) {
        String sql = "INSERT INTO orders (user_id, pizza_type, status) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setString(2, pizzaType);
            stmt.setString(3, status);
            stmt.executeUpdate();
            System.out.println("Pedido insertado correctamente.");
        } catch (SQLException ex) {
            System.out.println("Error al insertar pedido:");
            ex.printStackTrace();
        }
    }

    public void deleteOrder(int orderId) {
        String sql = "DELETE FROM orders WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, orderId);
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Pedido eliminado correctamente.");
            } else {
                System.out.println("No se encontró el pedido.");
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar pedido:");
            e.printStackTrace();
        }
    }

    public void getOrders() {
        String sql = "SELECT * FROM orders";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                System.out.println("Pedido ID: " + rs.getInt("id") +
                        " | User ID: " + rs.getInt("user_id") +
                        " | Pizza: " + rs.getString("pizza_type") +
                        " | Estado: " + rs.getString("status"));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener pedidos:");
            e.printStackTrace();
        }
    }

    public void updateOrderStatus(int orderId, String newStatus) {
        String sql = "UPDATE orders SET status = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, newStatus);
            stmt.setInt(2, orderId);
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Estado del pedido actualizado.");
            } else {
                System.out.println("No se encontró el pedido.");
            }
        } catch (SQLException e) {
            System.out.println("Error al actualizar estado del pedido:");
            e.printStackTrace();
        }
    }
}
