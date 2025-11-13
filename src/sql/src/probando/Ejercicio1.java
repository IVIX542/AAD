package probando;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Ejercicio1 {
	
/**
 * Hacer un programa en java que, primero visualice todas las columnas de la tabla "countrylanguage" de la base de
 * datos "world" y luego hacer una parte en la que se creen 4 nuevos registros, que tengan los 4 campos 
 * "CountryCode", "Language", "IsOfficial" y "Percentage" pero quiero además que con scanners introduzca el usuario
 * manualmente todos los campos para acabar, quiero que una vez insertados los 4 nuevos registros, pregunta:
 * quieres modificar alguno? y el usuario introduce por teclado un "CountryCode", y en función de ese, que se
 * muestren esos valores para ese "CountryCode", y que el usuario pueda modificar el valor de ese
 * "Language", "IsOfficial" o "Percentage" asociado a ese "CountryCode", si el "CountryCode" introducido no existe,
 * entonces que muestre que no se ha podido acceder a ese registro, no hace falta que te vuelva a preguntar si
 * quiere introducirlo nuevamente.
 * */
	
	public static void main(String[] args) {
		try(Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/world", "root", "12345678")){
			System.out.println("Conectado a la base de datos.");
			
			String sqlSelect = "SELECT * FROM countrylanguage";
			try (PreparedStatement psSelect = conexion.prepareStatement(sqlSelect)) { //le paso la statement con su argumento, y si se puede hacer esa consulta, continua
				try (ResultSet rs = psSelect.executeQuery()) {
					System.out.println("---TABLA COUNTRYLANGUAGE---");
					System.out.println("\n|CountryCode|Language|IsOfficial|\n");
                    while (rs.next()) {
                        System.out.println("|" + rs.getString("CountryCode") + "|        |" + rs.getString("Language")
                        + "|     |" + rs.getString("IsOfficial") + "|");
                    }
                    
                }
            }
			
		}catch(SQLException e){
			System.out.println("Error de conexión o SQL: " + e.getMessage());
		}
	}
}
