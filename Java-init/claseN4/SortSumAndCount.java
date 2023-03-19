

public class SortSumAndCount {

    public static void contarCaracter(String letra, String texto) {
        int cantidad = 0;
        String[] splitext = texto.toLowerCase().split("");
        for (String caracter : splitext) {
            cantidad += caracter.equals(letra.toLowerCase()) ? 1 : 0;
        }

        System.out.println("Cantidad de letras encontrada: "+cantidad);
    }


    public static Integer[] sort(Integer[] numeros, String orden) {

        Boolean activ = true;
        while (activ) {
            activ = false;
            for (int i = 0; i < numeros.length - 1; i++) {
                if(orden!=null&&orden.equals("D")){
                    if (numeros[i] < numeros[i + 1]) {
                        int pivot = numeros[i];
                        numeros[i] = numeros[i + 1];
                        numeros[i + 1] = pivot;
                        activ = true;
                    }
                }else if(orden==null||orden.equals("A")){
                    if (numeros[i] > numeros[i + 1]) {
                        int pivot = numeros[i];
                        numeros[i] = numeros[i + 1];
                        numeros[i + 1] = pivot;
                        activ = true;
                    }
                }

            }
        }

        return numeros;

    }

    public static int sum(Integer[] numeros,Integer mayorA){
        int sum=0;
        if(mayorA!=null){
            for(int num:numeros){
                if(num>mayorA){
                    sum+=num;
                }
            }
        }else{
            for(int num:numeros){
                    sum+=num;
            }
        }
        return sum;
    }

    
}