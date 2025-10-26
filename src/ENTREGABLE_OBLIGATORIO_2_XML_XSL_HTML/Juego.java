public class Juego {
    /**
     * Variables:
     * id: Identificador único del juego (entero).
     * titulo: Título del juego (cadena de texto).
     * anio: Año de lanzamiento del juego (entero).
     * compania: Compañía desarrolladora del juego (cadena de texto).
     * puntuacion: Puntuación del juego (decimal: 8.0).
     */

    private int id;
    private String titulo;
    private int anio;
    private String compania;
    private double puntuacion;

    // Constructor
    public Juego(int id, String titulo, int anio, String compania, double puntuacion) {
        this.id = id;
        this.titulo = titulo;
        this.anio = anio;
        this.compania = compania;
        this.puntuacion = puntuacion;
    }

    // Getters y Setters
    /**
     * @return int devuelve el id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id el id a establecer
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return String devuelve el titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo el titulo a establecer
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return int devuelve el anio
     */
    public int getAnio() {
        return anio;
    }

    /**
     * @param anio el año a establecer
     */
    public void setAnio(int anio) {
        this.anio = anio;
    }

    /**
     * @return String devuelve la compañía
     */
    public String getCompania() {
        return compania;
    }

    /**
     * @param compania la compañía a establecer
     */
    public void setCompania(String compania) {
        this.compania = compania;
    }

    /**
     * @return double devuelve la puntuacion
     */
    public double getPuntuacion() {
        return puntuacion;
    }

    /**
     * @param puntuacion la puntuacion a establecer
     */
    public void setPuntuacion(double puntuacion) {
        this.puntuacion = puntuacion;
    }

}
