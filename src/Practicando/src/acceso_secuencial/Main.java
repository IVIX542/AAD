package acceso_secuencial;

//Utilidades
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;

//Excepciones
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		
		//Declaraciones
		Scanner sc = new Scanner(System.in);
		String nombre, apellido, nombreDirTrabajo="src//acceso_secuencial//";
		//Directorio de trabajo
		File dirTrabajo=new File(nombreDirTrabajo);
		if(!dirTrabajo.exists()) {
			dirTrabajo.mkdir();
			System.out.println("Directorio de trabajo creado.");
		} else {
			System.out.println("El directorio ya existe.");
		}
		
		int movil;
		File fich = new File(nombreDirTrabajo + "Agenda.txt");
		
		//Introducir nombre
		System.out.print("Introduzca su nombre: ");
		nombre = sc.nextLine();
		
		
		//Introducir apellido
		System.out.print("\nIntroduzca su primer apellido: ");
		apellido= sc.nextLine();
		
		//Introducir tlf
		System.out.print("\nIntroduzca su telefono movil: ");
		movil = sc.nextInt();
		
		String contacto = nombre + ", " + apellido + ", " + movil + ".";
		
		FileWriter fw = new FileWriter(fich);
		
		fw.append("Hola\n");
		sc.close();
		fw.close();
	}

}
