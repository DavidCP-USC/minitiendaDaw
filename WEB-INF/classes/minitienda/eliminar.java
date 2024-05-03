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
        HashMap<HashMap<String, Integer>, Float> cdSesion = (HashMap) session.getAttribute("cdSesion");

        // Obtenemos los CDs marcados para eliminar del parámetro de la solicitud
        String[] cdsEliminar = request.getParameterValues("remove");

        // mostramos los cds a eliminar
        System.out.println("cds a eliminar: " + Arrays.toString(cdsEliminar));

        // Verificamos si hay CDs para eliminar y si existe el hashmap de la sesión
        if (cdsEliminar != null && cdSesion != null) {
            // Creamos una lista para almacenar las claves de los CDs a eliminar
            List<HashMap<String, Integer>> cdsAEliminar = new ArrayList<>();

            // Iteramos sobre los CDs marcados para eliminar
            for (String cd : cdsEliminar) {
                // Iteramos sobre los CDs en el hashmap de la sesión
                for (HashMap<String, Integer> cdPrecio : cdSesion.keySet()) {
                    // Si encontramos una coincidencia en el nombre del CD
                    if (cdPrecio.containsKey(cd)) {
                        // Añadimos la clave del CD a la lista de CDs a eliminar
                        cdsAEliminar.add(cdPrecio);
                        break; // Salimos del bucle interno, ya que solo puede haber una coincidencia
                    }
                }
            }

            // Iteramos sobre la lista de CDs a eliminar y los eliminamos del hashmap de la sesión
            for (HashMap<String, Integer> cdAEliminar : cdsAEliminar) {
                cdSesion.remove(cdAEliminar);
            }

            // Actualizamos el hashmap de la sesión con los CDs eliminados
            session.setAttribute("cdSesion", cdSesion);
        }

        // Redirigimos de vuelta al servlet que muestra el carrito de la compra
        response.sendRedirect("/minitienda/cesta");
    }

    public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
        throws ServletException, IOException {
        // Generamos un objeto sesion
        HttpSession session = request.getSession(true);
        HashMap<HashMap, Float> cdSesion = (HashMap)session.getAttribute("cdSesion");
        float total = 0;
        if ( cdSesion != null ){
            // Calculamos el importe total
            for (Float f : cdSesion.values()) {
                total = total + f;
            }
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
