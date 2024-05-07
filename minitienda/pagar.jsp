<%-- minitienda con JSTL y EL --%>
<%@ page isELIgnored="false" %>
<%@page session="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
    <head>
        <title>Pagar</title>
    </head>
    <body>
        <h1>Pago realizado!</h1>
        <p>El importe final de tu compra es: <bd><c:out value="${ListaCDS.importe}"/></b></p>
        <p>Gracias por tu compra.</p>
        <br>
        <a href="/minitienda/index.html">Volver al catalogo</a>
    </body>
</html>