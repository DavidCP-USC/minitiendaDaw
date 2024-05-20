package minitienda;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class eliminar extends HttpServlet {
    public void init(ServletConfig config) throws ServletException {
	    super.init(config);
    }

    public void doPost(HttpServletRequest request,
                    HttpServletResponse response)
        throws ServletException, IOException {

        // Obtenemos la sesión
        HttpSession session = request.getSession(true);

        // Obtenemos el hashmap de la sesión que contiene los CDs y sus precios
        ListaCDS cesta = (ListaCDS) session.getAttribute("ListaCDS");

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
                for (CD cdCesta : cesta.getListaCD()) {
                    System.out.println("cdCesta.getNombre(): " + cdCesta.getNombre() + " cdAEliminar: " + cdAEliminar);
                    if (cdCesta.getNombre().equals(cdAEliminar)) {
                        cdsAEliminar.add(cdCesta);
                    }
                }
            }
            
            // Iteramos sobre la lista de CDs a eliminar y los eliminamos del hashmap de la sesión
            for (CD cdAEliminar : cdsAEliminar) {
                cesta.setImporte(cesta.getImporte()-cdAEliminar.getPrecio());
                cesta.getListaCD().remove(cdAEliminar);
                
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
        doPost(request, response);
    }

    public void destroy() {
    }
}
