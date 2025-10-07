package entregable_obligatorio;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class ManejarFichero {
    /*
     *  Crear fichero
     *  Leer fichero (primeros 300 caracteres)
     *  Insertar texto (punto de inserción y final del fichero)
     *  Eliminar texto (cierto número de caracteres desde un punto de inserción)
     *  Eliminar fochero
     *  Resumen de "ficheros que empiezan por"
    */

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        menuPrincipal();
        int opcion = sc.nextInt();

        while (opcion != 7) {
            switch (opcion) {
                case 1:
                    System.out.print("Indica el nombre del fichero a crear: ");
                    String nombreFichero = sc.next();
                    crearFichero(nombreFichero);
                    break;
                case 2:
                    System.out.print("Indica el nombre del fichero a leer: ");
                    String nombreFicheroLeer = sc.next();
                    File ficheroLeer = new File(nombreFicheroLeer);
                    try {
                        leerFichero(ficheroLeer);
                    } catch (IOException e) {
                        System.out.println("Ha ocurrido un error:" + e.getMessage());
                    }
                default:
                    System.out.println("Opción no válida");
            }
            menuPrincipal();
            opcion = sc.nextInt();
        }
        sc.close();
    }

    private static Object resumenFicherosQueEmpiezanPor(File fichero) {
       fichero = new File("fichero.txt");
       return fichero;
    }

    private static Object eliminarFichero(File fichero) {
        fichero = new File("fichero.txt");
        return fichero;
    }

    private static Object eliminarTexto(File fichero) {
        fichero = new File("fichero.txt");
        return fichero;
    }

    private static Object insertarTexto(File fichero) {
        fichero = new File("fichero.txt");
        return fichero;
    }

    private static String leerFichero(File fichero) throws IOException {
        
        FileReader fileReader = new FileReader(fichero);
        
        for(int i = 0; i < 300; i++) {
            int caracter = fileReader.read();
            if(caracter == -1) {
                break;
            }
            System.out.print((char) caracter);
        }

        fileReader.close();
    
        return fichero.toString();
    }

    private static void crearFichero(String nombreFichero) {
        File fichero = new File("./" + nombreFichero);
        try {
            if (fichero.createNewFile()) {
                System.out.println("Fichero creado: " + fichero.getName());
            } else {
                System.out.println("El fichero ya existe.");
            }
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error:" + e.getMessage());
        }
    }

    private static void menuPrincipal() {
        System.out.println("""
                           --- Menú Principal ---
                           1. Crear fichero
                           2. Leer fichero
                           3. Insertar texto
                           4. Eliminar texto
                           5. Eliminar fichero
                           6. Resumen de ficheros que empiezan por...
                           7. Salir""");
    }
}
