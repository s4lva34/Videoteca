package es.salva;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    private static final String URL = "jdbc:postgresql://localhost:5432/videoteca";
    private static final String USUARIO = "casa";
    private static final String CONTRASENA = "";

    public Connection abrir() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, CONTRASENA);
    }
}


