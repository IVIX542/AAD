package entregable_obligatorio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
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

        System.out.print("Indica el directorio de trabajo: ");
        String directorioTrabajo = sc.nextLine();
        File dirTrabajo = new File(directorioTrabajo);
        if (!dirTrabajo.exists()) {
            System.out.println("El directorio no existe. Creando directorio....");
            dirTrabajo.mkdirs();
            System.out.println("Directorio creado.");
        } else {
            System.out.println("Directorio de trabajo: " + dirTrabajo.getAbsolutePath());
        }

        menuPrincipal();
        int opcion = sc.nextInt();

        while (opcion != 7) {
            switch (opcion) {
                case 1:
                    System.out.print("Indica el nombre del fichero a crear: ");
                    String nombreFichero = sc.next();
                    crearFichero(dirTrabajo, nombreFichero);
                    break;
                case 2:
                    System.out.print("Indica el nombre del fichero a leer: ");
                    String nombreFicheroLeer = sc.next();
                    
                    try {
                        leerFichero(dirTrabajo, nombreFicheroLeer);
                    } catch (IOException e) {
                        System.out.println("Ha ocurrido un error:" + e.getMessage());
                    }
                    break;
                case 3:
                    System.out.print("Indica el nombre del fichero donde insertar texto: ");
                    String nombreFicheroInsertar = sc.next();
                    
                    System.out.print("Indica el texto a insertar: ");
                    sc.nextLine(); // Consumir el salto de línea pendiente
                    String insercion = sc.nextLine();
                    try {
                        insertarTexto(dirTrabajo, nombreFicheroInsertar, 0, insercion);
                    } catch (IOException e) {
                        System.out.println("\nHa ocurrido un error:" + e.getMessage());
                    }
                    break;



                case 5:
                    System.out.print("Indica el nombre del fichero a eliminar: ");
                    String nombreFicheroEliminar = sc.next();
                    eliminarFichero(dirTrabajo, nombreFicheroEliminar);
                    break;
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

    private static void eliminarFichero(File dirTrabajo, String nombreFichero) {
        File fichero = new File(dirTrabajo + File.separator + nombreFichero);
        if (fichero.delete()) {
            System.out.println("Fichero " + fichero.getName() + " eliminado.");
        } else {
            System.out.println("No se pudo eliminar el fichero " + fichero.getName() + ". Es posible que no exista.");
        }
    }

    private static Object eliminarTexto(File fichero) {
        fichero = new File("fichero.txt");
        return fichero;
    }

    private static void insertarTexto(File dirTrabajo, String nombreFichero, int pos, String insercion) throws FileNotFoundException, IOException {
        //Declaraciones
        Scanner sc = new Scanner(System.in);
        RandomAccessFile ficheroRW = new RandomAccessFile(dirTrabajo+File.separator+nombreFichero, "rw");

        //Inicio
        System.out.print("Que desea hacer?\n1. Insertar en una posición concreta(1)\n2. Insertar al final del fichero(2): ");
        int opcion = sc.nextInt();
        switch (opcion) {
            case 1: //En caso de querer insertar en una posición específica
                System.out.print("\nIndica la posición donde insertar el texto: ");
                pos = sc.nextInt();
                try {
                    ficheroRW.seek(pos);
                    ficheroRW.write((insercion + "\n").getBytes());
                } catch (IOException e) {
                    System.out.println("Ha ocurrido un error:" + e.getMessage());
                }   break;
            case 2: //En caso de querer insertar al final del fichero
                try {
                    ficheroRW.seek(ficheroRW.length());
                    ficheroRW.writeBytes(insercion + "\n");
                } catch (IOException e) {
                    System.out.println("Ha ocurrido un error:" + e.getMessage());
                }   break;
            default: //En caso de opción no válida
                System.out.println("Opción no válida.");
                break;
        }

        //Cierres
        ficheroRW.close();
        sc.close();
    }

    private static void leerFichero(File dirTrabajo, String nombreFichero) throws IOException {
        
        FileReader fileReader = new FileReader(dirTrabajo + File.separator + nombreFichero);
        
        for(int i = 0; i < 300; i++) {
            int caracter = fileReader.read();
            if(caracter == -1) {
                break;
            }
            System.out.print((char) caracter);
        }
        System.out.println("");

        fileReader.close();
    }

    private static void crearFichero(File dirTrabajo, String nombreFichero) {
        File fichero = new File(dirTrabajo + File.separator + nombreFichero);
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
        System.out.print("""
                           --- Menú Principal ---
                           1. Crear fichero
                           2. Leer fichero
                           3. Insertar texto
                           4. Eliminar texto
                           5. Eliminar fichero
                           6. Resumen de ficheros que empiezan por...
                           7. Salir
                           Elige una opción (1-7): """
                        );
    }
}
