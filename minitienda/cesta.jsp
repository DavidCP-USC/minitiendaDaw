<%-- minitienda con JSTL y EL --%>
<%@ page isELIgnored="false" %>
<%@page session="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<head bgcolor="#FDF5E6">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Shopping CD</title>
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
</head>
<body>
    <h1>Carrito de la compra</h1>
    <form method="post" action="/minitienda/eliminar">
        <table>
            <thead>
                <tr>
                    <th>TITULO DEL CD</th>
                    <th>Autor</th>
                    <th>Cantidad</th>
                    <th>Importe</th>
                    <th>Marca los que quieras eliminar</th>
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
                        <td><input type="checkbox" name="remove" value="${CD.nombre}"></td>
                </tr>
                </c:forEach>
            </tbody>
        </table> <br>
        <input type="submit" value="Eliminar CDs Marcados">
    </form> 
    <br>
    <table>
        <thead>
            <tr>
                <th>Importe</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>
                    <bd><c:out value="${ListaCDS.importe}"/></b>
                </td>
            </tr>
        </tbody>
    </table>
    <br>
    <a href="/minitienda/index.html">Volver al catalogo</a>
    <br>
    <br>
    <form method="get" action="/minitienda/pagar">
        <input type="submit" value="Pagar">
    </form>
</body>
</html>
