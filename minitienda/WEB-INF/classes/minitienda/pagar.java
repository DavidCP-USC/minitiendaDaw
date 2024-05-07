package minitienda;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class pagar extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Generamos un objeto sesion
        HttpSession session = request.getSession(true);

        // Obtenemos el hashmap de la sesión que contiene los CDs y sus precios
        ListaCDS cesta = (ListaCDS) session.getAttribute("ListaCDS");

        if(cesta.getImporte() <= 0){
            gotoPage("/cesta.jsp", request, response);
        }
        else{
            ///////////////////

            gotoPage("/registroAntesDePagar.jsp", request, response);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private void gotoPage(String address, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Creamos objeto RequestDispatcher
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }

}