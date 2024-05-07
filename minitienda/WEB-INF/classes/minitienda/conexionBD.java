package minitienda;

import java.sql.*;
import java.io.IOException;
import java.util.Properties;

public class conexionBD {
    String url = "jdbc:postgresql://localhost:5432/tiendadaw";
    String usuario = "sergio";
    String contrasena = "sergio";

    public Connection conexion;

    

    public conexionBD() {
        testDriver();
        conectar();
        
    }

    /**
    * Comprueba si el driver de conexion JDBC de MySQL esta instalado*/
    protected void testDriver()
    {
        try
        {
            Class.forName ( "org.postgresql.Driver" );
            System.out.println ( "Encontrado el driver de PostgreSQL" );
        }
        catch(java.lang.ClassNotFoundException e)
        {
            System.out.println("PostgreSQL JDBC Driver no encontrado ... ");
        }
    }

    private void conectar() {
        try {
            this.conexion = DriverManager.getConnection(url, usuario, contrasena);
            System.out.println("Conexión exitosa");
        } catch (SQLException e) {
            System.out.println("Error en conexión: " + e.getMessage());
        }
    }

    private void desconectar() {
        try {
            this.conexion.close();
            System.out.println("Desconexión exitosa");
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }

}
