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

        Float importe = lista.getImporte();

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
            
    
            importe+=cdSeleccionado.getCantidad()*cdSeleccionado.getPrecio();

            // Comprobamos si el CD ya esta en la cesta
            boolean encontrado = false;
            for (CD cdIterado : lista.getListaCD()) {
                if (cdIterado.getNombre().equals(cdSeleccionado.getNombre())) {
                    cdIterado.setCantidad(cdIterado.getCantidad() + cdSeleccionado.getCantidad());
                    cdIterado.setPrecio(cdIterado.getCantidad()*cdSeleccionado.getPrecio());
                    encontrado = true;
                }
            }
            cdSeleccionado.setPrecio(cdSeleccionado.getCantidad()*cdSeleccionado.getPrecio());
            if (!encontrado){
                lista.setListaCD(cdSeleccionado);
            }

            lista.setImporte(importe);


            
            session.setAttribute("ListaCDS", lista);
            System.out.println("Tamaño: " + lista.getListaCD().size());
        }
        
        // Pasamos el control al jsp
        //RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/cesta2.jsp");
        //dispatcher.forward(request, response);
        gotoPage("/cesta.jsp", request, response);
    }

    private void gotoPage(String address, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Creamos objeto RequestDispatcher
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }

    public void destroy() {
    }
    
}
