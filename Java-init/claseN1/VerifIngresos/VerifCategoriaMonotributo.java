package claseN1.VerifIngresos;

import java.math.BigDecimal;

public class VerifCategoriaMonotributo {

    private long ingresosBrutos;
    private int superficie;
    private int energia;

    public VerifCategoriaMonotributo(){}

    public VerifCategoriaMonotributo setIngresosBrutos(long ingresosBrutos) {
        this.ingresosBrutos = ingresosBrutos;
        return this;
    }

    public VerifCategoriaMonotributo setSuperficie(int superficie) {
        this.superficie = superficie;
        return this;
    }

    public VerifCategoriaMonotributo setEnergia(int energia) {
        this.energia = energia;
        return this;
    }


    public String getCategory(){

        // podria agregar mas pero con esto ya esta
        // decidi usar ternario para escribir menos hehe
        String d= (this.ingresosBrutos<=500000&&this.superficie <= 30&&this.energia<=3330)?
        "A":(this.ingresosBrutos<=1485978&&this.superficie <= 45&&this.energia<=5000)?
        "B":
        "C"
        ;
        return d;
    }
    

}
