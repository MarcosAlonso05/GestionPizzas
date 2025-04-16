package manager;

import database.DataBaseManager;

import java.sql.*;

public class PaymentProcessor {
    private final Connection connection;

    public PaymentProcessor(DataBaseManager dbManager) {
        this.connection = dbManager.getConnection();
    }

    public void insertPayment(int orderId, double amount, String method) {
        String sql = "INSERT INTO payments (order_id, amount, payment_method) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, orderId);
            stmt.setDouble(2, amount);
            stmt.setString(3, method);
            stmt.executeUpdate();
            System.out.println("Pago registrado correctamente.");
        } catch (SQLException ex) {
            System.out.println("Error al insertar pago:");
            ex.printStackTrace();
        }
    }

    public void getPayments() {
        String sql = "SELECT * FROM payments";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                System.out.println("Pago ID: " + rs.getInt("id") +
                        " | Order ID: " + rs.getInt("order_id") +
                        " | Monto: " + rs.getDouble("amount") +
                        " | Método: " + rs.getString("payment_method"));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener pagos:");
            e.printStackTrace();
        }
    }
}
