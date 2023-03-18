


public class Main {
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static void main(String... args){
        
        SortSumAndCount.contarCaracter("H","hello , hi how are you");
        Integer[] temp={ 12, 3, 1, 4, 5, 13, 2, 0 };
        System.out.println("Orden de lista de numeros por defecto \n");
        for (Integer i : SortSumAndCount.sort(temp, null)) {
            System.out.print(i+"\t");
        }
        System.out.println("\n");

        System.out.println(ANSI_GREEN+"Sumatoria"+ANSI_RESET);
        System.out.println("Mayor a 7: "+ SortSumAndCount.sum(temp, 7));
        System.out.println("Por defecto ,suma todo: "+SortSumAndCount.sum(temp, null));

        System.out.println("\n");

        System.out.println(ANSI_GREEN+"Imprimir palabras \n"+ANSI_RESET);

        SplitPalabras.split("Mambru se fue a la guerra que dolor que dolor que pena");
    }
}
