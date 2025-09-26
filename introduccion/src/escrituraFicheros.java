import java.io.File;
import java.io.FileWriter;

public class escrituraFicheros {
    public static void main(String[] args) {
        File fichero = new File("introduccion\\src\\files\\escrito.txt");

        try {
            FileWriter ficheroEscritura = new FileWriter(fichero);

            String cadena = "Hola, este es un texto de ejemplo.";

            char[] cad = cadena.toCharArray();
            for (int i = 0; i < cad.length; i++) {
                ficheroEscritura.write(cad[i]);
            }
            ficheroEscritura.close();
            System.out.println("Fichero escrito correctamente.");
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        
    }
}
