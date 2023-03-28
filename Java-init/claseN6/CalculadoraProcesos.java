import java.util.Scanner;

public abstract class CalculadoraProcesos extends Operaciones {
    
    private void mensajeIndicador(){
        System.out.println("\n"+ColorsLog.COLOR_YELLOW+"Ingrese los numeros separados por espacio"+ColorsLog.COLOR_RESET+"\n");
    }

    protected int procesoSuma(Scanner scan){
        mensajeIndicador();
        String value = scan.nextLine();
        return sum(parsearAEnteros(value));
    }
    protected int procesoResta(Scanner scan){
        mensajeIndicador();
        String value = scan.nextLine();
        return resta(parsearAEnteros(value));
    }
    
    protected int procesoMultiplicacion(Scanner scan) {
        mensajeIndicador();
        String value = scan.nextLine();
        return multiplicacion(parsearAEnteros(value));
    }

    protected float procesoDivision(Scanner scan){
        mensajeIndicador();
        String value = scan.nextLine();
        return division(parsearAEnteros(value));
    }

}
