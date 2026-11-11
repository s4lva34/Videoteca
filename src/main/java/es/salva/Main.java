package es.salva;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Pelicula padrino = new Pelicula("El Padrino", "Francis Ford Coppola", "Drama",
                1972, 175,
                "El patriarca de una familia mafiosa transfiere el control de su imperio a su hijo.",
                "Blu-ray", "Edición coleccionista",
                LocalDate.of(2026, 10, 30));
        System.out.println(padrino.getTitulo());
        System.out.println("Duración: " + padrino.getDuracion() + " min.");

        XmlPeliculas xmlPeliculas = new XmlPeliculas();
        List<Pelicula> peliculas = xmlPeliculas.leerPeliculas("peliculas.xml");
        for (int i = 0; i < peliculas.size(); i++) {
            Pelicula pelicula = peliculas.get(i);
            System.out.println(pelicula.getTitulo() + ", " + pelicula.getDuracion() + " min.");
        }
        ConexionBD conexionBD = new ConexionBD();
        try {
            Connection conexion = conexionBD.abrir();
            System.out.println("Conexión realizada correctamente");
        } catch (SQLException e) {
            System.out.println("Error al conectar: " + e.getMessage());
        }


    }


}
