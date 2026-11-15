package es.salva;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class PeliculaDao {
    public void crearTabla(Connection conexion) throws SQLException {
        String sql = "CREATE TABLE peliculas ("
                + "id SERIAL PRIMARY KEY, "
                + "titulo VARCHAR(200) NOT NULL, "
                + "director VARCHAR(100), "
                + "genero VARCHAR(50), "
                + "anio INTEGER, "
                + "duracion INTEGER, "
                + "sinopsis TEXT, "
                + "formato VARCHAR(50), "
                + "estado VARCHAR(30), "
                + "fecha_adquisicion DATE)";
        try (Statement sentencia = conexion.createStatement()) {
            sentencia.execute(sql);
        }
    }

    public void insertarPelicula(Connection conexion, Pelicula pelicula) throws SQLException {
        String sql = "INSERT INTO peliculas (titulo, director, genero, anio, duracion, sinopsis, "
                + "formato, estado, fecha_adquisicion) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement sentencia = conexion.prepareStatement(sql)) {
            sentencia.setString(1, pelicula.getTitulo());
            sentencia.setString(2, pelicula.getDirector());
            sentencia.setString(3, pelicula.getGenero());
            sentencia.setInt(4, pelicula.getAnio());
            sentencia.setInt(5, pelicula.getDuracion());
            sentencia.setString(6, pelicula.getSinopsis());
            sentencia.setString(7, pelicula.getFormato());
            sentencia.setString(8, pelicula.getEstado());
            sentencia.setObject(9, pelicula.getFechaAdquisicion());
            sentencia.executeUpdate();
        }
    }
}
