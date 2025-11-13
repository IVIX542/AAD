package sqlsql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestMySQL {

	public static void main(String[] args) {

		    String url = "jdbc:mysql://localhost:3306/jardineria"; //Aqui le tengo que decir el conector, el local host
	        String usuario_mysql = "root"; //en mi caso el 3306 y la base de datos a la que me quiero conectar, que es la world
	        String contrasena_mysql = "12345678"; // y tambien mi usuario y mi contraseá
	       //JDBD= Java Data Base Connectivity

	        try {
	            Connection conexion_con_base_de_datos = DriverManager.getConnection(url, usuario_mysql, contrasena_mysql); //hago la conexión
	            System.out.println("¡Conexión exitosa!"); //muestro que ha sido exitosa
	            conexion_con_base_de_datos.close(); //cierro conexion con base de datos
	        } catch (SQLException e) { //sino, capturo el error SQLException e
	            e.printStackTrace(); //imprime en la salida el erorr que se ha producido
	        }	  
	}

}
