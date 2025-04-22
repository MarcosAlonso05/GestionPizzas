import database.DataBaseManager;
import manager.Authenticator;
import manager.OrderManager;
import manager.PaymentProcessor;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DataBaseManager dbManager = new DataBaseManager();

        Authenticator userManager = new Authenticator(dbManager);
        OrderManager orderManager = new OrderManager(dbManager);
        PaymentProcessor paymentProcessor = new PaymentProcessor(dbManager);

        Scanner sc = new Scanner(System.in);


        while (true) {
            System.out.println("\n::: GESTOR PEDIDOS :::");
            System.out.println("1. Registrar usuario");
            System.out.println("2. Ver usuarios");
            System.out.println("3. Eliminar usuario");
            System.out.println("4. Crear pedido");
            System.out.println("5. Ver pedidos");
            System.out.println("6. Eliminar pedido");
            System.out.println("7. Cambiar estado de pedido");
            System.out.println("8. Registrar pago");
            System.out.println("9. Ver pagos");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            int opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Nombre: ");
                    String username = sc.nextLine();
                    System.out.print("Contraseña: ");
                    String password = sc.nextLine();
                    userManager.insertUser(username, password);
                    break;
                case 2:
                    userManager.getUsers();
                    break;
                case 3:
                    System.out.print("ID del usuario a eliminar: ");
                    int userIdToDelete = sc.nextInt();
                    userManager.deleteUser(userIdToDelete);
                    break;
                case 4:
                    System.out.print("ID del usuario: ");
                    int userId = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Tipo de pizza: ");
                    String pizzaType = sc.nextLine();
                    System.out.print("Estado del pedido: ");
                    String status = sc.nextLine();
                    orderManager.insertOrder(userId, pizzaType, status);
                    break;
                case 5:
                    orderManager.getOrders();
                    break;
                case 6:
                    System.out.print("ID del pedido a eliminar: ");
                    int orderIdToDelete = sc.nextInt();
                    orderManager.deleteOrder(orderIdToDelete);
                    break;
                case 7:
                    System.out.print("ID del pedido: ");
                    int orderIdToUpdate = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Nuevo estado: ");
                    String newStatus = sc.nextLine();
                    orderManager.updateOrderStatus(orderIdToUpdate, newStatus);
                    break;
                case 8:
                    System.out.print("ID del pedido: ");
                    int orderId = sc.nextInt();
                    System.out.print("Factura: ");
                    double amount = sc.nextDouble();
                    sc.nextLine();
                    System.out.print("Método de pago: ");
                    String method = sc.nextLine();
                    paymentProcessor.insertPayment(orderId, amount, method);
                    break;
                case 9:
                    paymentProcessor.getPayments();
                    break;
                case 0:
                    dbManager.closeConnection();
                    System.out.println("Aplicación finalizada.");
                    return;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }
}