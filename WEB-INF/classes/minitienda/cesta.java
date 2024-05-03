package minitienda;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

/** Servlet para almacenar CDs seleccionados por el usuario */

public class cesta extends HttpServlet {
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

        // Generamos un objeto para el contexto de la aplicacion
        ServletContext context = getServletContext();
        
        // Obtenemos el hashmap de la sesion que contiene los CDs y sus precios
        HashMap<HashMap, Float> cdSesion = (HashMap)session.getAttribute("cdSesion");
        if ( cdSesion == null )
        {
            System.out.println("Sesion es null");
            // Creamos un hashmap de Hashmap(String, Integer) y Float
            HashMap<HashMap, Float> newCDSesion = new HashMap<HashMap, Float>();

            session.setAttribute("cdSesion", newCDSesion);
            cdSesion = (HashMap)session.getAttribute("cdSesion");
        }

        // Almacenamos los parametros de entrada en variables temporales
        String cd = request.getParameter("cd");      
        if (cd != null){
            // Recuperamos informacion del precio del CD
            StringTokenizer t = new StringTokenizer(cd,"|");
            cd = t.nextToken();
            cd = cd + t.nextToken();
            cd = cd + t.nextToken();
            String precioString = t.nextToken();
            precioString = precioString.replace('$',' ').trim();
            float precio = Float.parseFloat(precioString);
            // Seleccionamos la cantidad de CDs
            String cant = request.getParameter("cantidad");
            Integer cantidad = new Integer(Integer.parseInt(cant));
            
            // Anadimos el nombre y el precio del CD al hashmap interno
            HashMap<String, Integer> nombreCantidad = new HashMap<String, Integer>();
            nombreCantidad.put(cd, cantidad);
    
            // Anadimos el hashmap interno al hashmap de la sesion y calculamos el precio total
            cdSesion.put(nombreCantidad, precio*cantidad);
    
            // Almacenamos la suma en la sesion
            session.setAttribute("cdSesion", cdSesion);
        }


        // Generamos pagina de salida// Generamos pagina de salida
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String docType =
            "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " +
            "Transitional//EN\">\n";
        String inicio = "<head bgcolor=\"#FDF5E6\">\n" +
            "<meta charset=\"UTF-8\">\n" +
            "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
            "<title>Shopping Cart</title>\n" +
            "<style>\n" +
            " table {\n" +
                " width: 100%;\n" +
                " border-collapse: collapse;\n" +
            " }\n" +

            " th,\n" +
            " td {\n" +
                " padding: 8px;\n" +
                " text-align: left;\n" +
                " border-bottom: 1px solid #ddd;\n" +
            " }\n" +
            " th {\n" +
                " background-color: #f2f2f2;\n" +
            " }\n" +
        " </style>\n" +
        "</head>\n" +
        "<body>\n" +
            "<h1>Carrito de la compra</h1>\n" +
            "<form method=\"post\" action=\"/minitienda/eliminar\">\n" + // Formulario aquí
            "<table>\n" +
                "<thead>\n" +
                    "<tr>\n" +
                        "<th>TITULO DEL CD</th>\n" +
                        "<th>Cantidad</th>\n" +
                        "<th>Importe</th>\n" +
                        "<th>Marca los que quieras eliminar</th>\n" +
                " </tr>\n" +
            " </thead>\n";
        String centro = "<tbody>\n";

        // Insertamos los datos en la tabla
        for (HashMap<String, Float> cdPrecio : cdSesion.keySet()) {
            for (String key : cdPrecio.keySet()) {
                centro = centro +
                    "<tr>\n" +
                        "<td>" + key + "</td>\n" +
                        "<td>" + cdPrecio.get(key) + "</td>\n" +
                        "<td>" + cdSesion.get(cdPrecio) + "</td>\n" +
                        "<td><input type=\"checkbox\" name=\"remove\" value=\"" + key + "\"></td>\n" +
                " </tr>\n";
            }
        }

        centro = centro + " </tbody>\n";
        String fin = "</table>\n <br>" +
            "<input type=\"submit\" value=\"Eliminar CDs Marcados\">\n" + // Botón de enviar dentro del formulario
        "</form>\n" + // Cerrar formulario aquí
        "<br>\n" +
        "<table>\n" +
            "<thead>\n" +
                "<tr>\n" +
                    "<th>Importe</th>\n" + 
            " </tr>\n" +
        " </thead>\n" +
            "<tbody>\n" +
                "<tr>\n" +
                    "<td>";

        // Calculamos el importe total
        float total = 0;
        for (Float f : cdSesion.values()) {
            total = total + f;
        }

        fin = fin + total + "</td>\n" +
                        " </tr>\n" +
            " </table>\n" +
            "<br>\n" + 
            "<a href=\"/minitienda/index.html\">Volver al catalogo</a>\n" +
            "<br>\n" +
            "<br>\n" +
        "<form method=\"get\" action=\"/minitienda/eliminar\">\n" +
            "<input type=\"submit\" value=\"Pagar\">\n" +
        "</form>\n" +
        " </body>\n" +
        " </html>";

        out.println(docType + inicio + centro + fin);

    }
}
