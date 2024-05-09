<%-- minitienda con JSTL y EL --%>
<%@ page isELIgnored="false" %>
<%@page session="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
    <head>
        <title>Registro</title>
    </head>
    <body>
        <h1>El pedido casi esta hecho!</h1>
        <p>No hemos encontrado una cuenta con esas credenciales, pero puedes crear una!<p>
        <p>El importe final de tu compra es: <bd><c:out value="${ListaCDS.importe}"/></b></p>
        <p>Registra una cuenta</p>
        <form method="post" action="/minitienda/registro">
            <label for="correo">Correo electrónico:</label>
            <input type="email" id="correo" name="correo" required><br>

            <label for="contrasena">Contrasena:</label>
            <input type="password" id="contrasena" name="contrasena" required><br>
            
            <label for="tipoTarjeta">Tipo de tarjeta:</label>
            <select id="tipoTarjeta" name="tipoTarjeta" required>
                <option value="visa">Visa</option>
                <option value="mastercard">Mastercard</option>
                <option value="americanexpress">American Express</option>
            </select><br>
            
            <label for="numeroTarjeta">Número de tarjeta:</label>
            <input type="text" id="numeroTarjeta" name="numeroTarjeta" required><br>
            
            <input type="submit" value="Registrar">
        </form>
        <br>
        <a href="/minitienda/login.jsp">Login</a>
        <br>
        <a href="/minitienda/index.html">Volver al catalogo</a>
    </body>
</html>