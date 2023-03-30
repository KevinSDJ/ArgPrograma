public class Discount {
    
    private String desc;
    private int value;
    private int MAX=60; 

    public Discount(String desc,int value){
        if(value>MAX){
            throw new IllegalArgumentException("maximo descuento excedido");
        }
        this.desc=desc;
        this.value=value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getValue() {
        if(value>MAX){
            throw new IllegalArgumentException("maximo descuento excedido");
        }
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Discount [desc=" + desc + ", value=" + value + "]";
    }

}
