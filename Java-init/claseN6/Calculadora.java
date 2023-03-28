import java.util.Scanner;

public class Calculadora extends CalculadoraProcesos {
    private Scanner scan;

    public Calculadora() {
        System.out.println("\n" + ColorsLog.COLOR_YELLOW + "Calculadora Cask trucha" + ColorsLog.COLOR_RESET );
        iniciarCalculadora();

    }

    public void iniciarCalculadora(){
        System.out.println("\n"+ColorsLog.COLOR_GREEN +"Elige una operacion \n" + ColorsLog.COLOR_RESET);
        System.out.println(ColorsLog.ANSI_BLACK_BACKGROUND
                + "[S] suma [R] resta [M] multiplicacion [D] division [X] salir" + ColorsLog.COLOR_RESET+"\n");
        scan = new Scanner(System.in);
        String selection = scan.nextLine().trim().toUpperCase();
        switch(selection){
            case "S"->printResult(procesoSuma(scan));
            case "M"->printResult(procesoMultiplicacion(scan));
            case "D"->printResult(procesoDivision(scan));
            case "R"->printResult(procesoResta(scan));
            case "X"-> exit();
            default ->  reintentarEleccion();
        }
        confirmarContiniudad();
    }

    private void exit() {
        System.out.println(ColorsLog.COLOR_BLUE+"\nAdios 👋\n"+ColorsLog.COLOR_RESET);
        scan.close();
        System.exit(0);
    }
    private void reintentarEleccion(){
        System.out.println("\n"+ColorsLog.ANSI_RED_BACKGROUND+"Elección inconrrecta o inexistente !"+ColorsLog.COLOR_RESET+"\n");
        iniciarCalculadora();
    }
    private void printResult( Object result){
        System.out.println("\n"+
        ColorsLog.COLOR_GREEN+
        "Resultado: "+
        ColorsLog.COLOR_RESET+
        result+"\n");
        
    }

    private void confirmarContiniudad(){
        System.out.println("\ndeseas continuar una nueva operacion? [s] si [n] no\n");
        String decision= scan.nextLine().trim().toLowerCase();
        if(decision.equals("s")){
            iniciarCalculadora();
        }else if(decision.equals("n")){
            exit();
        }else{
            System.out.println("\n"+ColorsLog.ANSI_RED_BACKGROUND+"mala eleccion!"+ColorsLog.COLOR_RESET+"\n");
            confirmarContiniudad();
        }
    }
}
