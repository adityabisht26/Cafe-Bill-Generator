import java.util.*;

class MenuItem {
    private String name;
    private double price;

    public MenuItem(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

class Customer {
    private String name;
    private String orderNumber;

    public Customer(String name, String orderNumber) {
        this.name = name;
        this.orderNumber = orderNumber;
    }

    public String getName() {
        return name;
    }

    public String getOrderNumber() {
        return orderNumber;
    }
}

class CafeOrderSystem {
    private List<MenuItem> menu;
    private List<MenuItem> currentOrder;
    private Scanner scanner;

    public CafeOrderSystem() {
        menu = new ArrayList<>();
        currentOrder = new ArrayList<>();
        scanner = new Scanner(System.in);
        initializeMenu();
    }

    // Initialize the menu with items
    private void initializeMenu() {
        menu.add(new MenuItem("Hot Coffee: Caffe Americano", 150));
        menu.add(new MenuItem("Hot Coffee: Caffe Mocha", 200));
        menu.add(new MenuItem("Hot Coffee: Cappuccino", 220));
        menu.add(new MenuItem("Hot Coffee: Espresso", 200));
        menu.add(new MenuItem("Cold Coffee: Iced Caffe Mocha", 250));
        menu.add(new MenuItem("Cold Coffee: Iced Cappuccino", 230));
        menu.add(new MenuItem("Cold Coffee: Iced White Mocha", 270));
        menu.add(new MenuItem("Cold Coffee: Iced Caffe Latte", 300));
        menu.add(new MenuItem("Bakery: Almond Butterscotch Cookie", 170));
        menu.add(new MenuItem("Bakery: Red Velvet & Orange Pastry", 200));
        menu.add(new MenuItem("Bakery: Double Chocolate Chip Cookie", 160));
    }

    // Display the menu
    public void displayMenu() {
        System.out.println("\n==== StarBucks Menu ====");
        for (int i = 0; i < menu.size(); i++) {
            System.out.println((i + 1) + ". " + menu.get(i).getName() + " - Rs " + menu.get(i).getPrice());
        }
        System.out.println("========================");
    }

    // Get customer information
    public Customer getCustomerInfo() {
        System.out.print("\nEnter your name: ");
        String name = scanner.nextLine();
        String orderNumber = "ORD" + (int)(Math.random() * 10000);
        return new Customer(name, orderNumber);
    }

    // Take order from customer
    public void takeOrder() {
        System.out.println("\nPlace your order:");

        while (true) {
            System.out.print("Enter item number (1-" + menu.size() + ") or 0 to finish: ");
            int choice = scanner.nextInt();

            if (choice == 0) {
                break;
            } else if (choice >= 1 && choice <= menu.size()) {
                MenuItem selectedItem = menu.get(choice - 1);
                currentOrder.add(selectedItem);
                System.out.println("Added: " + selectedItem.getName());
            } else {
                System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    // Calculate total amount
    public double calculateTotal() {
        double total = 0;
        for (MenuItem item : currentOrder) {
            total += item.getPrice();
        }
        return total;
    }

    // Generate and display bill
    public void generateBill(Customer customer) {
        System.out.println("\n========== BILL ==========");
        System.out.println("Customer: " + customer.getName());
        System.out.println("Order Number: " + customer.getOrderNumber());
        System.out.println("-------------------------");

        if (currentOrder.isEmpty()) {
            System.out.println("No items ordered.");
            return;
        }

        System.out.println("Items Ordered:");
        for (MenuItem item : currentOrder) {
            System.out.println("• " + item.getName() + " - Rs " + item.getPrice());
        }

        System.out.println("-------------------------");
        System.out.println("Total Amount: Rs " + calculateTotal());
        System.out.println("==========================");
    }

    // Start the ordering process
    public void startOrdering() {
        System.out.println("Welcome to StarBucks!");
        displayMenu();

        Customer customer = getCustomerInfo();

        takeOrder();
        
        generateBill(customer);

        System.out.println("\nThank you for your visit! :)");
    }
}

public class Cafe {
    public static void main(String[] args) {
        CafeOrderSystem cafeSystem = new CafeOrderSystem();
        cafeSystem.startOrdering();
    }
}