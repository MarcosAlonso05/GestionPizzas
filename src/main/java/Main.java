import database.DataBaseManager;
import manager.Authenticator;
import manager.OrderManager;
import manager.PaymentProcessor;

public class Main {
    public static void main(String[] args) {
        DataBaseManager dbManager = new DataBaseManager();

        Authenticator userManager = new Authenticator(dbManager);
        OrderManager orderManager = new OrderManager(dbManager);
        PaymentProcessor paymentProcessor = new PaymentProcessor(dbManager);

        userManager.insertUser("marcos", "1234");

        userManager.getUsers();

        orderManager.insertOrder(1, "Pepperoni", "pendiente");

        orderManager.getOrders();

        paymentProcessor.insertPayment(1, 12.50, "Tarjeta");

        paymentProcessor.getPayments();

        dbManager.closeConnection();
    }
}
