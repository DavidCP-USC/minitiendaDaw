package minitienda;

import java.sql.*;
import java.io.IOException;
import java.util.Properties;
import javax.servlet.*;
import javax.servlet.http.*;

public class conexionBD extends HttpServlet{
    String url = "jdbc:postgresql://localhost:5432/tiendadaw";
    String usuario = "sergio";
    String contrasena = "sergio";
    conexionBD con = null;

    public Connection conexion;

    // El metodo doGet() conecta la BD
    public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        this.con = new conexionBD();
    }

    // El metodo doPost() desconecta la BD
    public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        desconectar();
    }

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

    /**
     *  Este metodo ejecuta una sentencia de seleccion/consulta y muestra el resultado
     *  @param con database connection
     *  @param sqlStatement SQL SELECT statement to execute
     */
    protected void ejecutarConsulta(Connection con, String sqlStatement)
	throws Exception 
    {    
	try 
	    {
		Statement s = con.createStatement( );
		ResultSet rs = s.executeQuery( sqlStatement );
		while ( rs.next() ) 
		    {
			String id = (rs.getObject("id").toString());
			String text = (rs.getObject("text").toString());
			System.out.println ( "Registro encontrado : " + id + " " + text );
		    }
		rs.close ( );
	    } 
	catch (SQLException e) 
	    {
		System.out.println ( "Error ejecutando la sentencia SQL" );
		throw (e);
	    }
    }

}
