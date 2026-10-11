<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Comprar Pasaje - LATAM Airlines</title>
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

    <div class="container" style="max-width: 800px;">
        
        <!-- Tarjeta de Resumen del Vuelo -->
        <div class="panel panel-primary" style="border-color: #1B0088;">
            <div class="panel-heading" style="background-color: #1B0088; border-color: #1B0088;">
                <h3 class="panel-title"><strong>Resumen del Vuelo Seleccionado</strong></h3>
            </div>
            <div class="panel-body">
                <div class="row">
                    <div class="col-sm-6">
                        <p><strong>N° Vuelo:</strong> <c:out value="${vuelo.numeroVuelo}"/></p>
                        <p><strong>Ruta:</strong> <c:out value="${vuelo.origen}"/> &rarr; <c:out value="${vuelo.destino}"/></p>
                    </div>
                    <div class="col-sm-6">
                        <p><strong>Fecha y Hora:</strong> <c:out value="${vuelo.fecha}"/> - <c:out value="${vuelo.hora}"/></p>
                        <p><strong>Precio del Pasaje:</strong> <span class="text-danger" style="font-size: 1.2em; font-weight: bold;">S/ <c:out value="${vuelo.precio}"/></span></p>
                    </div>
                </div>
            </div>
        </div>

        <!-- Alerta de Error de Validación en Servidor -->
        <c:if test="${not empty error}">
            <div class="alert alert-danger alert-dismissible" role="alert">
                <strong>Error:</strong> <c:out value="${error}"/>
            </div>
        </c:if>

        <!-- Formulario de Datos del Pasajero -->
        <div class="panel panel-default">
            <div class="panel-heading">
                <h4 class="panel-title"><strong>Datos del Pasajero</strong></h4>
            </div>
            <div class="panel-body">
                <form action="compra" method="POST">
                    <!-- Envía solo el ID del vuelo; el precio se toma en el servidor -->
                    <input type="hidden" name="idVuelo" value="<c:out value='${vuelo.idVuelo}'/>">

                    <div class="form-group">
                        <label for="dni">DNI (8 dígitos):</label>
                        <input type="text" class="form-control" id="dni" name="dni" maxlength="8"
                               value="<c:out value='${dni}'/>" placeholder="Ej. 74859612" required>
                    </div>

                    <div class="form-group">
                        <label for="nombres">Nombres:</label>
                        <input type="text" class="form-control" id="nombres" name="nombres"
                               value="<c:out value='${nombres}'/>" placeholder="Nombres del pasajero" required>
                    </div>

                    <div class="form-group">
                        <label for="apellidos">Apellidos:</label>
                        <input type="text" class="form-control" id="apellidos" name="apellidos"
                               value="<c:out value='${apellidos}'/>" placeholder="Apellidos completos" required>
                    </div>

                    <div class="form-group">
                        <label for="correo">Correo Electrónico:</label>
                        <input type="email" class="form-control" id="correo" name="correo"
                               value="<c:out value='${correo}'/>" placeholder="nombre@correo.com" required>
                    </div>

                    <div class="text-right">
                        <a href="catalogo" class="btn btn-default">Cancelar</a>
                        <button type="submit" class="btn btn-danger" style="background-color: #E8114B; border-color: #E8114B; font-weight: bold;">
                            Comprar pasaje
                        </button>
                    </div>
                </form>
            </div>
        </div>

    </div>

</body>
</html>