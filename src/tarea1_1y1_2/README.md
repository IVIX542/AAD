Proyecto hecho por Iván López Benítez, el 01/10/2025 a las 20:24.
Proyecto dedicado al manejo de ficheros en java.

Métodos importante de la clase File:
    String[] list()
    File[] listFiles()
    String getName()
    String getPatch()
    String getAbsolutePath()
    boolean canWrite()
    boolean canRead()
    boolean isFile()
    boolean isDirectory()
    long length()
    boolean mkdir()
    boolean renameTo(File NombreNuevo)
    boolean delete()
    boolean createNewFile()
    String getparent()

Métodos de la clase FileReader:
    int read()
    int read(char[] buf)
    int read(char[] buf, int desplazamiento, int n)

Métodos de la clase FileWriter:
    void write(int caracter)
    void write(char[] buf)
    void write(char[] buf, int desplazamiento, int n)
    void write(String cadena)
    void append(char caracter)