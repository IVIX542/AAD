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
		System.out.print("\nIntroduce la contraseña para " + USUARIO + ": " );
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
			
			int i = 0;
			boolean tablaCorrecta = false;
			String tabla = "";
			
			while(i<tablas.size() && !tablaCorrecta) {
				
				System.out.print("Escribe la tabla con la que desearías trabajar: ");
				tabla = sc.nextLine().toLowerCase();
				
				if(!tabla.equals(tablas.get(i).toLowerCase())) {
					System.out.println("Tabla incorrecta, intente de nuevo.");
				} else {
					tablaCorrecta = true;
				}
				i++;
			}
			
			System.out.println("Tabla " + tabla + " seleccionada.");
			
			boolean salir = false;
			
			while(!salir) {
				menuTabla();
				
				System.out.print("\nTeclea la opción que desea realizar: ");
				int eleccion = Integer.parseInt(sc.nextLine());
				
				switch(eleccion) {
				case 1:
					mostrarRegistros(BBDD, tabla, conexion);
					break;
				case 2:
					break;
				case 3:
					break;
				case 4:
					break;
				case 5:
					break;
				case 6:
					break;
				case 7:
					salir = true;
					break;
				}
				
				
			}
			
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
	
	
	/**
	 * Método que muestra un menú de elección para realizar acciones sobre una tabla de la BBDD
	 * @param Scanner sc utilizado para la entrada por teclado
	 * @return int eleccion de la acción a realizar
	 * */
	public static void menuTabla() {
		
				
		System.out.println("Teclea la opción que deseas realizar:");
		System.out.println("\n1. Ver todos los registros de la tabla");
		System.out.println("2. Ver el nombre de todas las columnas de la tabla");
		System.out.println("3. Insertar nuevo registro en la tabla");
		System.out.println("4. Modificar un registro existente en la tabla");
		System.out.println("5. Borrar un registro existente");
		System.out.println("6. Ver el tipo de datos y extensión de las columnas de la tabla");
		System.out.println("7. Salir del programa");
		
	}
	
	/**
	 * Método para ver todos los registros de una tabla
	 * @param String BBDD
	 * @param String TABLA
	 * */
	public static void mostrarRegistros(final String BBDD, final String TABLA, Connection conexion) {
		String sqlShowTables = "SELECT * FROM " + BBDD + "." + TABLA;
		
		try (PreparedStatement psSelect = conexion.prepareStatement(sqlShowTables)) {
            
            try (ResultSet rs = psSelect.executeQuery()) { //Probamos la ejecución de la consulta
            	ResultSetMetaData rsMetaData = rs.getMetaData();
            	int numColumnas = rsMetaData.getColumnCount();
            	
            	for (int i = 1; i <= numColumnas; i++) {
                    System.out.print(rsMetaData.getColumnName(i) + "\t");
                }
            	
            	System.out.println("\n---------------------------");
            	
                while (rs.next()) {
                	for (int i = 1; i <= numColumnas; i++) {
                        
                        String valor = String.valueOf(rs.getObject(i));
                        
                        System.out.print(valor + "\t");
                    }
                	System.out.println();
                }
                System.out.println("--------------------------");
            }
             
        } catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
