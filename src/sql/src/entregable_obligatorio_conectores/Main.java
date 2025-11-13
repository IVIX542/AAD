package entregable_obligatorio_conectores;

//Imports
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * @author Iván López Benítez
 * */
public class Main {
	
	//Variables globales
	private final static String SALUDO = "Bienvenido al Gestor de Bases de Datos de MySQL";
	private final static ArrayList<String> tablas = new ArrayList<String>(); 
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println(SALUDO);
		
		//Pedimos el usuario de la BBDD
		System.out.print("Introduce el usuario: ");
		final String USUARIO = sc.nextLine();
		
		//Pedimos la contraseña de la BBDD
		System.out.print("\nIntroduce la contraseña para " + USUARIO + ":" );
		final String CONTRASENIA = sc.nextLine();
		
		//Pedimos la BBDD con la que trabajaremos
		System.out.print("\nIntroduce la BBDD con la que quieras trabajar: ");
		final String BBDD = sc.nextLine();
		
		//Intentamos establecer la conexión con la base de datos
		try (Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + BBDD, USUARIO, CONTRASENIA)) {
			System.out.println("Conexión exitosa");
			System.out.println("----------------");
			
			System.out.println("\nEstas son las tablas disponibles para esta base de datos " + BBDD + ":");
			
			mostrarTablas(conexion);
			
			System.out.print("Escribe la tabla con la que desearías trabajar:");
			final String TABLA = sc.nextLine();
			
			
			
			conexion.close();
		} catch (SQLException e) {
            System.out.println("Error de conexión o SQL:");
            e.printStackTrace();
        }
		
		sc.close();
	}
	
	/**
	 * Método que muestra las tablas de la base de datos con la que se ha establecido conexión
	 * @param Connection conexión con la base de datos
	 * */
	public static void mostrarTablas(Connection conexion) {
		String sqlShowTables = "SHOW TABLES";
		
		try (PreparedStatement psSelect = conexion.prepareStatement(sqlShowTables)) {
            
            try (ResultSet rs = psSelect.executeQuery()) { //Probamos la ejecución de la consulta
                while (rs.next()) {
                    System.out.println(rs.getString(1));
                    tablas.add(rs.getString(1));
                }
                System.out.println("--------------------------");
            }
            
        } catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

}
