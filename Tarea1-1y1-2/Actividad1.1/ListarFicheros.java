
import java.io.File;

public class ListarFicheros {
    public static void main(String[] args) {
        //Realiza un programa en Java que utilice el método listFiles() para mostrar la lista de ficheros en un directorio cualquiera, o en un directorio actual.

        File directorio = new File("Tarea1-1y1-2"); // Directorio actual
    File[] ficheros = directorio.listFiles();

        if (ficheros != null) {
            for (File fichero : ficheros) {
                System.out.println(fichero.getName());
            }
        } else {
            System.out.println("El directorio no existe o no es un directorio.");
        }
    }
}