/**
 * Clase Main que contiene el método main para la ejecución del programa.
 * @author Iván López Benítez
 * @version 1.1
 * Fecha: 26/10/2025
*/

//Manejo de ficheros
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;

//XML
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.Source;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;

//DOM
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

//Excepciones
import java.io.IOException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

public class Main {
    public static ArrayList<Juego> juegos = new ArrayList<>();

    /**
     * Método principal para la ejecución del programa.
     * @param args
     */
    public static void main(String[] args) {
        //Declaraciones
        File txt = new File("Catalogo_inicial_sin_formato.txt");
        File xml = new File("Catalogo_juegos.xml");

        //Llamadas a métodos
        extraerDatos(txt);
        crearXML(xml);

    }

    /**
     * Método para extraer los datos del fichero de texto y mostrarlos por consola.
     * @since 1.0
     * @param txt File fichero de texto a leer
     */
    public static void extraerDatos(File txt){
        try {
            //Declaracion de variables relacionadas con el txt
            FileReader fr = new FileReader(txt);
            BufferedReader br = new BufferedReader(fr);
            String linea;
            //Lectura del fichero línea a línea
            while((linea = br.readLine()) != null && !linea.isEmpty()) {
                Juego juego = new Juego();
                //Extracción de datos de la línea
                // ID
                String id = linea.substring(4, 9);
                // Título
                String titulo = linea.substring(19, linea.indexOf("Año")).trim();
                // Año
                String anio = linea.substring((linea.indexOf("Año") + 4), (linea.indexOf("Compañía"))).trim();
                // Compañía
                String compania = linea.substring((linea.indexOf("Compañía") + 10), (linea.indexOf("Consola"))).trim();
                //Consola
                String consola = linea.substring((linea.indexOf("Consola") + 7), (linea.indexOf("Puntuación"))).trim();
                //Puntuación
                String puntuacion = linea.substring(linea.indexOf("Puntuación") + 12, linea.length()).trim();

                //Asignación de los atributos de cada juego:
                //ID
                juego.setId(id);
                //Título
                juego.setTitulo(titulo);
                //Año
                juego.setAnio(Integer.parseInt(anio));
                //Compañía
                juego.setCompania(compania);
                //Consola
                juego.setConsola(consola);
                //Puntuación
                juego.setPuntuacion(Double.parseDouble(puntuacion));

                //Añadir el juego al arraylist
                juegos.add(juego);

                System.out.println("\nJuego " + juego.getId() + ":");
                System.out.println("Título: " + juego.getTitulo());
                System.out.println("Año: " + juego.getAnio());
                System.out.println("Compañía: " + juego.getCompania());
                System.out.println("Consola: " + juego.getConsola());
                System.out.println("Puntuacion: " + juego.getPuntuacion());
            }
            br.close();
            
        } catch (IOException e) {
            System.out.println("Error al leer el fichero: " + e.getMessage());
        }
    }

    /**
     * Método para crear un XML a partit de los juegos leídos.
     * @since 1.1
     * @param xml File fichero XML a crear
     */
    public static void crearXML(File xml){
        try {

            //Declaración de variables relacionadas con el XML
            DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();
            DocumentBuilder constructor = fabrica.newDocumentBuilder();
            DOMImplementation implementacion = constructor.getDOMImplementation();

            //Creación del documento XML
            Document documento = implementacion.createDocument(null, xml.getName(), null);
            documento.setXmlVersion("1.0");

            //Creación de los elementos principales
            Element videojuegos = documento.createElement("Videojuegos");

            for (Juego juego : juegos) {

                Element videojuego = documento.createElement("Videojuego");
                videojuego.setAttribute("id", juego.getId());

                //Elementos de cada videojuego
                //Titulo
                Element titulo = documento.createElement("Titulo");
                Text tetxTitulo = documento.createTextNode(juego.getTitulo());
                titulo.appendChild(tetxTitulo);
                videojuego.appendChild(titulo);
                //Año
                Element anio = documento.createElement("Año");
                Text textAnio = documento.createTextNode(juego.getAnio() + "");
                anio.appendChild(textAnio);
                videojuego.appendChild(anio);
                //Compañía
                Element compania = documento.createElement("Compañía");
                Text textCompania = documento.createTextNode(juego.getCompania());
                compania.appendChild(textCompania);
                videojuego.appendChild(compania);
                //Consola
                Element consola = documento.createElement("Consola");
                Text textConsola = documento.createTextNode(juego.getConsola());
                consola.appendChild(textConsola);
                videojuego.appendChild(consola);
                //Puntuación
                Element puntuacion = documento.createElement("Puntuación");
                Text textPuntuacion = documento.createTextNode(juego.getPuntuacion() + "");
                puntuacion.appendChild(textPuntuacion);
                videojuego.appendChild(puntuacion);

                videojuegos.appendChild(videojuego);
                documento.getDocumentElement().appendChild(videojuegos);

                //Configuracion y creacion del fichero XML
                Source source = new DOMSource(documento);
                Result result = new StreamResult(xml);

                Transformer transformer = TransformerFactory.newInstance().newTransformer();
                transformer.transform(source, result);
            }

        } catch (ParserConfigurationException e) {
            System.out.println("Error al crear el XML: " + e.getMessage());
        } catch(TransformerConfigurationException e){
            System.out.println("Error de configuración del transformador: " + e.getMessage());
        } catch (TransformerException e){
            System.out.println("Error al transformar el documento XML: " + e.getMessage());
        }
    }
}