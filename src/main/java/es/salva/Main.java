package es.salva;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

/**
 * Programa de la videoteca: crea la tabla, inserta películas de prueba,
 * genera el XML e importa y exporta películas entre el XML y la base de datos.
 */
public class Main {

    /**
     * Prueba todos los pasos de la videoteca en orden.
     *
     * @param args no se usan
     */
    public static void main(String[] args) {
        Pelicula padrino = new Pelicula("El Padrino", "Francis Ford Coppola", "Drama",
                1972, 175,
                "El patriarca de una familia mafiosa transfiere el control de su imperio a su hijo.",
                "Blu-ray", "Edición coleccionista",
                LocalDate.of(2026, 10, 30));
        System.out.println(padrino.getTitulo());
        System.out.println("Duración: " + padrino.getDuracion() + " min.");

        XmlPeliculas xmlPeliculas = new XmlPeliculas();
        List<Pelicula> peliculasXml = List.of(
                new Pelicula("El laberinto del fauno", "Guillermo del Toro", "Fantasía", 2006, 118,
                        "En la España de 1944, una niña escapa de la crueldad de su padrastro a un mundo mágico guardado por un fauno.",
                        "Blu-ray", "Nuevo", null),
                new Pelicula("Amélie", "Jean-Pierre Jeunet", "Comedia", 2001, 122,
                        "Una camarera tímida de Montmartre decide cambiar en secreto la vida de quienes la rodean.",
                        "DVD", "Usado", null),
                new Pelicula("Parásitos", "Bong Joon-ho", "Thriller", 2019, 132,
                        "Una familia pobre se infiltra poco a poco en la casa de una familia rica, hasta que todo se descontrola.",
                        "4K", "Nuevo", null));
        int grabadas = xmlPeliculas.escribirPeliculas(peliculasXml, "peliculas.xml");
        System.out.println("Películas grabadas en el XML: " + grabadas);
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
            int insertadas = importarXml(conexion, "peliculas.xml");
            System.out.println("Películas insertadas desde el XML: " + insertadas);
            int exportadas = exportarXml(conexion, "peliculas_exportadas.xml");
            System.out.println("Películas grabadas en peliculas_exportadas.xml: " + exportadas);

        } catch (SQLException e) {
            System.out.println("Error al conectar: " + e.getMessage());
        }


    }

    /**
     * Método A: lee las películas de un XML y las inserta en la tabla peliculas.
     *
     * @param conexion conexión abierta con la base de datos
     * @param ruta     ruta del archivo XML que se va a leer
     * @return número de películas insertadas
     * @throws SQLException si falla alguna inserción
     */
    public static int importarXml(Connection conexion, String ruta) throws SQLException {
        XmlPeliculas xmlPeliculas = new XmlPeliculas();
        PeliculaDao peliculaDao = new PeliculaDao();
        List<Pelicula> peliculas = xmlPeliculas.leerPeliculas(ruta);
        int insertadas = 0;
        for (int i = 0; i < peliculas.size(); i++) {
            peliculaDao.insertarPelicula(conexion, peliculas.get(i));
            insertadas++;
        }
        return insertadas;
    }

    /**
     * Método B: vuelca todas las películas de la tabla peliculas a un archivo XML.
     *
     * @param conexion conexión abierta con la base de datos
     * @param ruta     ruta del archivo XML que se va a crear
     * @return número de películas grabadas en el archivo
     * @throws SQLException si falla la lectura de la tabla
     */
    public static int exportarXml(Connection conexion, String ruta) throws SQLException {
        PeliculaDao peliculaDao = new PeliculaDao();
        XmlPeliculas xmlPeliculas = new XmlPeliculas();
        List<Pelicula> peliculas = peliculaDao.seleccionarPeliculas(conexion);
        return xmlPeliculas.escribirPeliculas(peliculas, ruta);
    }

}
