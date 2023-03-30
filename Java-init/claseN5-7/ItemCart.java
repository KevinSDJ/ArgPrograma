public class ItemCart {
    
    private int quantity=1;
    private Product product;

    public ItemCart(){}

    public ItemCart(int q,Product p){
        if(q>=p.getStock()){
            throw new IllegalArgumentException("stock limit exceeded");
        }
        this.quantity=q;
        this.product=p;
        product.setStock(p.getStock()-quantity);
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if(quantity<1){
            throw new IllegalArgumentException();
        }
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "ItemCart [quantity=" + quantity + ", product=" + product + "]";
    }
}
