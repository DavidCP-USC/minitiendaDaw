# Información del proyecto
Proyecto creado para la asignatura de Desarrollo de Aplicaciones Web de la Universidad de Santiago de Compostela (USC)

Este proyecto tiene como finalidad simular el _backend_ de una aplicación web correspondiente a una tienda de discos de música. El objetivo es desarrollar una serie de funcionalidades siguiendo el patrón de diseño MVC.

## Funcionalidades implementadas

F1. Seleccionar un CD de una lista, indicar la cantidad que se desea comprar, y almacenar la selección en un carrito de la compra.

F2. Mostrar la lista de CDs del carrito de la compra y el importe total de la
Selección.

F3. Presentar el importe final de la selección para realizar el pago de la misma.

F4. Eliminar un CD del carrito y seguir con la compra o solicitar el importe final de la selección.
F5. Inicializar el carrito de la compra cuando se realiza el pago y se vuelve a la página principal, y preparar la aplicación para realizar una nueva compra.

F6. Para confirmar la compra, preguntar al usuario sus datos de contacto (nombre y correo electrónico) y su tarjeta de crédito (tipo y número de tarjeta).

F7. Almacenar el pedido en una base de datos. Crear al menos dos tablas en la
BD: una para guardar los nuevos usuarios y otra para guardar sus pedidos. Para
el pedido basta con guardar el importe final.

F8. Después de guardar los datos en la BD, mostrar al usuario un mensaje de
confirmación de la compra que contenga los datos del pedido

# Dependencias
Para poder ejecutar correctamente este proyecto, es necesario tener algunos programas instalados:
 + Java (preferiblemente ```java-8-openjdk```)
 + PostgreSQL (la versión probada es la 16.2)

# Creación de la Base de Datos
La base de datos está hecha en PostgreSQL, y en el propio proyecto hay un documento que contiene todo lo necesario para crear las tablas con sus respectivos datos.

# Notas para la correcta ejecución

Para ejecutar correctamente este proyecto hay que crear la base de datos tal y como se indicó anteriormente. No obstante, puede que al crearla necesite modificar el archivo ```minitienda/WEB-INF/classes/minitienda/conexionBD.java```. En él se ha de modificar la url de la base de datos que ha creado, el usuario y la contraseña (para evitar filtraciones de datos, se recomienda leer estos datos de un archivo externo y no tenerlos en el código). Note que si realiza alguna moficación en esta o en cualquier otra clase Java, será necesario compilarla de nuevo y volver a generar el correspondiente archivo .war.
 

# Compilación
Este apartado está dirigido a aquellas personas que quiera compilar por su cuenta de las clases Java.

## Versión de Java recomendada
Para poder compilar las clases y evitar problemas con otras que ya están compiladas, es recomendable utilizar ```java-8-openjdk```.

## Configuración del ClassPath
Para compilar alguna de las clases Java del proyecto es necesario definir la variable de entorno ```CLASSPATH```, la cuál dependerá de si se está haciendo uso de la API de JDBC para PostgreSQL, referencias a otras clases o incluso algún software de contenedor de Servlets tipo Tomcat.

Para evitar problemas definiremos un único ```CLASSPATH``` que nos sirva para todos los casos.

El ```CLASSPATH``` debe incluir todas las referencias necesarias. Se proporciona un comando que define un ```CLASSPATH``` pero que ha de adecuarse a cada sistema, dependiendo del software que utilice y de la ubicación de los archivos.

```bash
export CLASSPATH=apache-tomcat-9.0.87/lib/servlet-api.jar:minitienda/WEB-INF/classes:/minitienda/WEB-INF/lib/jstl.jar:minitienda/WEB-INF/lib/standard.jar:/minitienda/WEB-INF/lib/postgresql-42.7.3.jar
```
Una vez adecuado este el ```CLASSPATH``` a tu caso particular, ya puedes compilar las clases Java sin inconvenientes


# Despliegue del proyecto en Tomcat
Para el despliegue del proyecto en Tomcat ya está presente en la carpeta del proyecto el archivo ```minitienda.war``` que contiene todo lo necesario para desplegar correctamente el proyecto. No obstante, si ha modificado algún archivo deberá volver a generar el archivo .war. Para ello,e stando en la raíz del directorio del proyecto, se ha de ejecutar el siguiente comando:

```bash
jar -cvf minitienda.war .
```
Así se creará un archivo ```minitienda.war``` que será el que despleguemos en Tomcat

## Contribuir
Las pull request son bienvenidas. Para cambios importantes, por favor abra una *issue* primero para discutir qué le gustaría cambiar

## License

[MIT](https://choosealicense.com/licenses/mit/)
