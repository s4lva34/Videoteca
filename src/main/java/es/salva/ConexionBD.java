package es.salva;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Abre la conexión con la base de datos videoteca de PostgreSQL.
 */
public class ConexionBD {
    private static final String URL = "jdbc:postgresql://localhost:5432/videoteca";
    private static final String USUARIO = "casa";
    private static final String CONTRASENA = "";

    /**
     * Abre una conexión con la base de datos usando la URL, el usuario y la contraseña.
     *
     * @return la conexión abierta
     * @throws SQLException si no se puede conectar.
     */
    public Connection abrir() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, CONTRASENA);
    }
}


