public class Product {

    private int sku;
    private String productName;
    private String description;
    private Double price;
    private int stock;

    public Product(){}

    public Product(int sku,String product_name,String description,double price,int stock){
        this.sku=sku;
        this.productName=product_name;
        this.description=description;
        this.price=price;
        this.stock=stock;
    }

    public int getSku() {
        return sku;
    }

    public void setSku(int sku) {
        this.sku = sku;
    }

    public String getProductName() {
        return productName;
    }

    public void setProduct_name(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDesc(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Product [sku=" + sku + ", productName=" + productName + ", description=" + description + ", price=" + price
                + ", stock=" + stock + "]";
    }
    
    
}
