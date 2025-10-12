package entregable_obligatorio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

/**
 * Clase principal que proporciona una utilidad de línea de comandos para
 * realizar operaciones básicas de gestión de ficheros.
 * Permite crear, leer, modificar y eliminar ficheros, así como listar
 * ficheros que coincidan con un prefijo.
 */
public class ManejarFichero {
    /**
     * @author Iván López Benítez
     * @version 2.0
     * Fecha: 12/10/2025
     * Crear fichero
     * Leer fichero (primeros 300 caracteres)
     * Insertar texto (punto de inserción y final del fichero)
     * Eliminar texto (cierto número de caracteres desde un punto de inserción)
     * Eliminar fichero
     * Resumen de "ficheros que empiezan por"
     */

    /**
     * Método principal que sirve como punto de entrada para la aplicación.
     * Gestiona el menú de opciones y la interacción con el usuario.
     *
     * @param args Argumentos de la línea de comandos (no se utilizan).
     */
    public static void main(String[] args) {
        //Declaracion escaner
        Scanner sc = new Scanner(System.in);

        //Inicio
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
                        // La posición se pide dentro del método, por eso se pasa 0.
                        insertarTexto(dirTrabajo, nombreFicheroInsertar, 0, insercion);
                    } catch (IOException e) {
                        System.out.println("\nHa ocurrido un error:" + e.getMessage());
                    }
                    break;

                case 4:
                    System.out.print("Indica el nombre del fichero donde eliminar texto: ");
                    String nombreFicheroEliminarTexto = sc.next();
                    System.out.print("Indica la posición desde donde eliminar: ");
                    long posicion = sc.nextLong();
                    System.out.print("Indica el número de caracteres a eliminar: ");
                    int numCaracteres = sc.nextInt();
                    try {
                        eliminarTexto(dirTrabajo, nombreFicheroEliminarTexto, posicion, numCaracteres);
                    } catch (IOException e) {
                        System.out.println("Ha ocurrido un error:" + e.getMessage());
                    }
                    break;

