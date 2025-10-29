/**
 * Clase Main que contiene el método main para la ejecución del programa.
 * @author Iván López Benítez
 * @version 1.4
 * @since 1.0
 * Fecha: 26/10/2025
 */

import java.util.Scanner;
//Manejo de ficheros
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Comparator;

//XML
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.Source;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;

//DOM
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

//SAX
import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.SAXParser;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Attributes;

//Excepciones
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;

public class Main {
    
    //ArrayList global, para usarlo en todos los métodos de la clase Main
    public static ArrayList<Juego> juegos = new ArrayList<>();
    
    /**
     * Método principal para la ejecución del programa.
     * @since 1.0
     * @param args
     */
    public static void main(String[] args) {
        //Declaraciones
        boolean continuar = true;
        Scanner sc = new Scanner(System.in);
        File txt = new File("Catalogo_inicial_sin_formato.txt");
        File xml = new File("Catalogo_juegos.xml");
        File xmlRecomendados = new File("Catalogo_juegos_recomendados.xml");
        File xsl = new File("Catalogo_plantilla.xsl");
        File html = new File("Catalogo_recomendado_top.html");

        //Llamadas a métodos
        extraerDatos(txt);
        crearXML(xml);
        leerXML(xml);
        
        //Cambiar la recomendación de un juego mediante un bucle
        System.out.println("\nDeseas cambiar la recomendación de algún juego? (S/N): ");
        String respuestaInicial = sc.nextLine();
        if(respuestaInicial.equalsIgnoreCase("N")){
            continuar = false;
        }else if(!respuestaInicial.equalsIgnoreCase("S")){
            System.out.println("Respuesta no válida. Saliendo del programa.");
            continuar = false;
        } else{
            continuar=true;
            
            while(continuar){
                System.out.print("\nIntroduce el ID del juego que no recomiendes: ");
                String idJuego = sc.nextLine();
                cambiarRecomendacion(idJuego);
                crearXML(xml); //Volver a crear el XML con la recomendación modificada
                System.out.print("\nDeseas continuar cambiando recomendaciones? (S/N): ");
                String respuesta = sc.nextLine();
                switch(respuesta.toUpperCase()){
                    case "S":
                    continuar = true;
                    break;
                    case "N":
                    continuar = false;
                    break;
                    default:
                    System.out.println("Respuesta no válida. Saliendo del programa.");
                    continuar = false;
                    break;
                }
            }
        }
        
        crearRecomendadosXML(xmlRecomendados);
        //Mostrar los juegos no recomendados
        mostrarNoRecomendados();

        //Crear el HTML a partir del XML y la hoja de estilo XSL
        crearHTML(xmlRecomendados, xsl, html);

        sc.close();
    }

    /**
     * Método para extraer los datos del fichero de texto y mostrarlos por consola.
     * @since 1.0
     * @param txt File fichero de texto a leer
     * @exception IOException Si hay un error al leer el fichero.
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
                String consola = linea.substring((linea.indexOf("Consola") + 9), (linea.indexOf("Puntuación"))).trim();
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
     * @exception ParserConfigurationException Si hay un error en la configuración del parser.
     * @exception TransformerConfigurationException Si hay un error en la configuración del transformador.
     * @exception TransformerException Si hay un error al transformar el documento XML.
     */
    public static void crearXML(File xml){
        try {

            //Declaración de variables relacionadas con el XML
            DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();
            DocumentBuilder constructor = fabrica.newDocumentBuilder();
            DOMImplementation implementacion = constructor.getDOMImplementation();

            //Creación del documento XML
            Document documento = implementacion.createDocument(null, "videojuegos", null);
            documento.setXmlVersion("1.0");


            //Bucle SOLO para cada videojuego en el arraylist
            for (Juego juego : juegos) {

                Element videojuego = documento.createElement("videojuego");
                videojuego.setAttribute("id", juego.getId());

                //Elementos de cada videojuego
                //Titulo
                Element titulo = documento.createElement("titulo");
                Text tetxTitulo = documento.createTextNode(juego.getTitulo());
                titulo.appendChild(tetxTitulo);
                videojuego.appendChild(titulo);
                //Año
                Element anio = documento.createElement("anio");
                Text textAnio = documento.createTextNode(juego.getAnio() + "");
                anio.appendChild(textAnio);
                videojuego.appendChild(anio);
                //Compañía
                Element compania = documento.createElement("compania");
                Text textCompania = documento.createTextNode(juego.getCompania());
                compania.appendChild(textCompania);
                videojuego.appendChild(compania);
                //Consola
                Element consola = documento.createElement("consola");
                Text textConsola = documento.createTextNode(juego.getConsola());
                consola.appendChild(textConsola);
                videojuego.appendChild(consola);
                //Puntuación
                Element puntuacion = documento.createElement("puntuacion");
                Text textPuntuacion = documento.createTextNode(juego.getPuntuacion() + "");
                puntuacion.appendChild(textPuntuacion);
                videojuego.appendChild(puntuacion);
                //Recomendación (siempre "S", hasta que el usuario lo cambie manualmente)
                Element recomendacion = documento.createElement("recomendacion");
                Text textRecomendacion = documento.createTextNode(juego.getRecomendacion());
                recomendacion.appendChild(textRecomendacion);
                videojuego.appendChild(recomendacion);

                //Añadir cada videojuego al elemento principal
                documento.getDocumentElement().appendChild(videojuego);
            }

            //Configuracion y creacion del fichero XML
            Source source = new DOMSource(documento);
            Result result = new StreamResult(xml);

            //Transformador
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            
            //Formato con sangrías
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

            //Transformación y creación del XML
            transformer.transform(source, result);

        }
        //Excepciones
        catch (ParserConfigurationException e) {
            System.out.println("Error al crear el XML: " + e.getMessage());
        } catch(TransformerConfigurationException e){
            System.out.println("Error de configuración del transformador: " + e.getMessage());
        } catch (TransformerException e){
            System.out.println("Error al transformar el documento XML: " + e.getMessage());
        }
    }

