package minitienda;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;


public class registro extends HttpServlet {
    public void init(ServletConfig config) throws ServletException {
	    super.init(config);
    }


    public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
        throws ServletException, IOException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request,
                    HttpServletResponse response)
        throws ServletException, IOException {
        // Generamos un objeto sesion
        HttpSession session = request.getSession(true);

        // Seleccionamos la conexion de la sesion
        conexionBD con = (conexionBD)session.getAttribute("conexionBD");

        // Obtenemos los parametros de entrada del formulario
        String correo = request.getParameter("correo");
           
        String contrasena = request.getParameter("contrasena");  
         
        String tipoTarjeta = request.getParameter("tipoTarjeta");   
        
        String numTarjeta = request.getParameter("numeroTarjeta");
        
         

        ListaCDS lista = (ListaCDS)session.getAttribute("ListaCDS");
        if (lista.getUser()==null){
            
            usuario newUser = new usuario();
            lista.setUser(newUser);
        }
        
        lista.getUser().setCorreo(correo);
        lista.getUser().setContrasena(contrasena);
        lista.getUser().setTipoTarjeta(tipoTarjeta);
        lista.getUser().setNumeroTarjeta(numTarjeta);
        
        // Verificamos si el usuario ya existe en la BD
        if(!con.existeUsuario(con.getConexion(), correo, contrasena)){
            con.introducirUsuario(con.getConexion(), correo, contrasena, tipoTarjeta, numTarjeta);
            session.setAttribute("ListaCDS", lista);
            con.insertarPedido(con.getConexion(), lista);
            
            // Redirigimos a la pagina de pago
            gotoPage("/pagar.jsp", request, response);
            
            

            session.invalidate();
        }
        else{
            gotoPage("/login.jsp", request, response);
        }
    }

    private void gotoPage(String address, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Creamos objeto RequestDispatcher
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }

    public void destroy() {
    }
}
