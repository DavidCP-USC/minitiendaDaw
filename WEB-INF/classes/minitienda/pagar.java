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
        ArrayList<CD> cesta = (ArrayList)session.getAttribute("cesta");
        float total = 0;
        if( cesta != null ){
            // Calculamos el importe total
            for (CD cd : cesta) {
                total += cd.getPrecio() * cd.getCantidad();
            }
            // HACER COMPROBACION DE PRECIO > 0 
            // TODO
        }

        // Creamos el objeto out
        PrintWriter out = response.getWriter();
        out.println("<html>" + 
            "<head>" +
                "<title>Pagar</title>" +
            "</head>" +
            "<body>" +
                "<h1>Pago realizado!</h1>" +
                "<p>El importe final de tu compra es: " + total +"</p>" +
                "<p>Gracias por tu compra.</p>" +
                "<br>\n" + 
            "<a href=\"/minitienda/index.html\">Volver al catalogo</a>\n" +
            "</body>" +
            "</html>"
        );
        // Invalidamos la sesion
        session.invalidate();
    }

    public void doPost(HttpServletRequest request,
                    HttpServletResponse response)
        throws ServletException, IOException {
        doGet(request, response);
    }
}
