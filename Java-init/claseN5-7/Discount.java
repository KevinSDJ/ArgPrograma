public class Discount {
    
    private String desc;
    private int value;

    public Discount(String desc,int value){
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
