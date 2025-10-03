import java.io.BufferedReader;
import java.io.File; //Librería que maneja ficheros y directorios
import java.io.FileReader; //Librerioa con la que accedemos a ficheros y leerlos
import java.io.IOException; //Librería que maneja errores de entrada/salida

//Programa muy básico que lee ficheros

public class lecturaFicheros{
    public static void main(String[] args) throws IOException{ //throws IOException es opcional
        try {

            //Declaramos un fichero
            File fichero = new File("introduccion\\src\\files\\Tarea1.txt");
            
            BufferedReader ficheroLectura = new BufferedReader(new FileReader(fichero)); //Este sería el fichero de lectura que se encarga de leer el "fichero" que he  declarado en la linea anterior

            for(int i = ficheroLectura.read(); i != -1; i = ficheroLectura.read()){ //Leemos el fichero carácter a carácter hasta que llegue al     final del fichero (cuando read() devuelve -1)
                System.out.print((char) i); //Imprimimos el caracter que hemos leído
            }
            ficheroLectura.close(); //Cerramos el fichero
            System.out.println("\nFin del programa.");
            
        } catch (IOException e) {
            System.out.println("Error al leer el fichero: " + e.getMessage());
        }
    }
}