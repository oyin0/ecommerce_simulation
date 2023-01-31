import java.util.ArrayList;
import java.util.Scanner;


class Ecommerce {
    // Main method
    public static void main(String[] args) {
        Scanner new_input = new Scanner(System.in);
        buyers_agent buyer = new buyers_agent();
        sellers_agent seller = new sellers_agent();
        boolean shopping = true;

        while (shopping) {
            System.out.println("You are welcome to Aish grocery store. Please check through the options provided");
            System.out.println("1. Make your search");
            System.out.println("2. See shopping cart");
            System.out.println("3. Checkout");
            System.out.println("4. Add a product (for seller only)");
            System.out.println("5. Exit");

            int choice = new_input.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("What are you looking for?: ");
                    new_input.nextLine();
                    String query = new_input.nextLine();
                    ArrayList<Product> searchResults = buyer.search(query);
                    if (searchResults.size() > 0) {
                        System.out.println("Here are your results:");
                        for (int i = 0; i < searchResults.size(); i++) {
                            Product p = searchResults.get(i);
                            System.out.println((i+1) + ". " + p.getProductName() + " - £" + p.getProductPrice());
                        }
                        System.out.println("Enter the number of the product you would like to add to your cart, or 0 to return to the main menu: ");
                        int selection = new_input.nextInt();
                        if (selection > 0 && selection <= searchResults.size()) {
                            Product selectedProduct = searchResults.get(selection - 1);
                            buyer.addToCart(selectedProduct);
                            System.out.println("Product have been added to cart!");
                        }
                    } else {
                        System.out.println("No results found.");
                    }
                    break;
                case 2:
                    ArrayList<Product> cart = buyer.getShoppingCart();
                    if (cart.size() > 0) {
                        System.out.println("You have the following items in your shopping cart:");
                        for (Product p : cart) {
                            System.out.println(p.getProductName() + " - £" + p.getProductPrice());
                        }
                    } else {
                        System.out.println("Your shopping cart is empty.");
                    }
                    break;
                case 3:
                    if (buyer.checkout()) {
                        System.out.println("Your payment has been received.");
                        shopping = false;
                    } else {
                        System.out.println("We have encountered a problem with your purchase. Please try again.");
                    }
                    break;
                case 4:
                    System.out.println("Enter the product's name you are trying to add:");
                    new_input.nextLine();
                    String name = new_input.nextLine();
                    System.out.println("Enter the price of the product: ");
                    double price = new_input.nextDouble();
                    Product newProduct = new Product(name, price);
                    if (seller.addProduct(newProduct)) {
                        System.out.println("Successful! Product has been added to the store!");
                    } else {
                        System.out.println("We encountered a problem adding the product to your store. Please try again.");
                    }
                    break;
                case 5:
                    shopping = false;
                    break;
                default:
                    System.out.println("Your selection is not valid. Please try again.");
                    break;
            }
        }
    }
}

class Product {
    // Private fields for storing product details
    private String name;
    private double price;

    // Constructor for creating a new Product
    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    // Getter method for product name
    public String getProductName() {
        return this.name;
    }

    // Getter method for product price
    public double getProductPrice() {
        return this.price;
    }
}


class sellers_agent {
    private ArrayList<Product> catalogue;

    public sellers_agent() {
        catalogue = new ArrayList<Product>();
    }

    public boolean addProduct(Product p) {
        if (p != null) {
            catalogue.add(p);
            buyers_agent.products.add(p);
            return true;
        }
        return false;
    }

    public ArrayList<Product> getCatalogue() {
        return catalogue;
    }
}


class buyers_agent {
    // Private fields for storing products, shopping cart, etc.
    public static ArrayList<Product> products;
    private ArrayList<Product> shoppingCart;

    // Constructor for creating a new buyers_agent
    public buyers_agent() {
        this.products = new ArrayList<Product>();
        this.shoppingCart = new ArrayList<Product>();

        // products in catalogue
        this.products.add(new Product("Water", 1.50));
        this.products.add(new Product("White bread", 1.60));
        this.products.add(new Product("Strawberry Jam", 2.00));
        this.products.add(new Product("Coke", 1.05));
        this.products.add(new Product("Sugar", 1.20));
        this.products.add(new Product("Milk", 1.19));
        this.products.add(new Product("Orange juice", 2.99));
        this.products.add(new Product("Biscuits", 3.50));
        this.products.add(new Product("Pasta", 1.50));
        this.products.add(new Product("Butter", 2.00));
    }

    // Method for searching for products
    public ArrayList<Product> search(String query) {
        ArrayList<Product> results = new ArrayList<Product>();
        for (Product p : this.products) {
            if (p.getProductName().contains(query)) {
                results.add(p);
            }
        }
        return results;
    }

    // Method for adding items to shopping cart
    public void addToCart(Product item) {
        this.shoppingCart.add(item);
    }

    // Method for retrieving items in shopping cart
    public ArrayList<Product> getShoppingCart() {
        return this.shoppingCart;
    }

    // Method for completing a purchase
    public boolean checkout() {
        double total = 0;
        for (Product p : this.shoppingCart) {
            total += p.getProductPrice();
        }

        
        boolean paymentSuccessful = processPayment(total);
        if (paymentSuccessful) {
            // If payment was successful, clear shopping cart and return true
            this.shoppingCart.clear();
            return true;
        } else {
            
            return false;
        }
    }

    private boolean processPayment(double amount) {
        return true;
    }
}





