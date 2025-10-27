public class Juego {
    /**
     * @author Iván López Benítez
     * @version 1.1
     * Variables:
     * id: Identificador único del juego (entero).
     * titulo: Título del juego (cadena de texto).
     * anio: Año de lanzamiento del juego (entero).
     * compania: Compañía desarrolladora del juego (cadena de texto).
     * puntuacion: Puntuación del juego (decimal: 8.0).
     */

    private String id;
    private String titulo;
    private int anio;
    private String compania;
    private String consola;
    private double puntuacion;
    private String recomendacion;

    /**
     * Constructor principal de la clase Juego.
     * @since 1.0
     * @param id int
     * @param titulo String
     * @param anio int
     * @param compania String
     * @param puntuacion double
     * @param recomendacion String
     */
    public Juego(String id, String titulo, int anio, String compania, String consola, double puntuacion, String recomendacion) {
        this.id = id;
        this.titulo = titulo;
        this.anio = anio;
        this.compania = compania;
        this.consola = consola;
        this.puntuacion = puntuacion;
        this.recomendacion=recomendacion;
    }

    /**
     * Constructor vacío de la clase Juego.
     * @since 1.0
     */
    public Juego() {
        this.id = "0";
        this.titulo = "";
        this.anio = 0;
        this.compania = "";
        this.consola = "";
        this.puntuacion = 0.0;
        this.recomendacion="S";
    }

    //Metodos
    

    // Getters y Setters
    /**
     * @return int devuelve el id
     * @since 1.0
     */
    public String getId() {
        return id;
    }

    /**
     * @param id el id a establecer
     * @since 1.0
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return String devuelve el titulo
     * @since 1.0
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo el titulo a establecer
     * @since 1.0
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return int devuelve el anio
     * @since 1.0
     */
    public int getAnio() {
        return anio;
    }

    /**
     * @param anio el año a establecer
     * @since 1.0
     */
    public void setAnio(int anio) {
        this.anio = anio;
    }

    /**
     * @return String devuelve la compañíaç
     * @since 1.0
     */
    public String getCompania() {
        return compania;
    }

    /**
     * @param compania la compañía a establecer
     * @since 1.0
     */
    public void setCompania(String compania) {
        this.compania = compania;
    }

    /**
     * @return String devuelve la consola
     * @since 1.0
     */
    public String getConsola() {
        return consola;
    }
    /**
     * @param consola la consola a establecer
     * @since 1.0
     */
    public void setConsola(String consola) {
        this.consola = consola;
    }

    /**
     * @return double devuelve la puntuacion
     * @since 1.0
     */
    public double getPuntuacion() {
        return puntuacion;
    }

    /**
     * @param puntuacion la puntuacion a establecer
     * @since 1.0
     */
    public void setPuntuacion(double puntuacion) {
        this.puntuacion = puntuacion;
    }

    /**
     * @return double devuelve la recomendacion
     * @since 1.1
     */
    public String getRecomendacion(){
        return recomendacion;
    }

    /**
     * @param recomendacion la recomendacion a establecer
     * @since 1.1
     */
    public void setRecomendacion(String recomendacion){
        this.recomendacion=recomendacion;
    }

}
