import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    private static List<Product> dbMock;

    public static void main(String... args) {
        loadProducts();
        simulateShoppingAndPrint();
    }

    private static void loadProducts() {
        dbMock = ScanDataFromFile.parseFileDataProducts("./data.txt");
    }
    private static void simulateShoppingAndPrint(){
        Cart cart = new Cart();
        Set<ItemCart> items = new HashSet<>();
        items.add(new ItemCart(10, dbMock.get(0)));
        items.add(new ItemCart(12, dbMock.get(1)));
        cart.setItems(items);
        System.out.println("\n");
        System.out.printf("| %-10s | %-8s | %4s |%n", "QUANTITY","PRICE","PRODUCT NAME");
        System.out.printf("--------------------------------%n");
        cart.getItems().forEach(e -> {
            System.out.printf("| %-10s | %-8s | %-10s |%n", e.getQuantity() , e.getProduct().getPrice() ,e.getProduct().getProductName());
        });
        System.out.printf("--------------------------------%n");
        System.out.println("\nTotal: " + cart.getTotal());

    }
}
