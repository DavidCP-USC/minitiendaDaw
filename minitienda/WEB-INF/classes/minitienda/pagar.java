package minitienda;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class pagar extends HttpServlet {

    public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
        throws ServletException, IOException {
        // Generamos un objeto sesion
        HttpSession session = request.getSession(true);
        ListaCDS cesta = (ListaCDS)session.getAttribute("ListaCDS");
        float total = 0;
        if( cesta != null ){
            // Calculamos el importe total
            for (CD cd : cesta.getListaCD()) {
                total += cd.getPrecio() * cd.getCantidad();
            }
            // HACER COMPROBACION DE PRECIO > 0 
            // TODO
        }

        gotoPage("/pagar.jsp", request, response);
        
        // Invalidamos la sesion
        session.invalidate();
    }

    public void doPost(HttpServletRequest request,
                    HttpServletResponse response)
        throws ServletException, IOException {
        doGet(request, response);
    }

    private void gotoPage(String address, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Creamos objeto RequestDispatcher
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }

}