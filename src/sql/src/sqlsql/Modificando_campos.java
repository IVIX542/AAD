package sqlsql;

import java.sql.*;

public class Modificando_campos {

    public static void main(String[] args) {

    	
        try (Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/jardineria", "root", "12345678")) {
            System.out.println("Conectado a la base de datos.");

            int codigoCliente = 41; // el cliente que queremos modificar lo guardo en esta variable, este valor en la base de datos corresponde a  codigo_cliente
            String sqlSelect = "SELECT nombre_cliente, telefono, ciudad FROM cliente WHERE codigo_cliente = ?";
            //le guardo en el string sqlselect la consula que le quiero hacer
            
            try (PreparedStatement psSelect = conexion.prepareStatement(sqlSelect)) { //le paso la statement con su argumento, y si se puede hacer esa consulta, continua
                psSelect.setInt(1, codigoCliente); //ahora en psSelect tenemos seleccionado el registro con codigo_cliente, 41, porque es la primera columna en la tabla, por eso el 1
                 //le inserto el 41 del codigoCliente a mi tabla
                try (ResultSet rs = psSelect.executeQuery()) {
                    if (rs.next()) {
                        System.out.println("Antes de actualizar:");
                        System.out.println("Nombre: " + rs.getString("nombre_cliente"));
                        System.out.println("Teléfono: " + rs.getString("telefono"));
                        System.out.println("Ciudad: " + rs.getString("ciudad"));
                        //esta leyendo de rs esos campos que están en el 41, y le he dicho que me vaya mostrando lo que hay dentro
                        //de esas columnas nombre_cliente, telefono, o ciudad, con esos getString, basándome en el tipo de datos
                        //que se almacenan ahí en la base de datos
                    }//cierro if
                    else {
                        System.out.println("No se encontró el cliente con código " + codigoCliente);   
                    } //cierro else
                }//cierro el try de la consulta para visualizar el registro 41
            }

            // Modificar un campo, voy a elegir telefono
            String nuevoTelefono = "67934537530"; //guardo en un string el nuevo telefono
            //recordamos que el Int codigoCliente=41; está definido arriba ya 
            String sqlUpdate = "UPDATE cliente SET telefono = ? WHERE codigo_cliente = ?"; //escribo la nueva consulta que quiero hacerle
           //LE PASO INTERROGACIONES, porque son los parámetros que se va a encargar java de rellenar
            //en mySQL haríamos UPDATE cliente SET telefono = '600123456' WHERE codigo_cliente = 41;
            try (PreparedStatement psUpdate = conexion.prepareStatement(sqlUpdate)) {
                psUpdate.setString(1, nuevoTelefono); //va a la primera interrogación
                psUpdate.setInt(2, codigoCliente); //va al campo de la segunda interrogación

                int filas = psUpdate.executeUpdate(); //metodo que se emplea para saber lo que se ha modificado, cuantas filas
                System.out.println("Teléfono actualizado. Filas modificadas: " + filas);
            }

            // Comprobar el cambio
            try (PreparedStatement psSelect = conexion.prepareStatement(sqlSelect)) {
                psSelect.setInt(1, codigoCliente);
             //recordamos que sqlSelect lo que pasa es: "SELECT nombre_cliente, telefono, ciudad FROM cliente WHERE codigo_cliente = ?";
             //y a esa primera interrogacion le paso codigoCliente
                try (ResultSet rs = psSelect.executeQuery()) { //algoritmo para leer las columnas de una fila
                    if (rs.next()) {
                        System.out.println("Después de actualizar:");
                        System.out.println("Nombre: " + rs.getString("nombre_cliente"));
                        System.out.println("Teléfono: " + rs.getString("telefono"));
                        System.out.println("Ciudad: " + rs.getString("ciudad"));
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println("Error de conexión o SQL:");
            e.printStackTrace();
        }
    }
}

//PROPUESTA DE EJERCICIO
/* Hacer un programa en java que, primero visualice todas las columnas de la tabla countrylanguage de la base de datos world
 y luego hacer una parte en la que se creen 4 nuevos registros, que tengan los 4 campos CountryCode Language IsOfficial y Percentage
 pero quiero además que con scanners introduzca el usuario manualmente todos los campos
 para acabar, quiero que una vez insertados los 4 nuevos registros, pregunta, quieres modificar alguno?
 y el usuario introduce por teclado un CountryCode, y en función de ese, que se muestren esos valores para ese CountryCode
 y que el usuario pueda modificar el valor de ese Languaje,IsOfficial o Percentage asociado a ese CountryCode, si el CountryCode introducido
 no existe, entonces que muestre que no se ha podido acceder a ese registro, no hace falta que te vuelva a preguntar si quiere introducirlo nuevamente */
 