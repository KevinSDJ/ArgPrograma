public class ItemCart {
    
    private int quantity=1;
    private Product product;

    public ItemCart(){}

    public ItemCart(int quantity,Product product){
        if(quantity>product.getStock()){
            throw new IllegalArgumentException("limite de stock excedido");
        }
        this.quantity=quantity;
        this.quantity=quantity;
        this.product=product;
        product.setStock(product.getStock()-quantity);
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
        if(quantity>product.getStock()){
            throw new IllegalArgumentException("limite de stock excedido");
        }
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
