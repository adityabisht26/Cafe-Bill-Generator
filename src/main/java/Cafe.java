import java.util.*;

// MenuItem class representing individual menu items
class MenuItem {
    private String productname;
    private double price;

    public MenuItem(String productname, double price) {
        this.productname = productname;
        this.price = price;
    }

    public String getProductName() {
        return productname;
    }

    public double getPrice() {
        return price;
    }
}

// Customer class representing customer details
class Customer {
    private final String orderNumber;
    private final String name;

    public Customer(String orderNumber, String name) {
        this.orderNumber = orderNumber;
        this.name = name;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public String getName() {
        return name;
    }
}

// BillGenerator class to manage orders and generate bills
class BillGenerator {
    private List<MenuItem> menuItems = new ArrayList<>();
    private List<MenuItem> orderedItems = new ArrayList<>();
    private List<Customer> customers = new ArrayList<>();

    public BillGenerator() {
        // Adding some products to the menu
        menuItems.add(new MenuItem("Hot Coffee: Caffe Americano", 150));
        menuItems.add(new MenuItem("Hot Coffee: Caffe Mocha", 200));
        menuItems.add(new MenuItem("Hot Coffee: Cappuccino", 220));
        menuItems.add(new MenuItem("Hot Coffee: Espresso", 200));
        menuItems.add(new MenuItem("Cold Coffee: Iced Caffe Mocha", 250));
        menuItems.add(new MenuItem("Cold Coffee: Iced Cappuccino", 230));
        menuItems.add(new MenuItem("Cold Coffee: Iced White Mocha", 270));
        menuItems.add(new MenuItem("Cold Coffee: Iced Caffe Latte", 300));
        menuItems.add(new MenuItem("Bakery: Almond Butterscotch Cookie", 170));
        menuItems.add(new MenuItem("Bakery: Red Velvet & Orange Pastery", 200));
        menuItems.add(new MenuItem("Bakery: Double Chocolate Chip Cookie", 160));
    }

    public void showMenu() {
        System.out.println("<<<<StarBucks Menu>>>>");
        for (int i = 0; i < menuItems.size(); i++) {
            System.out.println((i + 1) + ". " + menuItems.get(i).getProductName() + " - " + menuItems.get(i).getPrice() + " Rs");
        }
    }

    public void takeOrder() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter the number of the item you want to order (or 0 to finish): ");
            int choice = scanner.nextInt();
            if (choice == 0) {
                break;
            } else if (choice > 0 && choice <= menuItems.size()) {
                orderedItems.add(menuItems.get(choice - 1));
                System.out.println("Item added to your order: " + menuItems.get(choice - 1).getProductName());
            } else {
                System.out.println("Invalid choice, please try again.");
            }
        }
    }

    public double calculateTotal() {
        double total = 0;
        for (MenuItem item : orderedItems) {
            total += item.getPrice();
        }
        return total;
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
        System.out.println("Bill for Customer: " + customer.getName());
        System.out.println("Order Number: " + customer.getOrderNumber());
        double total = calculateTotal();
        System.out.println("Total Amount:" + total + " Rs");
    }

    public Customer findOrCreateCustomer(String name) {
        for (Customer customer : customers) {
            if (customer.getName().equalsIgnoreCase(name)) {
                return customer;
            }
        }
        Customer newCustomer = new Customer("CUS" + (customers.size() + 1), name);
        addCustomer(newCustomer);
        return newCustomer;
    }
}

// Main class to run the application
public class Cafe {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BillGenerator billGenerator = new BillGenerator();

        // Show the menu first
        billGenerator.showMenu();

        // Get customer details
        System.out.println("Enter your name: ");
        String name = scanner.nextLine();

        // Customer is created here
        Customer customer = billGenerator.findOrCreateCustomer(name);

        // Take the order
        billGenerator.takeOrder();

        // Generate the bill
        billGenerator.addCustomer(customer);
        System.out.println("Thank you for your visit. :)");
    }
}
