<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Mis Pasajes - LATAM Airlines</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    
    <!-- Regla CSS para impresión segura -->
    <style>
        @media print {
            .no-print, .navbar, .btn, form {
                display: none !important;
            }
            .panel {
                border: 1px solid #ddd !important;
                box-shadow: none !important;
            }
        }
    </style>
</head>
<body style="background-color: #f7f7f9;">

    <nav class="navbar navbar-default no-print">
        <div class="container">
            <div class="navbar-header">
                <a class="navbar-brand" href="${pageContext.request.contextPath}/catalogo">
                    <strong>LATAM AIRLINES</strong>
                </a>
            </div>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="${pageContext.request.contextPath}/catalogo">Catálogo</a></li>
                <li class="active"><a href="${pageContext.request.contextPath}/consulta">Mis pasajes</a></li>
            </ul>
        </div>
    </nav>

    <div class="container">
        
        <div class="jumbotron no-print" style="background-color: #1B0088; color: white;">
            <h2><strong>Consulta de Pasajes</strong></h2>
            <p>Ingresa tu número de DNI para consultar o imprimir tus boletos adquiridos.</p>
        </div>

        <!-- Formulario de Consulta -->
        <div class="panel panel-default no-print">
            <div class="panel-body">
                <form action="consulta" method="POST" class="form-inline text-center">
                    <div class="form-group">
                        <label for="dni" style="margin-right: 10px;">DNI:</label>
                        <input type="text" class="form-control" id="dni" name="dni" maxlength="8"
                               value="<c:out value='${dniBuscado}'/>" placeholder="Ingrese 8 dígitos" required>
                    </div>
                    <button type="submit" class="btn btn-danger" style="background-color: #E8114B; border-color: #E8114B;">
                        Consultar
                    </button>
                </form>
            </div>
        </div>

        <!-- Resultados de la Búsqueda -->
        <c:if test="${consultado}">
            <c:choose>
                <c:when test="${not empty listaVentas}">
                    <div class="row">
                        <div class="col-xs-12 text-right no-print" style="margin-bottom: 15px;">
                            <button onclick="window.print()" class="btn btn-default">
                                <span class="glyphicon glyphicon-print"></span> Imprimir boletos
                            </button>
                        </div>
                    </div>

                    <c:forEach var="v" items="${listaVentas}">
                        <div class="panel panel-primary" style="border-color: #1B0088;">
                            <div class="panel-heading" style="background-color: #1B0088; border-color: #1B0088;">
                                <h3 class="panel-title">
                                    Boleto Electrónico: <strong><c:out value="${v.codigo}"/></strong>
                                </h3>
                            </div>
                            <div class="panel-body">
                                <div class="row">
                                    <div class="col-sm-6">
                                        <p><strong>Pasajero:</strong> <c:out value="${v.pasajero.nombres}"/> <c:out value="${v.pasajero.apellidos}"/></p>
                                        <p><strong>DNI:</strong> <c:out value="${v.pasajero.dni}"/></p>
                                        <p><strong>Vuelo:</strong> <c:out value="${v.vuelo.numeroVuelo}"/></p>
                                        <p><strong>Ruta:</strong> <c:out value="${v.vuelo.origen}"/> &rarr; <c:out value="${vuelo.destino != null ? vuelo.destino : v.vuelo.destino}"/></p>
                                    </div>
                                    <div class="col-sm-6">
                                        <p><strong>Fecha Vuelo:</strong> <c:out value="${v.vuelo.fecha}"/></p>
                                        <p><strong>Hora Vuelo:</strong> <c:out value="${v.vuelo.hora}"/></p>
                                        <p><strong>Precio:</strong> <strong class="text-danger">S/ <c:out value="${v.precio}"/></strong></p>
                                        <p><strong>Fecha de Compra:</strong> <c:out value="${v.fechaCompra}"/></p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </c:when>

                <c:otherwise>
                    <div class="alert alert-warning text-center" role="alert">
                        No se encontraron pasajes comprados para ese DNI.
                    </div>
                </c:otherwise>
            </c:choose>
        </c:if>

    </div>

</body>
</html>