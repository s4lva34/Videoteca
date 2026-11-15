package es.salva;

import java.sql.Connection;
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
}
