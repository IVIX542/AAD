package actividad2_1;

// Librerías para manejar ficheros
import java.io.File;
import java.util.Scanner;

//Librerías para manejar XML
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.TransformerException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class ManejoFicherosXML  {

    public static void main(String[] args) throws ParserConfigurationException, TransformerException {
        System.out.print("Introduce el directorio donde se creará el fichero XML (por ejemplo, C:\\Users\\usuario\\OneDrive\\OneNote\\2ºDAM\\AAD): ");

        String directorio = new Scanner(System.in).nextLine();

        crearXML(directorio);

        System.out.println("Fichero XML creado en: " + directorio + File.separator + "Biblioteca.xml");

    }

    public static void crearXML(String directorio) throws ParserConfigurationException, TransformerException {
        
        /**
         * Preparación de las herramientas para crear el documento XML:
         * Creamos una instancia de DocumentBuilderFactory,
         * lo que nos permitirá crear el Document builder.
         * Con el DocumentBuilder creamos el DOMImplementation
        */

        //Crear enstancia con DocumentBuilderFactory
        DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();
        //Crear el documentBuilder
        DocumentBuilder constructor = fabrica.newDocumentBuilder();
        //Crear el DOMImplementation
        DOMImplementation implementacion = constructor.getDOMImplementation();

        /**
         * Creación del Documento y elemento raíz:
         * Con el DOMImplementation creamos el documento XML,
         * estableciendo el nombre del elemento raíz.
         * También podemos establecer la versión del documento XML.
        */

        //Crear el documento, con un elemento raíz
        Document documento = implementacion.createDocument(null, "Biblioteca", null);
        //Establecemos la versión del documento XML
        documento.setXmlVersion("1.0");

        /**
         * Construcción de la jerarquía de elementos:
         * En este punto, se añaden las "ramas" a nuestro árbol XML.
         * Se crean los elementos y se añaden al documento.
        */

        //LIBRO1
        //Creamos los elementos de las etiquetas (sólo existen en memoria)
        Element libros = documento.createElement("Libros");
        Element libro1 = documento.createElement("Libro");
        libro1.setAttribute("id", "1");

        //Creamos los elementos de un libro
        Element titulo1 = documento.createElement("Titulo");
        Text textTitulo1 = documento.createTextNode("Cien años de soledad"); //Crea el texto
        titulo1.appendChild(textTitulo1); //Añade el texto al elemento
        libro1.appendChild(titulo1); //Añade el elemento al libro

        Element autor1 = documento.createElement("Autor");
        Text textAutor1 = documento.createTextNode("Gabriel García Márquez"); //Crea el texto
        autor1.appendChild(textAutor1); //Añade el texto al elemento
        libro1.appendChild(autor1); //Añade el elemento al libro
        
        Element anioPublicacion1 = documento.createElement("AñoPublicacion");
        Text textAnioPublicacion1 = documento.createTextNode("1967"); //Crea el texto
        anioPublicacion1.appendChild(textAnioPublicacion1); //Añade el texto al elemento
        libro1.appendChild(anioPublicacion1); //Añade el elemento al libro
        
        Element generoLiterario1 = documento.createElement("GeneroLiterario");
        Text textGeneroLiterario1 = documento.createTextNode("Realismo mágico"); //Crea el texto
        generoLiterario1.appendChild(textGeneroLiterario1); //Añade el texto al elemento
        libro1.appendChild(generoLiterario1); //Añade el elemento al libro

        //Añadimos el libro a la lista de libros
        libros.appendChild(libro1);

        //LIBRO2
        //Creamos los elementos de las etiquetas (sólo existen en memoria)
        Element libro2 = documento.createElement("Libro");
        libro2.setAttribute("id", "2");

        //Creamos los elementos de un libro
        Element titulo2 = documento.createElement("Titulo");
        Text textTitulo2 = documento.createTextNode("Cien años de soledad"); //Crea el texto
        titulo2.appendChild(textTitulo2); //Añade el texto al elemento
        libro2.appendChild(titulo2); //Añade el elemento al libro

        Element autor2 = documento.createElement("Autor");
        Text textAutor2 = documento.createTextNode("Gabriel García Márquez"); //Crea el texto
        autor2.appendChild(textAutor2); //Añade el texto al elemento
        libro2.appendChild(autor2); //Añade el elemento al libro
        
        Element anioPublicacion2 = documento.createElement("AñoPublicacion");
        Text textAnioPublicacion2 = documento.createTextNode("1967"); //Crea el texto
        anioPublicacion2.appendChild(textAnioPublicacion2); //Añade el texto al elemento
        libro2.appendChild(anioPublicacion2); //Añade el elemento al libro
        
        Element generoLiterario2 = documento.createElement("GeneroLiterario");
        Text textGeneroLiterario2 = documento.createTextNode("Realismo mágico"); //Crea el texto
        generoLiterario2.appendChild(textGeneroLiterario2); //Añade el texto al elemento
        libro2.appendChild(generoLiterario2); //Añade el elemento al libro
        //Añadimos el libro a la lista de libros
        libros.appendChild(libro2);

        //LIBRO3
        //Creamos los elementos de las etiquetas (sólo existen en memoria)
        Element libro3 = documento.createElement("Libro");
        libro3.setAttribute("id", "3");

        //Creamos los elementos de un libro
        Element titulo3 = documento.createElement("Titulo");
        Text textTitulo3 = documento.createTextNode("El ingenioso hidalgo don Quijote de la Mancha"); //Crea el texto
        titulo3.appendChild(textTitulo3); //Añade el texto al elemento
        libro3.appendChild(titulo3); //Añade el elemento al libro

        Element autor3 = documento.createElement("Autor");
        Text textAutor3 = documento.createTextNode("Miguel de Cervantes"); //Crea el texto
        autor3.appendChild(textAutor3); //Añade el texto al elemento
        libro3.appendChild(autor3); //Añade el elemento al libro
        
        Element anioPublicacion3 = documento.createElement("AñoPublicacion");
        Text textAnioPublicacion3 = documento.createTextNode("1605 (primera parte) y 1615 (segunda parte)"); //Crea el texto
        anioPublicacion3.appendChild(textAnioPublicacion3); //Añade el texto al elemento
        libro3.appendChild(anioPublicacion3); //Añade el elemento al libro
        
        Element generoLiterario3 = documento.createElement("GeneroLiterario");
        Text textGeneroLiterario3 = documento.createTextNode("Novela de caballerías (parodia), sátira"); //Crea el texto
        generoLiterario3.appendChild(textGeneroLiterario3); //Añade el texto al elemento
        libro3.appendChild(generoLiterario3); //Añade el elemento al libro

        //Añadimos el libro a la lista de libros
        libros.appendChild(libro3);

        //LIBRO4
        //Creamos los elementos de las etiquetas (sólo existen en memoria)
        Element libro4 = documento.createElement("Libro");
        libro4.setAttribute("id", "4");

        //Creamos los elementos de un libro
        Element titulo4 = documento.createElement("Titulo");
        Text textTitulo4 = documento.createTextNode("El ingenioso hidalgo don Quijote de la Mancha"); //Crea el texto
        titulo4.appendChild(textTitulo4); //Añade el texto al elemento
        libro4.appendChild(titulo4); //Añade el elemento al libro

        Element autor4 = documento.createElement("Autor");
        Text textAutor4 = documento.createTextNode("Miguel de Cervantes"); //Crea el texto
        autor4.appendChild(textAutor4); //Añade el texto al elemento
        libro4.appendChild(autor4); //Añade el elemento al libro
        
        Element anioPublicacion4 = documento.createElement("AñoPublicacion");
        Text textAnioPublicacion4 = documento.createTextNode("1605 (primera parte) y 1615 (segunda parte)"); //Crea el texto
        anioPublicacion4.appendChild(textAnioPublicacion4); //Añade el texto al elemento
        libro4.appendChild(anioPublicacion4); //Añade el elemento al libro
        
        Element generoLiterario4 = documento.createElement("GeneroLiterario");
        Text textGeneroLiterario4 = documento.createTextNode("Novela de caballerías (parodia), sátira"); //Crea el texto
        generoLiterario4.appendChild(textGeneroLiterario4); //Añade el texto al elemento
        libro4.appendChild(generoLiterario4); //Añade el elemento al libro
        //Añadimos el libro a la lista de libros
        libros.appendChild(libro4);

        /**
         * Añadir otros elementos (no se añaden a la lista de libros):
         * Podemos crear otros elementos que no formen parte
         * de la lista de libros, como por ejemplo una lista de cómics.
         * Estos elementos se crean de la misma manera,
         * pero no se añaden al elemento raíz "Libros".
         */

        //COMIC1 (no se añade a la lista de libros)
        Element comics = documento.createElement("Comics");
        Element comic1 = documento.createElement("Comic");
        comic1.setAttribute("id", "1");

        //Creamos los elementos de un cómic

        Element tituloComic1 = documento.createElement("Titulo");
        Text textTituloComic1 = documento.createTextNode("Maus"); //Crea el texto

        tituloComic1.appendChild(textTituloComic1); //Añade el texto al elemento
        comic1.appendChild(tituloComic1); //Añade el elemento al cómic

        Element guionistaComic1 = documento.createElement("Guionista");
        Text textGuionistaComic1 = documento.createTextNode("Art Spiegelman"); //Crea el texto

        guionistaComic1.appendChild(textGuionistaComic1); //Añade el texto al elemento
        comic1.appendChild(guionistaComic1); //Añade el elemento al cómic

        Element dibujanteComic1 = documento.createElement("Dibujante");
        Text textDibujanteComic1 = documento.createTextNode("Art Spiegelman"); //Crea el texto

        dibujanteComic1.appendChild(textDibujanteComic1); //Añade el texto al elemento
        comic1.appendChild(dibujanteComic1); //Añade el elemento al cómic

        Element editorialComic1 = documento.createElement("Editorial");
        Text textEditorialComic1 = documento.createTextNode("Pantheon Books"); //Crea el texto

        editorialComic1.appendChild(textEditorialComic1); //Añade el texto al elemento
        comic1.appendChild(editorialComic1); //Añade el elemento al cómic

        comics.appendChild(comic1); //Añade el cómic a la lista de cómics



        //Añadimos las listas al documento
        documento.getDocumentElement().appendChild(libros);
        documento.getDocumentElement().appendChild(comics);

        /**
         * Paso final: Guardar el documento XML en un archivo
         * Utilizamos las clases Transformer, Source y Result
         * para escribir el documento en un archivo.
         * El Transformer se encarga de transformar el origen (DOM)
         * en el resultado (archivo).
         * También podemos establecer propiedades de salida,
         * como la indentación para mejorar la legibilidad del XML.
        */

        //Asociamos el documento a un origen DOM
        Source origen = new DOMSource(documento);

        //Creamos el resultado, es decir, el fichero donde se guardará
        Result resultado = new StreamResult(new File(directorio + File.separator + "Biblioteca.xml"));

    }

}