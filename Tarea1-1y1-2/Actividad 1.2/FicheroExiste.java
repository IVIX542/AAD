import java.io.File;

public class FicheroExiste {
    public static void main(String[] args) {
        //Variable que contiene la ruta del fichero
        String rutaFichero = "Tarea1-1y1-2\\Actividad 1.2\\FicheroExiste.java";
        
        //Declaramos un fichero
        File fichero = new File(rutaFichero);

        if (fichero.exists()) {
            System.out.println("El fichero existe.");
            System.out.println("Ruta absoluta: " + fichero.getAbsolutePath());
            System.out.println("¿Es un directorio? " + (fichero.isDirectory()? "Sí" : "No"));
            System.out.println("¿Es un fichero? " + (fichero.isFile()? "Sí" : "No"));
            System.out.println("Tamaño: " + fichero.length() + " bytes");
        } else {
            System.out.println("El fichero no existe.");
        }
    }
}