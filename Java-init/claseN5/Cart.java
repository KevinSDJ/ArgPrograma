import java.util.HashSet;
import java.util.Set;

public class Cart {
    private double total=0.0;
    private Set<ItemCart> items= new HashSet<>();

    public Cart(){}

    public double getTotal() {
        return total;
    }

    public Set<ItemCart> getItems() {
        return items;
    }

    public void setItems(Set<ItemCart> items) {
        this.items = items;
        this.items.forEach(e-> {
            this.total+= (e.getProduct().getPrice()*e.getQuantity());
        });
    }

    @Override
    public String toString() {
        return "Cart [total=" + total + ", items=" + items + "]";
    }
    
}
