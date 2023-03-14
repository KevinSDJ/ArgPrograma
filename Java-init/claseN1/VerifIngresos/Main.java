package claseN1.VerifIngresos;

public class Main {


    public static void main(String... args){

        VerifIngresos sujeto = new VerifIngresos()
        .setIngresMensualNeto(500000)
        .setMayorOIgual3autos(true)
        .setMinAntgVehiculo(2)
        .setMaxAntgVehiculo(4)
        .setMayorOIguala3Inmuebles(true)
        .setEmbarAeronaveOActSocietario(true);
        Boolean esMonotributista= sujeto.poseeIngresosAltos();

        if(esMonotributista){

            System.out.println(getCategoriaMonotributo(sujeto.gettIngresMensualNeto()));
            
        }else{
            System.out.println("""
                 De acuerdo con los requerimientos,
                 no estas considerado/a en el rango de salarios altos
                 """);
        }
        
    }


    public static String getCategoriaMonotributo(long ingresos){
        VerifCategoriaMonotributo  in = new VerifCategoriaMonotributo() 
                                   .setIngresosBrutos(ingresos)
                                   .setSuperficie(30)
                                   .setEnergia(3330);
    
        return in.getCategory();
    }
    
}