                case 5:
                    System.out.print("Indica el nombre del fichero a eliminar: ");
                    String nombreFicheroEliminar = sc.next();
                    eliminarFichero(dirTrabajo, nombreFicheroEliminar);
                    break;
                case 6:
                    System.out.print("Indica el nombre o prefijo de los ficheros a buscar: ");
                    String prefijo = sc.next();
                    resumenFicherosQueEmpiezanPor(dirTrabajo, prefijo);
                    break;
                default:
                    System.out.println("Opción no válida");
            }
            menuPrincipal();
            opcion = sc.nextInt();
        }
        sc.close();
    }

    /**
     * Muestra por pantalla los ficheros que se encuentran en el directorio de trabajo
     * y cuyo nombre comienza con el prefijo especificado.
     *
     * @param dirTrabajo El directorio donde se realizará la búsqueda.
     * @param prefijo    La cadena de texto con la que deben comenzar los nombres de los ficheros.
     */
    private static void resumenFicherosQueEmpiezanPor(File dirTrabajo, String prefijo) {

        File[] listaFicheros = dirTrabajo.listFiles();

        if (listaFicheros != null && listaFicheros.length > 0) {
            System.out.println("Ficheros en el directorio " + dirTrabajo.getAbsolutePath() + ":");
            for (File fichero : listaFicheros) {
                if (fichero.isFile() && fichero.getName().startsWith(prefijo)) {
                    System.out.println(" - " + fichero.getName());
                }
            }
        } else {
            System.out.println("No hay ficheros en el directorio " + dirTrabajo.getAbsolutePath());
        }
    }

    /**
     * Elimina un fichero del directorio de trabajo especificado.
     * Informa al usuario si la operación fue exitosa o no.
     *
     * @param dirTrabajo    El directorio donde se encuentra el fichero.
     * @param nombreFichero El nombre del fichero que se va a eliminar.
     */
    private static void eliminarFichero(File dirTrabajo, String nombreFichero) {
        File fichero = new File(dirTrabajo + File.separator + nombreFichero);
        if (fichero.delete()) {
            System.out.println("Fichero " + fichero.getName() + " eliminado.");
        } else {
            System.out.println("No se pudo eliminar el fichero " + fichero.getName() + ". Es posible que no exista.");
        }
    }

    /**
     * Elimina un número determinado de caracteres (bytes) de un fichero a partir de una posición.
     * Para ello, lee el contenido posterior a la sección eliminada y lo desplaza
     * hacia el inicio de dicha sección, sobreescribiéndola. Finalmente, trunca el fichero.
     *
     * @param directorio     El directorio que contiene el fichero a modificar.
     * @param nombreFichero  El nombre del fichero a modificar.
     * @param posicion       El byte de inicio (índice) desde donde se comenzará a eliminar.
     * @param numCaracteres  El número de bytes (caracteres) que se van a eliminar.
     * @throws IOException Si ocurre un error de entrada/salida durante la operación con el fichero.
     */
    public static void eliminarTexto(File directorio, String nombreFichero, long posicion, int numCaracteres) throws IOException {
        // Usamos try-with-resources para asegurar que el fichero se cierra siempre,
        // incluso si ocurre una excepción.
        try (RandomAccessFile ficheroRW = new RandomAccessFile(new File(directorio + File.separator + nombreFichero), "rw")) {

            // --- Validación de parámetros ---
            if (posicion < 0 || numCaracteres < 0 || posicion + numCaracteres > ficheroRW.length()) {
                System.err.println("Error: La posición o el número de caracteres están fuera de los límites del fichero.");
                return; // Salimos del método si los datos son inválidos.
            }

            // --- Lógica principal de borrado ---

            // Puntero de lectura: donde empezamos a leer los datos que queremos conservar.
            long punteroLectura = posicion + numCaracteres;

            // Puntero de escritura: donde vamos a empezar a sobreescribir los datos.
            long punteroEscritura = posicion;

            // Buffer para leer y escribir en bloques, es más eficiente que ir byte a byte.
            byte[] buffer = new byte[1024];
            int bytesLeidos;

            // Movemos el puntero de lectura al inicio de los datos que queremos conservar.
            ficheroRW.seek(punteroLectura);

            // Bucle para leer los datos restantes del fichero y moverlos hacia atrás.
            // Leemos un bloque desde `punteroLectura`.
            while ((bytesLeidos = ficheroRW.read(buffer)) != -1) {
                // Movemos el puntero de escritura a su posición actual.
                ficheroRW.seek(punteroEscritura);
                // Escribimos el bloque que acabamos de leer.
                ficheroRW.write(buffer, 0, bytesLeidos);

                // Actualizamos la posición del puntero de escritura.
                punteroEscritura += bytesLeidos;

                // El puntero de lectura se actualiza automáticamente con cada .read(),
                // así que no necesitamos moverlo manualmente dentro del bucle.
            }

            // Una vez movido todo el contenido, el fichero todavía tiene la longitud original.
            // Truncamos (cortamos) el fichero a su nuevo tamaño final.
            ficheroRW.setLength(punteroEscritura);
        }
        System.out.println("\nOperación completada con éxito.");
    }

    /**
     * Inserta un texto en un fichero. Ofrece dos modos: añadir al final o
     * sobrescribir desde una posición específica.
     * <strong>¡Atención!</strong> La inserción en una posición específica sobrescribe
     * los datos existentes, no los desplaza.
     *
     * @param dirTrabajo    El directorio donde se encuentra el fichero.
     * @param nombreFichero El nombre del fichero a modificar.
     * @param pos           Parámetro inicial de posición (se pide de nuevo al usuario).
     * @param insercion     El texto que se va a insertar/escribir.
     * @throws FileNotFoundException Si el fichero no se encuentra.
     * @throws IOException           Si ocurre un error de entrada/salida.
     */
    private static void insertarTexto(File dirTrabajo, String nombreFichero, int pos, String insercion) throws FileNotFoundException, IOException {
        //Declaraciones
        // NOTA: No se debe crear un nuevo Scanner aquí y cerrarlo, ya que cerraría System.in
        // para toda la aplicación. Se reutiliza el del main.
        Scanner sc = new Scanner(System.in);
        RandomAccessFile ficheroRW = new RandomAccessFile(dirTrabajo + File.separator + nombreFichero, "rw");

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
                }
                break;
            case 2: //En caso de querer insertar al final del fichero
                try {
                    ficheroRW.seek(ficheroRW.length());
                    ficheroRW.writeBytes(insercion + "\n");
                } catch (IOException e) {
                    System.out.println("Ha ocurrido un error:" + e.getMessage());
                }
                break;
            default: //En caso de opción no válida
                System.out.println("Opción no válida.");
                break;
        }

        //Cierres
        ficheroRW.close();
    }

    /**
     * Lee los primeros 300 caracteres de un fichero y los muestra por consola.
     * Si el fichero tiene menos de 300 caracteres, lee hasta el final.
     *
     * @param dirTrabajo    El directorio donde se encuentra el fichero.
     * @param nombreFichero El nombre del fichero a leer.
     * @throws IOException Si el fichero no existe o no se puede leer.
     */
    private static void leerFichero(File dirTrabajo, String nombreFichero) throws IOException {

        // Se recomienda usar try-with-resources para el manejo automático del cierre.
        try (FileReader fileReader = new FileReader(dirTrabajo + File.separator + nombreFichero)) {
            for (int i = 0; i < 300; i++) {
                int caracter = fileReader.read();
                if (caracter == -1) { // -1 indica el final del fichero
                    break;
                }
                System.out.print((char) caracter);
            }
            System.out.println("");
        }
    }

    /**
     * Crea un nuevo fichero vacío en el directorio de trabajo especificado.
     * Si el fichero ya existe, informa al usuario y no realiza ninguna acción.
     *
     * @param dirTrabajo    El directorio donde se creará el fichero.
     * @param nombreFichero El nombre del fichero a crear.
     */
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

    /**
     * Muestra el menú principal de opciones al usuario.
     */
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