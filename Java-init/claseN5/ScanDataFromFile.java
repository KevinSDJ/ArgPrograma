import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class ScanDataFromFile {
    private static String pathFile;


    public static List<Product> parseFileDataProducts(String path){
        pathFile=path;
   
        return collectToProduct(validateMapData()).stream().toList();
    }

    private static Collection<Map<String,String>> validateMapData(){
        Collection<Map<String,String>> listMaped= new ArrayList<>();

        try {
            Field[] att= Product.class.getDeclaredFields();
            Files.readAllLines(Paths.get(pathFile)).forEach(line->{
                Map<String,String> map=new HashMap<>();
                if(line.contains("|")){
                    String[] lines=line.split("\\|");
                    if(lines.length<att.length){
                        throw new IllegalArgumentException("""
                                los valores no pueden estar vacios, 
                                y deben concidir con los valores del producto
                                """);
                    }
                    int i=0;
                    for(String word:line.split("\\|")){
                        map.put(att[i].getName(), word.trim());
                        i++;
                    }
                    listMaped.add(map);
                }
            });
        } catch (IOException ex) {
            Logger.getLogger("ScanDataFormFile").log(Level.SEVERE, ex.getMessage(), ex);
        } catch(IllegalArgumentException ex){
            Logger.getLogger("ScanDataFormFile").log(Level.SEVERE, ex.getMessage(), ex);
        }
        return listMaped;
        
    }

    private  static  Set<Product> collectToProduct(Collection<Map<String,String>> datamap){
        Field[] att= Product.class.getDeclaredFields();
        Set<Product> products = datamap.stream().map(e-> new Product(
            Integer.parseInt(e.get(att[0].getName())),
            e.get(att[1].getName()),
            e.get(att[2].getName()),
            Double.parseDouble(e.get(att[3].getName())),
            Integer.parseInt(e.get(att[4].getName()))
        )).collect(Collectors.toSet());
        
        return products;
    }
}
