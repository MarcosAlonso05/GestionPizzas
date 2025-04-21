import database.DataBaseManager;
import manager.Authenticator;
import manager.OrderManager;
import manager.PaymentProcessor;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DatabaseTest {
    private DataBaseManager dbManager;
    private Authenticator userManager;
    private OrderManager orderManager;
    private PaymentProcessor paymentProcessor;
    private Connection connection;

    private int userId;
    private int orderId;

    @BeforeAll
    void setup() {
        dbManager = new DataBaseManager();
        userManager = new Authenticator(dbManager);
        orderManager = new OrderManager(dbManager);
        paymentProcessor = new PaymentProcessor(dbManager);
        connection = dbManager.getConnection();
    }

    @Test
    public void testUserOrderPaymentFlow() {
        try {
            userManager.insertUser("testuser", "password");
            userId = getLastId("users");
            Assertions.assertTrue(userId > 0);

            orderManager.insertOrder(userId, "Margarita", "pendiente");
            orderId = getLastId("orders");
            Assertions.assertTrue(orderId > 0);

            paymentProcessor.insertPayment(orderId, 15.50, "efectivo");
            int paymentId = getLastId("payments");
            Assertions.assertTrue(paymentId > 0);

        } catch (SQLException e) {
            Assertions.fail("Fallo en el flujo de inserción: " + e.getMessage());
        }
    }

    @AfterAll
    void cleanup() {
        try {
            deleteById("payments", "order_id", orderId);
            deleteById("orders", "id", orderId);
            deleteById("users", "id", userId);

            dbManager.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int getLastId(String table) throws SQLException {
        String sql = "SELECT id FROM " + table + " ORDER BY id DESC LIMIT 1";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("id");
            }
        }
        return -1;
    }

    private void deleteById(String table, String column, int id) throws SQLException {
        String sql = "DELETE FROM " + table + " WHERE " + column + " = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Eliminado de " + table + " con " + column + " = " + id);
        }
    }
}
