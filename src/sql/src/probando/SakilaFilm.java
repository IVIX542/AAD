package probando;
import java.sql.*; //Meto la librería para trabajar en bases de datos relacionales, y apañao'

public class SakilaFilm{
    public static void main(String[] args) {
        // Lo guardo en Strings lo que me pedirá para la conexión
        String url = "jdbc:mysql://localhost:3306/jardineria";
        String user = "root";
        String password = "root"; // cambia si tu contraseña es distinta

        try (Connection conn = DriverManager.getConnection(url, user, password)) { 
            System.out.println("Ya hay conexión!");

            // Creo estatement, que le pasaré el contenido de la consulta
            String contenido_consulta="SELECT * FROM jardineria.cliente LIMIT 1"; //sin jardineria también valdría
            Statement stmt = conn.createStatement();

            // Ejecutamos una consulta que no devuelve muchos datos, solo para obtener metadatos
            ResultSet rs = stmt.executeQuery(contenido_consulta); //a la que declaro el result le paso la statement y la consulta

            // Como tenemos que obtener metadatos del resultado, declaro una variable de tipo ResultSetMetaData 
            ResultSetMetaData metaData = rs.getMetaData(); //El método getMetaData() devuelve un objeto de tipo ResultSetMetaData 
            //que describe la estructura de lo que hay en el ResultSet.
            int numColumnas = metaData.getColumnCount(); //Me devuelvo un entero, y ese entero es el número total de columnas que tiene la tabla
            //como luego voy a recorrer para leer cada columna, pues me interesa ese valor para hacer el bucle

            System.out.println("\n Estructura de la tabla cliente:");
            System.out.println("\n NOMBRE                  TIPO DE DATOS        TAMAÑO DE LOS DATOS\n\n");

            for (int i = 1; i <= numColumnas; i++) { //para recorrer cada una de ellas
                String nombreColumna = metaData.getColumnName(i); //me dice el nombre de la columna
                String tipoColumna = metaData.getColumnTypeName(i);//me dice el tipo de datos que almacena la columna
                int tamañoColumna = metaData.getColumnDisplaySize(i); //y el tamaño de datos que ocupa
                System.out.printf("%-25s %-15s  tamaño: %d\n", nombreColumna, tipoColumna, tamañoColumna); //el tamaño muestra el número de caracteres que se pueden visualizar
                // el -25s Imprime una cadena (s) en un campo de 25 caracteres, alineada a la izquierda (por el -).
                // el -15s Imprime una cadena (s) en un campo de 25 caracteres, alineada a la izquierda (por el -).
                //lo he hecho por darle algo de formato y estructura
            } //cierro for

            rs.close(); //ya no trabajo más con el result, lo cierro
            stmt.close();//y la statement tambien

        } catch (SQLException e) {
            System.err.println("No se puedo conectar con la base de datos el error será el: ");
            e.printStackTrace();
        }
    }
}
