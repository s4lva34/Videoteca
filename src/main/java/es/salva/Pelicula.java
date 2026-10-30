package es.salva;

import java.time.LocalDate;

public class Pelicula {
    private int id;
    private String titulo;
    private String director;
    private String genero;
    private int anio;
    private int duracion;
    private String sinopsis;
    private String formato;
    private String estado;
    private LocalDate fechaAdquisicion; // uso localdate para fecha mejor que int, pienso que es mas correcto

    /**
     * peliculas que aun no estan en BD, crea peliculas que aun no estan en la BD, el id lo asigna la BD
     * @param titulo titulo de la pelicula, no puede estar vacio
     * @param director nombre del director de la pelicula
     * @param genero genero de la pelicula
     * @param anio año en que se termino de hacer la pelicula y se presento
     * @param duracion duracion de la pelicula en minutos
     * @param sinopsis explicacion resumida de la pelicula
     * @param formato DVD/Blu-ray/4K
     * @param estado si la pelicula se compra nueva, usada, etc.
     * @param fechaAdquisicion fecha en la que se compro y podemos ponerla null si se desconoce
     */
    public Pelicula(String titulo, String director, String genero, int anio, int duracion, String sinopsis, String formato, String estado, LocalDate fechaAdquisicion) {
        this.titulo = titulo;
        this.director = director;
        this.genero = genero;
        this.anio = anio;
        this.duracion = duracion;
        this.sinopsis = sinopsis;
        this.formato = formato;
        this.estado = estado;
        this.fechaAdquisicion = fechaAdquisicion;
    }

    /**
     * peliculas que vienen en la BD y traen asignadas el id
     * @param id identificador unico que asigna la base de datos
     * @param titulo titulo de la pelicula, no puede estar vacio
     * @param director nombre del director de la pelicula
     * @param genero genero de la pelicula
     * @param anio año en que se termino de hacer la pelicula y se presento
     * @param duracion duracion de la pelicula en minutos
     * @param sinopsis explicacion resumida de la pelicula
     * @param formato DVD/Blu-ray/4K
     * @param estado si la pelicula se compra nueva, usada, etc.
     * @param fechaAdquisicion fecha en la que se compro y podemos ponerla null si se desconoce
     */
    public Pelicula(int id, String titulo, String director, String genero, int anio, int duracion, String sinopsis, String formato, String estado, LocalDate fechaAdquisicion) {
        this.id = id;
        this.titulo = titulo;
        this.director = director;
        this.genero = genero;
        this.anio = anio;
        this.duracion = duracion;
        this.sinopsis = sinopsis;
        this.formato = formato;
        this.estado = estado;
        this.fechaAdquisicion = fechaAdquisicion;
    }
}
