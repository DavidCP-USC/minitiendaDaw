<%-- minitienda con JSTL y EL --%>
<%@ page isELIgnored="false" %>
<%@page session="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
    <head>
        <style>
            table {
            width: 100%;
            border-collapse: collapse;
            }
    
            th,td {
                padding: 8px;
                text-align: left;
                border-bottom: 1px solid #ddd;
                }
            th {
                    background-color: #f2f2f2;
                }
        </style>
        <title>Pagar</title>
    </head>
    <body>
        <h1>Pago realizado!</h1>
        <h2>Has comprado los siguientes productos</h2>
        <table>
            <thead>
                <tr>
                    <th>TITULO DEL CD</th>
                    <th>Autor</th>
                    <th>Cantidad</th>
                    <th>Importe</th>
                </tr>
            </thead>
            <tbody>
                <c:set var="vectorCD" value="${ListaCDS.listaCD}"/>
                <c:forEach var="CD" items="${vectorCD}" >
                <tr>
                    <td><bd><c:out value="${CD.nombre}"/></b></td>
                    <td><bd><c:out value="${CD.autor}"/></b></td>
                    <td><bd><c:out value="${CD.cantidad}"/></b></td>
                    <td><bd><c:out value="${CD.precio}"/></b></td>
                </tr>
                </c:forEach>
            </tbody>
        </table>
        <br>
        <p>El importe final de tu compra es: <bd><c:out value="${ListaCDS.importe}"/></b></p>
        <br>
        <a href="/minitienda/index.html">Volver al catalogo</a>
    </body>
</html>