    /**
     * Método para leer el XML con SAX.
     * @since 1.2
     * @param xml File fichero XML a leer
     */
    public static void leerXML(File xml){
        try {
            SAXParserFactory fabrica = SAXParserFactory.newInstance();
            SAXParser parser = fabrica.newSAXParser();

            DefaultHandler manejador = new DefaultHandler(){
                boolean bTitulo = false;
                boolean bAnio = false;
                boolean bCompania = false;
                boolean bConsola = false;
                boolean bPuntuacion = false;
                boolean bRecomendacion = false;

                /**
                 * Método que se ejecuta al encontrar el inicio de un elemento.
                 * @param uri String
                 * @param localName String
                 * @param qName String
                 * @param attributes Attributes
                 * @throws SAXException Si hay un error al procesar el elemento.
                 * @since 1.2
                 */
                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes)throws SAXException {

                    if (qName.equalsIgnoreCase("videojuego")) {
                        String id = attributes.getValue("id");
                        System.out.println("\nID: " + id);
                    } else if (qName.equalsIgnoreCase("titulo")) {
                        bTitulo = true;
                    } else if (qName.equalsIgnoreCase("anio")) {
                        bAnio = true;
                    } else if (qName.equalsIgnoreCase("compania")) {
                        bCompania = true;
                    } else if (qName.equalsIgnoreCase("consola")) {
                        bConsola = true;
                    } else if (qName.equalsIgnoreCase("puntuacion")) {
                        bPuntuacion = true;
                    } else if (qName.equalsIgnoreCase("recomendacion")) {
                        bRecomendacion = true;
                    }
                }

                /**
                 * Método que se ejecuta al encontrar el contenido de un elemento.
                 * @param ch char[]
                 * @param start int
                 * @param length int
                 * @throws SAXException Si hay un error al procesar el contenido.
                 * @since 1.2
                 */
                @Override
                public void characters(char[] ch, int start, int length)throws SAXException {
                    if (bTitulo) {
                        System.out.println("Título: " + new String(ch, start, length));
                        bTitulo = false;
                    } else if (bAnio) {
                        System.out.println("Año: " + new String(ch, start, length));
                        bAnio = false;
                    } else if (bCompania) {
                        System.out.println("Compañía: " + new String(ch, start, length));
                        bCompania = false;
                    } else if (bConsola) {
                        System.out.println("Consola: " + new String(ch, start, length));
                        bConsola = false;
                    } else if (bPuntuacion) {
                        System.out.println("Puntuación: " + new String(ch, start, length));
                        bPuntuacion = false;
                    } else if (bRecomendacion) {
                        System.out.println("Recomendación: " + new String(ch, start, length));
                        bRecomendacion = false;
                    }
                }

            };
            parser.parse(xml, manejador);
        } catch (IOException | ParserConfigurationException | SAXException e) {
            System.out.println("Error al leer el XML: " + e.getMessage());
        }
    }

    /**
     * Método para cambiar la recomendación de un juego de "S" a "N".
     * @since 1.3
     * @param idJuego String ID del juego a modificar
     */

    public static void cambiarRecomendacion(String idJuego){
        for (Juego juego : juegos) {
            if (juego.getId().equals(idJuego)) {
                juego.setRecomendacion("N");
                System.out.println("Recomendación del juego \"" + juego.getTitulo() + "\" cambiada a 'N'.");
                return;
            }
        }
        System.out.println("No se encontró un juego con el ID " + idJuego + ".");
    }

    /**
     * Método para mostrar los juegos no recomendados
    */
    public static void mostrarNoRecomendados(){
        System.out.println("\nJuegos no recomendados:");
        for (Juego juego : juegos) {
            if (juego.getRecomendacion().equals("N")) {
                System.out.println("- " + juego.getTitulo() + " (ID: " + juego.getId() + ")");
            }
        }
    }

    /**
     * Método para crear un XML con los juegos recomendados
     * @since 1.4
     */
    public static void crearRecomendadosXML(File xmlRecomendados){
        //Crear un nuevo ArrayList con los juegos recomendados
        ArrayList<Juego> juegosRecomendados = new ArrayList<>();
        for (Juego juego : juegos) {
            if (juego.getRecomendacion().equals("S")) {
                juegosRecomendados.add(juego);
            }
        }

        //Ordenar los juegos recomendados de mayor a menor puntuación
        juegosRecomendados.sort(Comparator.comparingDouble(Juego::getPuntuacion).reversed());

        try {

            //Declaración de variables relacionadas con el XML
            DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();
            DocumentBuilder constructor = fabrica.newDocumentBuilder();
            DOMImplementation implementacion = constructor.getDOMImplementation();

            //Creación del documento XML
            Document documento = implementacion.createDocument(null, "videojuegos", null);
            documento.setXmlVersion("1.0");


            //Bucle SOLO para cada videojuego en el arraylist
            for (Juego juego : juegosRecomendados) {

                Element videojuego = documento.createElement("videojuego");
                videojuego.setAttribute("id", juego.getId());

                //Elementos de cada videojuego
                //Titulo
                Element titulo = documento.createElement("titulo");
                Text tetxTitulo = documento.createTextNode(juego.getTitulo());
                titulo.appendChild(tetxTitulo);
                videojuego.appendChild(titulo);
                //Año
                Element anio = documento.createElement("anio");
                Text textAnio = documento.createTextNode(juego.getAnio() + "");
                anio.appendChild(textAnio);
                videojuego.appendChild(anio);
                //Compañía
                Element compania = documento.createElement("compania");
                Text textCompania = documento.createTextNode(juego.getCompania());
                compania.appendChild(textCompania);
                videojuego.appendChild(compania);
                //Consola
                Element consola = documento.createElement("consola");
                Text textConsola = documento.createTextNode(juego.getConsola());
                consola.appendChild(textConsola);
                videojuego.appendChild(consola);
                //Puntuación
                Element puntuacion = documento.createElement("puntuacion");
                Text textPuntuacion = documento.createTextNode(juego.getPuntuacion() + "");
                puntuacion.appendChild(textPuntuacion);
                videojuego.appendChild(puntuacion);
                //Recomendación (siempre "S", hasta que el usuario lo cambie manualmente)
                Element recomendacion = documento.createElement("recomendacion");
                Text textRecomendacion = documento.createTextNode(juego.getRecomendacion());
                recomendacion.appendChild(textRecomendacion);
                videojuego.appendChild(recomendacion);

                //Añadir cada videojuego al elemento principal
                documento.getDocumentElement().appendChild(videojuego);
            }

            //Configuracion y creacion del fichero XML
            Source source = new DOMSource(documento);
            Result result = new StreamResult(xmlRecomendados);

            //Transformador
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            
            //Formato con sangrías
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

            //Transformación y creación del XML
            transformer.transform(source, result);

        }
        //Excepciones
        catch (ParserConfigurationException e) {
            System.out.println("Error al crear el XML: " + e.getMessage());
        } catch(TransformerConfigurationException e){
            System.out.println("Error de configuración del transformador: " + e.getMessage());
        } catch (TransformerException e){
            System.out.println("Error al transformar el documento XML: " + e.getMessage());
        }
    }

    /**
     * Método para crear un HTML a partir de un XML y una hoja de estilo XSL.
     * @since 1.3
     * @param xml File fichero XML de entrada
     * @param xsl File fichero XSL de entrada
     * @param html File fichero HTML de salida
     * @exception ParserConfigurationException Si hay un error en la configuración del parser.
     * @exception TransformerException Si hay un error al transformar el documento XML.
     */
    public static void crearHTML(File xml, File xsl, File html){
        try {
            
            //Streams
            FileOutputStream fos = new FileOutputStream(html);
            Source estilos = new StreamSource(xsl);
            Source datos = new StreamSource(xml);

            //Resultado de la transformación
            Result resultado = new StreamResult(fos);

            //Transformador
            Transformer transformer = TransformerFactory.newInstance().newTransformer(estilos);
            transformer.transform(datos, resultado);

            //Cerramos el Stream
            fos.close();
            
        } catch (IOException | TransformerFactoryConfigurationError | TransformerException e) {
            System.out.println("Error al crear el HTML: " + e.getMessage());
        }
        
    }
}