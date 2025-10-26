/**
 * Clase Main que contiene el método main para la ejecución del programa.
 * @author Iván López Benítez
 */
//Manejo de ficheros
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
//Excepciones
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            //Declaracion de variables relacionadas con el txt
            File txt = new File("Catalogo_inicial_sin_formato.txt");
            FileReader fr = new FileReader(txt);
            BufferedReader br = new BufferedReader(fr);
            String linea;
            int contador = 0;
            //Lectura del fichero línea a línea
            while((linea = br.readLine()) != null && !linea.isEmpty()) {
                contador++;
                Juego juego = new Juego();

                //Asignación del ID.
                if (contador>=1 && contador < 10) {
                    juego.setId("vg00" + contador);
                } else if (contador >= 10 && contador < 100) {
                    juego.setId("vg0" + contador);
                } else if(contador >= 100) {
                    juego.setId("vg" + contador);
                }
                //Comprobación del ID asignado.
                System.out.println("Juego " + juego.getId() + ":");
                System.out.println(linea);
            }
            br.close();
            
        } catch (IOException e) {
            System.out.println("Error al leer el fichero: " + e.getMessage());
        }

    }
}