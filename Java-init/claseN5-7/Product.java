public class Product {

    private int sku;
    private String productName;
    private String description;
    private Double price;
    private int stock;
    private Discount discount;

    public Product(){}

    public Product(int sku,String product_name,String description,double price,int stock){
        this.sku=sku;
        this.productName=product_name;
        this.description=description;
        setPrice(price);
        this.stock=stock;
    }

    public Product(int sku,String product_name,String description,double price,int stock,Discount discount){
        this.sku=sku;
        this.productName=product_name;
        this.description=description;
        setPrice(price);
        this.stock=stock;
        setDiscount(discount);
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
        if(price<0){
            throw new IllegalArgumentException("!price negative, not accept this value");
        }
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        if(this.price==0){
            throw new IllegalArgumentException("can't add to price equals 0");
        }
        this.discount = discount;
    }
    
    @Override
    public String toString() {
        return "Product [sku=" + sku + ", productName=" + productName + ", description=" + description + ", price=" + price
                + ", stock=" + stock + ", discount="+discount +"]";
    }
}
