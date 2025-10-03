import java.io.File;

public class ListarFicheros {
    public static void main(String[] args) {
        //Realiza un programa en Java que utilice el método listFiles() para mostrar la lista de ficheros en un directorio cualquiera, o en un directorio actual.

        String dir = args[0];

        File directorio = new File(dir); // Directorio introducido por cmd
        File[] ficheros = directorio.listFiles();

        if (ficheros != null) {
            for (File fic : ficheros) {
                System.out.println(fic.getName());
            }
        } else {
            System.out.println("El directorio no existe o no es un directorio.");
        }
    }
}