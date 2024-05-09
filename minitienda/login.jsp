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
        <p>El importe final de tu compra es: <bd><c:out value="${ListaCDS.importe}"/></b></p>
        <p>Inicia sesion con tu cuenta</p>
        <form method="post" action="/minitienda/login">
            <label for="correo">Correo electrónico:</label>
            <input type="email" id="correo" name="correo" required><br>

            <label for="contrasena">Contrasena:</label>
            <input type="password" id="contrasena" name="contrasena" required><br>
            <input type="submit" value="Iniciar sesión">
        </form>
        <br>
        <a href="/minitienda/registrarUsuario.jsp">Registrar</a>
        <br>
        <a href="/minitienda/index.html">Volver al catalogo</a>
    </body>
</html>