package minitienda;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

/** Servlet para almacenar CDs seleccionados por el usuario */

public class cesta extends HttpServlet {

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

        // Generamos un objeto para el contexto de la aplicacion
        ServletContext context = getServletContext();
        
        ListaCDS lista = (ListaCDS)session.getAttribute("ListaCDS");
        if (lista == null){
            ListaCDS newlista = new ListaCDS();
            session.setAttribute("ListaCDS", newlista);
            lista = (ListaCDS)session.getAttribute("ListaCDS");
        }
        // Almacenamos los parametros de entrada en variables temporales
        String cd = request.getParameter("cd");   
        CD cdSeleccionado = new CD();
        if (cd != null){
            // Recuperamos informacion del precio del CD
            // Formato del CD: autor|nombre|lugar|precio
            StringTokenizer t = new StringTokenizer(cd,"|");
            cd = t.nextToken();
            cdSeleccionado.setAutor(cd);
            cd = t.nextToken();
            cdSeleccionado.setNombre(cd);
            cd = t.nextToken();
            cdSeleccionado.setLugar(cd);
            String precioString = t.nextToken();
            precioString = precioString.replace('$',' ').trim();
            cdSeleccionado.setPrecio(Float.parseFloat(precioString));
            // Seleccionamos la cantidad de CDs
            String cant = request.getParameter("cantidad");
            cdSeleccionado.setCantidad(Integer.parseInt(cant));
    

            // Comprobamos si el CD ya esta en la cesta
            boolean encontrado = false;
            for (CD cdIterado : lista.getListaCD()) {
                if (cdIterado.getNombre().equals(cdSeleccionado.getNombre())) {
                    cdIterado.setCantidad(cdIterado.getCantidad() + cdSeleccionado.getCantidad());
                    encontrado = true;
                }
            }
            if (!encontrado){
                lista.setListaCD(cdSeleccionado);
            }


            
            session.setAttribute("ListaCDS", lista);
            System.out.println("Tamaño: " + lista.getListaCD().size());
        }
        
        // Pasamos el control al jsp
        //RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/cesta2.jsp");
        //dispatcher.forward(request, response);
        gotoPage("/cesta2.jsp", request, response);


        // Generamos pagina de salida// Generamos pagina de salida
        /*response.setContentType("text/html");
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
        for (CD cdPrecio : cesta) {
            centro = centro +
                "<tr>\n" +
                    "<td>" + cdPrecio.getNombre() + "</td>\n" +
                    "<td>" + cdPrecio.getCantidad() + "</td>\n" +
                    "<td>" + cdPrecio.getPrecio() + "</td>\n" +
                    "<td><input type=\"checkbox\" name=\"remove\" value=\"" + cdPrecio.getNombre() + "\"></td>\n" +
            " </tr>\n";
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
        for (CD cdPrecio : cesta) {
            total = total + cdPrecio.getPrecio() * cdPrecio.getCantidad();
        }


        fin = fin + total + "</td>\n" +
                        " </tr>\n" +
            " </table>\n" +
            "<br>\n" + 
            "<a href=\"/minitienda/index.html\">Volver al catalogo</a>\n" +
            "<br>\n" +
            "<br>\n" +
        "<form method=\"get\" action=\"/minitienda/pagar\">\n" +
            "<input type=\"submit\" value=\"Pagar\">\n" +
        "</form>\n" +
        " </body>\n" +
        " </html>";

        out.println(docType + inicio + centro + fin);
        */
    }

    private void gotoPage(String address, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Creamos objeto RequestDispatcher
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }

    public void destroy() {
    }
    
}
