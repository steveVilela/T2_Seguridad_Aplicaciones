<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Confirmación de Compra - LATAM Airlines</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body style="background-color: #f7f7f9;">

    <nav class="navbar navbar-default">
        <div class="container">
            <div class="navbar-header">
                <a class="navbar-brand" href="${pageContext.request.contextPath}/catalogo">
                    <strong>LATAM AIRLINES</strong>
                </a>
            </div>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="${pageContext.request.contextPath}/catalogo">Catálogo</a></li>
                <li><a href="${pageContext.request.contextPath}/consulta">Mis pasajes</a></li>
            </ul>
        </div>
    </nav>

    <div class="container" style="max-width: 750px;">
        <div class="panel panel-success">
            <div class="panel-heading text-center" style="background-color: #dff0d8;">
                <h2 style="color: #3c763d; margin-top: 10px;">¡Compra Realizada con Éxito!</h2>
                <p>Su boleto de viaje ha sido emitido satisfactoriamente.</p>
            </div>
            <div class="panel-body">
                <div class="text-center" style="margin: 20px 0;">
                    <h4>CÓDIGO DE PASAJE:</h4>
                    <span class="label label-primary" style="font-size: 24px; padding: 10px 20px; background-color: #1B0088;">
                        <c:out value="${venta.codigo}"/>
                    </span>
                </div>

                <hr>

                <h4 style="color: #1B0088;"><strong>Detalle del Pasajero</strong></h4>
                <p><strong>Pasajero:</strong> <c:out value="${venta.pasajero.nombres}"/> <c:out value="${venta.pasajero.apellidos}"/></p>
                <p><strong>DNI:</strong> <c:out value="${venta.pasajero.dni}"/></p>
                <p><strong>Correo de contacto:</strong> <c:out value="${venta.pasajero.correo}"/></p>

                <hr>

                <h4 style="color: #1B0088;"><strong>Detalle del Vuelo</strong></h4>
                <table class="table table-bordered">
                    <tr>
                        <th class="active">N° de Vuelo</th>
                        <td><c:out value="${venta.vuelo.numeroVuelo}"/></td>
                        <th class="active">Fecha</th>
                        <td><c:out value="${venta.vuelo.fecha}"/></td>
                    </tr>
                    <tr>
                        <th class="active">Origen</th>
                        <td><c:out value="${venta.vuelo.origen}"/></td>
                        <th class="active">Hora</th>
                        <td><c:out value="${venta.vuelo.hora}"/></td>
                    </tr>
                    <tr>
                        <th class="active">Destino</th>
                        <td><c:out value="${venta.vuelo.destino}"/></td>
                        <th class="active">Total Pagado</th>
                        <td class="text-danger fw-bold"><strong>S/ <c:out value="${venta.precio}"/></strong></td>
                    </tr>
                </table>

                <div class="text-center" style="margin-top: 30px;">
                    <a href="consulta" class="btn btn-primary" style="background-color: #1B0088; border-color: #1B0088;">
                        Ir a Mis Pasajes
                    </a>
                    <a href="catalogo" class="btn btn-default">
                        Volver al Catálogo
                    </a>
                </div>
            </div>
        </div>
    </div>

</body>
</html>