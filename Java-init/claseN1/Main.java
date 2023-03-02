package claseN1;

public class Main {
    /* este objeto num lo hago para controlar las entradas */
    enum Options {
        NOPRIMOS("no primos"),PRIMOS("primo");
        Options(String key){}
    }

    public static void main(String... args) {
        logNumerosPrimosONotPrimos(Options.NOPRIMOS.name(), true);

    }

    static void logNumerosPrimosONotPrimos(String seleccion, Boolean invertido) {
        try {
            if (invertido == null)
                invertido = false;
            if (seleccion != null) {
                Options.valueOf(seleccion);
            } else {
                System.out.println("Seleccion no ingresada");
                System.out.println("En su defecto imprimira los numeros primos");
                seleccion = "default";
            }
        } catch (Exception ex) {
            System.out.println("seleccion no valida");
            System.out.println("Sugerencia: " + "no primos,invertido");
            return;
        }
        int numeroInicio = 5;
        int numeroFin = 14;
        logNumeros(seleccion, numeroInicio, numeroFin, invertido);

    }

    static void logNumeros(String seleccion, int start, int end, Boolean invertido) {
        if (seleccion == Options.PRIMOS.name()) {
            while (invertido ? start <= end : end >= start) {
                if ((invertido ? end : start) % 2 == 0)
                    System.out.println(invertido ? end : start);
                if (invertido) {
                    end--;
                } else {
                    start++;
                }
            }
        } else if (seleccion == Options.NOPRIMOS.name()) {
            while (invertido ? start <= end : end >= start) {
                if ((invertido ? end : start) % 2 != 0)
                    System.out.println(invertido ? end : start);
                if (invertido) {
                    end--;
                } else {
                    start++;
                }
            }
        }
    }

}
