package minitienda;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class eliminar extends HttpServlet {


    public void doPost(HttpServletRequest request,
                    HttpServletResponse response)
        throws ServletException, IOException {

        // Obtenemos la sesión
        HttpSession session = request.getSession(true);

        // Obtenemos el hashmap de la sesión que contiene los CDs y sus precios
        ArrayList<CD> cesta = (ArrayList) session.getAttribute("cesta");

        // Obtenemos los CDs marcados para eliminar del parámetro de la solicitud
        String[] cdsEliminar = request.getParameterValues("remove");

        // mostramos los cds a eliminar
        System.out.println("cds a eliminar: " + Arrays.toString(cdsEliminar));

        // Verificamos si hay CDs para eliminar y si existe el hashmap de la sesión
        if (cdsEliminar != null && cesta != null) {
            // Creamos una lista de CDs a eliminar
            ArrayList<CD> cdsAEliminar = new ArrayList<>(); 

            // Iteramos sobre los CDs a eliminar y los añadimos a la lista
            for (String cdAEliminar: cdsEliminar) {
                for (CD cdCesta : cesta) {
                    System.out.println("cdCesta.getNombre(): " + cdCesta.getNombre() + " cdAEliminar: " + cdAEliminar);
                    if (cdCesta.getNombre().equals(cdAEliminar)) {
                        cdsAEliminar.add(cdCesta);
                    }
                }
            }
            
            // Iteramos sobre la lista de CDs a eliminar y los eliminamos del hashmap de la sesión
            for (CD cdAEliminar : cdsAEliminar) {
                cesta.remove(cdAEliminar);
            }

            // Actualizamos el hashmap de la sesión con los CDs eliminados
            session.setAttribute("cesta", cesta);
        }

        // Redirigimos de vuelta al servlet que muestra el carrito de la compra
        response.sendRedirect("/minitienda/cesta");
    }

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
}
