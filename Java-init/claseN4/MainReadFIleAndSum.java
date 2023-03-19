import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MainReadFIleAndSum {
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_GREEN="\u001b[32m";
    public static void main(String... args) throws IOException{
        File file;
        try{
            file=new File("./"+args[0]);
        }catch(Exception ex){
            System.out.println(ANSI_RED+"!Archivo no encontrado"+ANSI_RESET+"\n");
            System.out.println(ANSI_BLACK_BACKGROUND +"el archivo debe estar en esta capeta \nsolo debes pasar el nombre con su extension ejemplo: demo.txt"+ ANSI_RESET);
            return;
        }
        FileReader reader=null;
        try {
            reader = new FileReader(file);
            BufferedReader buffer= new BufferedReader(reader);
            String linee=null;
            try {
                while((linee=buffer.readLine())!=null){
                    String[] splitline= linee.trim().split(" ");
                    Boolean isMultiplication= args.length>1&& args[1].equals("multiplicacion")?
                    true:false;
                    int resultado=0;
                    
                    if(!isMultiplication){
                        System.out.println(ANSI_RED+"""
                            No se encontro la palabra multiplicacion,\n
                            por defecto se sumara,\n 
                            ten encuenta en que los numeros deben estar separados por espacio
                            o la suma/multiplicacion no se hara correctamente
                         """+ANSI_RESET);
                    }
                    
                    
                    for(int i=0;i< splitline.length;i++){
                        try{
                            if(isMultiplication){
                                if(resultado==0){
                                    resultado=Integer.parseInt(splitline[i]);
                                }else{
                                    resultado*=Integer.parseInt(splitline[i]);
                                }
                            }else resultado+=Integer.parseInt(splitline[i]);
                        }catch(Exception ex){
                            System.out.println("Los valores deben ser un numero");
                            buffer.close();
                            reader.close();
                            return;
                        }
                    }
                    if(isMultiplication){
                        System.out.println(ANSI_GREEN+"Multiplicacion total: "+ANSI_RESET+ resultado);
                    }else{
                        System.out.println(ANSI_GREEN+"Suma total: "+ANSI_RESET+ resultado);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                buffer.close();
                reader.close();
            }
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally{
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


}
