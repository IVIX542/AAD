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
                //Extracción de datos de la línea
                // ID
                String id = linea.substring(4, 9);
                // Título
                String titulo = linea.substring(19, linea.indexOf("Año")).trim();
                // Año
                String anio = linea.substring((linea.indexOf("Año") + 4), (linea.indexOf("Compañía"))).trim();
                // Compañía
                String compania = linea.substring((linea.indexOf("Compañía") + 10), (linea.indexOf("Consola"))).trim();
                //Consola
                String consola = linea.substring((linea.indexOf("Consola") + 7), (linea.indexOf("Puntuación"))).trim();
                //Puntuación
                String puntuacion = linea.substring(linea.indexOf("Puntuación") + 12, linea.length()).trim();

                //Asignación de los atributos de cada juego:
                //ID
                if (contador>=1 && contador < 10) {
                    juego.setId(id);
                } else if (contador >= 10 && contador < 100) {
                    juego.setId(id);
                } else if(contador >= 100) {
                    juego.setId(id);
                }
                //Título
                juego.setTitulo(titulo);
                //Año
                juego.setAnio(Integer.parseInt(anio));
                //Compañía
                juego.setCompania(compania);
                //Consola
                juego.setConsola(consola);
                //Puntuación
                juego.setPuntuacion(Double.parseDouble(puntuacion));

                System.out.println("\nJuego " + juego.getId() + ":");
                System.out.println("Título: " + juego.getTitulo());
                System.out.println("Año: " + juego.getAnio());
                System.out.println("Compañía: " + juego.getCompania());
                System.out.println("Consola: " + juego.getConsola());
                System.out.println("Puntuacion: " + juego.getPuntuacion());
            }
            br.close();
            
        } catch (IOException e) {
            System.out.println("Error al leer el fichero: " + e.getMessage());
        }

    }
}