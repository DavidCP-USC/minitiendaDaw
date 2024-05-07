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
        System.out.println("HE ENTRADO\n");
        // Generamos un objeto sesion
        HttpSession session = request.getSession(true);


        

        String correo = request.getParameter("correo");
           
        String nombre = request.getParameter("nombre");  
         
        String tipoTarjeta = request.getParameter("tipoTarjeta");   
        
        String numTarjetaStr = request.getParameter("numeroTarjeta");
        Integer numTarjeta = Integer.parseInt(numTarjetaStr);
         

        ListaCDS lista = (ListaCDS)session.getAttribute("ListaCDS");
        if (lista.getUser()==null){
            
            usuario newUser = new usuario();
            lista.setUser(newUser);
        }
        
        lista.getUser().setCorreo(correo);
        lista.getUser().setNombre(nombre);
        lista.getUser().setTipoTargeta(tipoTarjeta);
        lista.getUser().setNumeroTargeta(numTarjeta);
        
        /// VERIFICAR EN LA BD: TODO

        session.setAttribute("ListaCDS", lista);
        
        
        // Pasamos el control al jsp
        //RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/cesta2.jsp");
        //dispatcher.forward(request, response);
        
        gotoPage("/pagar.jsp", request, response);
        session.invalidate();
    }

    private void gotoPage(String address, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Creamos objeto RequestDispatcher
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }

    public void destroy() {
    }
}
