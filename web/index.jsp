<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Contactos</h1>
        <form action="Grabador" method="post">
            Nombre: <input type="text" name="nombre">
            <br>
            Telefono: <input type="text" name="telefono">
            <br>
            Correo: <input type="text" name="correo">
            <br>
            <input type="submit">
        </form>
        <p></p>
        <a href="Listador">Ver todos los registros</a>

    </body>
</html>
