import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CrearArchivo {
    public static void main(String[] args) {
        if(args.length != 3) {
            System.out.println("Por favor, proporciona exactamente tres argumentos: el nombre del fichero sin extension, el directorio y el contenido del fichero (sin espacios).");
        } else{
            String nombreFichero = args[0];
            String directorio = args[1];
            String contenido = args[2];

            String rutaCompleta = directorio + File.separator + nombreFichero + ".txt";

            File directorioFile = new File(directorio);

            if(!directorioFile.exists()){
                if(directorioFile.mkdirs()){
                    System.out.println("Directorio creado: " + directorioFile.getAbsolutePath());
                } else {
                    System.out.println("No se pudo crear el directorio: " + directorioFile.getAbsolutePath());
                }
            }

            File fichero = new File(rutaCompleta);

            try {
                
                if(fichero.createNewFile()){
                    System.out.println("Fichero creado en: " + fichero.getAbsolutePath());
                } else {
                    System.out.println("El fichero ya existe en: " + fichero.getAbsolutePath());
                }

                // Escribir contenido en el fichero
                BufferedWriter texto = new BufferedWriter(new FileWriter(fichero));
                texto.write(contenido);
                texto.close();
                System.out.println("Contenido escrito en el fichero.");

            } catch (IOException e) {
                System.out.println("Error al crear el fichero: " + e.getMessage());
            }
            
        }
    }
}
