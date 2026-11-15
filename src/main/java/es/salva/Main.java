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
            PeliculaDao peliculaDao = new PeliculaDao();
            peliculaDao.crearTabla(conexion);
            System.out.println("Tabla peliculas creada");
            List<Pelicula> peliculasPrueba = List.of(
                    new Pelicula("El Padrino", "Francis Ford Coppola", "Drama", 1972, 175,
                            "El patriarca de una familia mafiosa transfiere el control de su imperio a su hijo.",
                            "Blu-ray", "Edición coleccionista", null),
                    new Pelicula("La lista de Schindler", "Steven Spielberg", "Drama histórico", 1993, 195,
                            null, "DVD", "Nuevo", null),
                    new Pelicula("Pulp Fiction", "Quentin Tarantino", "Crimen", 1994, 154,
                            null, "Blu-ray", "Usado", null),
                    new Pelicula("El caballero oscuro", "Christopher Nolan", "Acción", 2008, 152,
                            null, "4K", "Nuevo", null),
                    new Pelicula("Origen", "Christopher Nolan", "Ciencia ficción", 2010, 148,
                            null, "Blu-ray", "Nuevo", null));
            for (int i = 0; i < peliculasPrueba.size(); i++) {
                peliculaDao.insertarPelicula(conexion, peliculasPrueba.get(i));
            }
            System.out.println("Peliculas de prueba insertadas: " + peliculasPrueba.size());

        } catch (SQLException e) {
            System.out.println("Error al conectar: " + e.getMessage());
        }


    }


}
