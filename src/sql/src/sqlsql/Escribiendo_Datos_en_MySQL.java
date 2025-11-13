package sqlsql;

import java.sql.*;

public class Escribiendo_Datos_en_MySQL {

	public static void main(String[] args) {
		
		        //Para hacer la conexion
		        String url = "jdbc:mysql://localhost:3306/jardineria";
		        String usuario = "root";      
		        String contraseña = "12345678";    

		        Connection conexion = null;
		        PreparedStatement insertStmt = null;
		        PreparedStatement selectStmt = null;
		        ResultSet rs = null;

		        try {
		           
		            conexion = DriverManager.getConnection(url, usuario, contraseña);
		            System.out.println("Conectado a la base de datos.");

		           
		            String sqlInsert = "INSERT INTO cliente " +
		                    "(codigo_cliente, nombre_cliente, nombre_contacto, apellido_contacto, " +
		                    "telefono, fax, linea_direccion1, ciudad, codigo_empleado_rep_ventas) " +
		                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

		            insertStmt = conexion.prepareStatement(sqlInsert);
		            insertStmt.setInt(1, 40);                  // codigo_cliente
		            insertStmt.setString(2, "Olga Pérez");     // nombre_cliente
		            insertStmt.setString(3, "Olga ");           // nombre_contacto
		            insertStmt.setString(4, "Pérez");            // apellido_contacto
		            insertStmt.setString(5, "52322344342");      // telefono
		            insertStmt.setString(6, "12452789");        // fax
		            insertStmt.setString(7, "Calle Falsa 41");  // linea_direccion1
		            insertStmt.setString(8, "Madrid");           // ciudad
		            insertStmt.setNull(9, Types.INTEGER);        // codigo_empleado_rep_ventas = NULL

		            int filasInsertadas = insertStmt.executeUpdate();
		            System.out.println("Filas insertadas: " + filasInsertadas);

		     
		            String sqlSelect = "SELECT codigo_cliente, nombre_cliente, telefono, ciudad FROM cliente WHERE codigo_cliente = ?";
		            selectStmt = conexion.prepareStatement(sqlSelect);
		            selectStmt.setInt(1, 40);

		            rs = selectStmt.executeQuery();

		            while (rs.next()) {
		                int codigo = rs.getInt("codigo_cliente");
		                String nombre = rs.getString("nombre_cliente");
		                String telefono = rs.getString("telefono");
		                String ciudad = rs.getString("ciudad");

		                System.out.println("Cliente: " + codigo + ", " + nombre + ", " + telefono + ", " + ciudad);
		            }

		        } catch (SQLException e) {
		            e.printStackTrace();
		        } finally {
		            // Cerrar recursos
		            try { if (rs != null) rs.close(); } catch (SQLException ignored) {}
		            try { if (insertStmt != null) insertStmt.close(); } catch (SQLException ignored) {}
		            try { if (selectStmt != null) selectStmt.close(); } catch (SQLException ignored) {}
		            try { if (conexion != null) conexion.close(); } catch (SQLException ignored) {}
		        }
		    }
		}