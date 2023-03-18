

public class SplitPalabras {
    
    public static void split(String texto){
        texto=texto.trim();
        String[] palabras= texto.split(" ");
        for(String palabra:palabras){
            System.out.println(palabra);
        }
    }
}
