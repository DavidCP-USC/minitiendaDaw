package minitienda;

import java.sql.*;
import java.io.IOException;
import java.util.Properties;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.Random;

public class conexionBD extends HttpServlet{
    String url = "jdbc:postgresql://localhost:5432/tiendadaw";
    String usuario = "postgres";
    String contrasena = "psql";
    // id random
    int id = 0;
    public Connection conexion;

    public conexionBD() {
        testDriver();
        if (conectar()){
            // Sentencia SQL para obtener el ID más alto de la tabla de pedidos
            String sql = "SELECT MAX(id) AS max_id FROM Pedidos";

            try (PreparedStatement pstmt = this.conexion.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    // Obtener el ID máximo de la tabla de pedidos
                    this.id = rs.getInt("max_id") + 1;
                }
            } catch (SQLException e) {
                System.out.println("Error al obtener el ID máximo de la tabla de pedidos: " + e.getMessage());
                // Si no se puede obtener el ID máximo, se devuelve -1 como indicador de error
                this.id = 1001;
            }
        }
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

    private boolean conectar() {
        try {
            this.conexion = DriverManager.getConnection(url, usuario, contrasena);
            System.out.println("Conexión exitosa");
            return true;
        } catch (SQLException e) {
            System.out.println("Error en conexión: " + e.getMessage());
            return false;
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

    // Funcion que devuelva un booleano si un usuario con un correo y una contrasena esta en la BD
    protected boolean existeUsuario(Connection con, String correo, String contrasena) {
        try {
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM usuarios WHERE correo = '" + correo + "' AND contrasinal = '" + contrasena + "'");
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar usuario: " + e.getMessage());
        }
        return false;
    }

    // Funcion que devuelva un booleano si un usuario con un correo y una contrasena esta en la BD
    protected void introducirUsuario(Connection con, String correo, String contrasena, String tipotarjeta, String numtarjeta) {
    try {
        
        // Crear una sentencia preparada para la inserción del usuario
        PreparedStatement ps = con.prepareStatement("INSERT INTO usuarios (correo, contrasinal, tipotarjeta, numerotarjeta) VALUES (?, ?, ?, ?)");
        
        // Establecer los valores de los parámetros
        ps.setString(1, correo);
        ps.setString(2, contrasena);
        ps.setString(3, tipotarjeta);
        ps.setString(4,numtarjeta);
        
        // Ejecutar la inserción
        int filasInsertadas = ps.executeUpdate();
        
        // Verificar si la inserción fue exitosa
        if (filasInsertadas > 0) {
            System.out.println("Usuario insertado correctamente.");
        } else {
            System.out.println("No se pudo insertar el usuario.");
        }
        
        // Cerrar la sentencia preparada
        ps.close();
        
    } catch (SQLException e) {
        System.out.println("Error al introducir usuario: " + e.getMessage());
    }
}

    // Método para insertar un pedido en la base de datos
    public void insertarPedido(Connection conn, ListaCDS lista) {
        // Obtención de los datos del usuario
        String correoUsuario = lista.getUser().getCorreo();

        // Sentencia SQL para insertar un pedido
        String sqlPedido = "INSERT INTO Pedidos (id, correo, importe) VALUES (?, ?, ?)";

        // Sentencia SQL para insertar un CD vendido
        String sqlCDVendido = "INSERT INTO CD_Vendidos (id, autor, nombre, lugar, cantidad) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement pstmtPedido = conn.prepareStatement(sqlPedido);
            PreparedStatement pstmtCDVendido = conn.prepareStatement(sqlCDVendido)) {
            // Generación de un ID único para el pedido (puedes implementar tu lógica aquí)
            

            // Establecimiento de los parámetros del pedido
            pstmtPedido.setInt(1, this.id);
            pstmtPedido.setString(2, correoUsuario);
            pstmtPedido.setFloat(3, lista.getImporte());

            // Ejecución de la sentencia SQL para insertar el pedido
            pstmtPedido.executeUpdate();
            

            // Iteramos entre los CDS para anadirlos a CD vendido
            for (CD cd : lista.getListaCD()) {
                insertarCDVendido(pstmtCDVendido, this.id, cd.getAutor(), cd.getNombre(), cd.getLugar(), cd.getCantidad());
                System.out.println("Pedido insertado correctamente.");
            }
            aumentarIDPedido();

        } catch (SQLException e) {
            System.out.println("Error al insertar el pedido: " + e.getMessage());
        }
        desconectar();
    }

    private void insertarCDVendido(PreparedStatement pstmt, int idPedido, String autor, String nombre, String lugar, Integer cantidad) throws SQLException {
        // Establecimiento de los parámetros del CD vendido
        pstmt.setInt(1, idPedido);
        pstmt.setString(2, autor);
        pstmt.setString(3, nombre);
        pstmt.setString(4, lugar);
        pstmt.setInt(5, cantidad);

        // Ejecución de la sentencia SQL para insertar el CD vendido
        pstmt.executeUpdate();
    }

    // Método para generar un ID único para el pedido (puedes implementar tu lógica aquí)
    private void aumentarIDPedido() {
        this.id += 1;
    }

}
