package sqlsql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;


public class Primeras_consulas_Mysql {

	public static void main(String[] args) {
		
	        try (Connection conexion_con_base_de_datos = DriverManager.getConnection("jdbc:mysql://localhost:3306/jardineria", "root",  "12345678")){
	            System.out.println("¡Conexión exitosa!"); //muestro que ha sido exitosa
	            
	            // Aquí creo un string que guardo literalmente la sentencia que le pondría a mysql para la consulta
	            String primera_consulta = "SELECT nombre_cliente, telefono FROM jardineria.cliente";
                //lo de jardineria me lo podía haber ahorrado porque ya sabe el programa que estamos en la base de datos de jardineria
	            
	            
	            // Crear objeto Statement para ejecutar la consulta
	            Statement statement_primera_consulta = conexion_con_base_de_datos.createStatement();

	            // para el result le paso por parametro la línea de la consulta y obtenendré el resultado de la consulta
	            ResultSet resultado_primera_consulta = statement_primera_consulta.executeQuery(primera_consulta);
                //executeQuery para ejecutar esa consulta
	            System.out.println("\n A continuación te enseñaré la lista de clientes:");
	        

	            // Recorrer los resultados
	            while (resultado_primera_consulta.next()) { 
	            	//Se ejecuta el while mientras devuelva TRUE, y el .next() lo que va leyendo es la linea siguiente
	            	//cada vez, así que mientras siga habiendo lineas, que guarde ese nombre_cliente en el string y lo muestre
	            	//y lo mismo con el telefono del cliente
	                String nombre_cliente = resultado_primera_consulta.getString("nombre_cliente");
	                String telefono_cliente = resultado_primera_consulta.getString("telefono");
	                System.out.println("Nombre: " + nombre_cliente + " | Teléfono: " + telefono_cliente);
	            }

	            //TENGO QUE CERRAR tantos los ResultSet como los Statement cuando haya acabado de trabajar con ellos
	            resultado_primera_consulta.close();
	            statement_primera_consulta.close();

	            System.out.println("\n Primera consulta finalizada");
	 
	            conexion_con_base_de_datos.close(); //cierro conexion con base de datos porque he acabado mis consultas
	        } //cierro el try
	        catch (SQLException e) { //sino, capturo el error SQLException e
	            e.printStackTrace(); //imprime en la salida el erorr que se ha producido
	        } //cierro el catch	  		
	}//cierro el main
}//cierro class
