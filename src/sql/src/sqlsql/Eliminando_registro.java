package sqlsql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Eliminando_registro {

    public static void main(String[] args) {
        
    	 String url = "jdbc:mysql://localhost:3306/jardineria"; //Aqui le tengo que decir el conector, el local host
	        String usuario_mysql = "root"; //en mi caso el 3306 y la base de datos a la que me quiero conectar, que es la world
	        String contrasena_mysql = "12345678"; // y tambien mi usuario y mi contraseá
	       //JDBD= Java Data Base Connectivity
    	
        try (Connection conexion_con_base_de_datos = DriverManager.getConnection(url,usuario_mysql,contrasena_mysql)) {
            System.out.println("¡Conexión exitosa!"); // Si lo de dentro del try se cumple, todo guay, se ejecutará esta linea y mostrará esto
            // Aquí meto lo que quiero pasarle como código a mysql
            String eliminar_cliente = "DELETE FROM cliente WHERE codigo_cliente = 40;";
            
            // Crear objeto Statement
            Statement statement_eliminar = conexion_con_base_de_datos.createStatement();
            
            // Ejecutar la eliminación
            int filas_afectadas = statement_eliminar.executeUpdate(eliminar_cliente); //le paso por parámetro el string con el código
             //OJO!! EL MÉTODO executeUpdate() devuelve el número de filas o registroso afectados por la operación.
            // Mostrar resultado
            if (filas_afectadas > 0) {
                System.out.println("Registro eliminado correctamente. Filas afectadas: " + filas_afectadas);
            } else {
                System.out.println(" No se ha eliminado ningún registro.");
            }

     
            statement_eliminar.close(); //cierro el statement porque he terminado de trabajar con él
            
            System.out.println("\n Operación DELETE finalizada.");
            
            conexion_con_base_de_datos.close(); //cierro conexion con base de datos porque he acabado mis consultas
        } 
        catch (SQLException e) {  //y el catch relativo al mySQL
            e.printStackTrace(); 
        } 		
    }
}
