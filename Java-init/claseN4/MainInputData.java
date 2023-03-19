

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MainInputData {
    private static List<String> op= Arrays.asList("A","D");
    public static void main(String... args){
        if(args.length!=0){
            String opt= args[args.length-1];
            ordenarLista(args,opt);
            
        }else{
            String list="";
            String option=null;  
            try (Scanner scan = new Scanner(System.in)) {
                System.out.println("ingresar lista de numeros:\n");
                list=scan.nextLine();
                String[] formatedList= list.trim().split(" ");
    
                System.out.println("ingresar metodo: [A] ascendente [D] descendente \n");
                option=scan.nextLine();
                ordenarLista(formatedList,option);
                
            }
        }
    }

    public static void ordenarLista(String[] array , String opt){ 
        List<Integer> arr= new ArrayList<>();
        if(!op.contains(opt)){
            System.out.println("Ingrese una opcion valida: [A] ascendente o [D] descendente");
            return;
        }else{
            for(int i=0;i<array.length-1;i++){
                try{
                    arr.add(Integer.parseInt(array[i]));
                }catch(NumberFormatException ex){
                    System.out.println("la lista debe ser de numeros, ingresalo correctamente");
                    return;
                }
                
            }
        }
        System.out.println("Lista Ordenada ");
        for(int numero:SortSumAndCount.sort(arr.toArray(new Integer[]{}), opt)){
            System.out.print(numero +"\t");
        }
        System.out.println("\n");
    }
}
