

package bien_hecho;

import java.io.File;
import java.io.IOException;

public class ArgumentosDirectorioFichero {
	//IMPORTANTEEE, EL DIRECTORIO QUE LE PASES NO PUEDE TENER ESPACIOS, es decir, una carpeta no puede llamarse "Nueva Carpeta", deber� ser Nueva_Carpeta", sino, crear� una que se llame 
    //Nueva y ser� una carpeta, porque cuando le das un espacio, el ya piensa que le has metido m�s argumentos, en este caso, se piensa que le has metido 3
    public static void main(String[] args) {
        // Comprobar que se pasa exactamente un argumento
        switch (args.length) {
            case 0:
                System.out.println("No se ha pasado ning�n argumento. Por favor, ejecuta el programa con un nombre de fichero como argumento.");
                return; // Termina el programa si no hay argumentos
            case 2:
                System.out.println("Se han pasado dos argumentos, el primero es el nombre sin la extensi�n del fichero, el segundo ser�a el directorio");
                return;
            default:
                System.out.println("Me has pasado un argumento, genial!");
                break;
        }
        String nombreFichero = args[0]; // Nombre del archivo pasado como argumento
        String elDirectorio = args[1];
        //guarda en ese String, el nombre que tiene args[0] almacenado dentro, que sera datos.txt que es lo que le
        //hemos escrito en la consola de comandos
        
        // Construir la ruta completa
        String ruta_y_fichero = elDirectorio + "\\" + nombreFichero + ".txt";
        
        File directorioFile = new File(elDirectorio); //creo un file con mi ruta
        
       
        if (!directorioFile.exists()) { //si da false, entonces no existe, ahora puedo crearlo
            if (directorioFile.mkdirs()) { //Creo el directorio
                System.out.println("Directorio creado correctamente: " + directorioFile.getAbsolutePath());
            } else {
                System.out.println("No se pudo crear el directorio: " + directorioFile.getAbsolutePath());
                return;
            }
        }

        // Ahora s� crear el fichero
        File fichero = new File(ruta_y_fichero); //aqui va mi ruta con el fichero

        try {
            if (fichero.createNewFile()) {
                System.out.println("Fichero creado en: " + fichero.getAbsolutePath());
            } else {
                System.out.println("El fichero ya existe en: " + fichero.getAbsolutePath());
            } //cierro el try
            
        } catch (IOException e) {
            System.out.println("Error al crear el fichero: " + e.getMessage());
        }  //cierro el catch
                     
    } //ciero el main
}//cierro la class
    