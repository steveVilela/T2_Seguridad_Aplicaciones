<%@page contentType="text/html" pageEncoding="UTF-8"%> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
 
<!DOCTYPE html> 
<html> 
<head> 
    <meta charset="UTF-8"> 
    <meta name="viewport" content="width=device-width, initial-scale=1"> 
    <title>Catálogo de vuelos - LATAM Airlines</title> 
 
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"> 
</head> 
<body> 
 
    <nav class="navbar navbar-default"> 
        <div class="container"> 
            <div class="navbar-header"> 
                <a class="navbar-brand" href="${pageContext.request.contextPath}/catalogo"> 
                    <strong>LATAM AIRLINES</strong> 
                </a> 
            </div> 
 
            <ul class="nav navbar-nav navbar-right"> 
                <li class="active"> 
                    <a href="${pageContext.request.contextPath}/catalogo">Catálogo</a> 
                </li> 
                <li> 
                    <a href="${pageContext.request.contextPath}/consulta">Mis pasajes</a> 
                </li> 
            </ul> 
        </div> 
    </nav> 
 
    <div class="container"> 
 
<div class="jumbotron"
     style="background-color: #1B0088; color: white;">

    <h1>
        <strong>Catálogo de vuelos</strong>
    </h1>

    <p>
        Elige tu vuelo y compra tu pasaje.
    </p>

</div>
 
        <div class="table-responsive"> 
            <table class="table table-striped table-hover"> 
                <thead> 
                    <tr> 
                        <th>Vuelo</th> 
                        <th>Origen</th> 
                        <th>Destino</th> 
                        <th>Fecha</th> 
                        <th>Hora</th> 
                        <th>Precio</th> 
                        <th>Acción</th> 
                    </tr> 
                </thead> 
 
                <tbody> 
                    <c:forEach var="vuelo" items="${vuelos}"> 
                        <tr> 
                            <td><strong><c:out value="${vuelo.numeroVuelo}"/></strong></td> 
                            <td><c:out value="${vuelo.origen}"/></td> 
                            <td><c:out value="${vuelo.destino}"/></td> 
                            <td><c:out value="${vuelo.fecha}"/></td> 
                            <td><c:out value="${vuelo.hora}"/></td> 
                            <td><strong>S/ <c:out value="${vuelo.precio}"/></strong></td> 
                            <td> 
                                <a class="btn btn-danger btn-sm" 
                                   href="${pageContext.request.contextPath}/compra?id=${vuelo.idVuelo}"> 
                                    Comprar 
                                </a> 
                            </td> 
                        </tr> 
                    </c:forEach> 
 
                    <c:if test="${empty vuelos}"> 
                        <tr> 
                            <td colspan="7" class="text-center"> 
                                <div class="alert alert-info"> 
                                    No hay vuelos disponibles actualmente. 
                                </div> 
                            </td> 
                        </tr> 
                    </c:if> 
                </tbody> 
            </table> 
        </div> 
 
        <hr> 
 
        <div class="text-center"> 
            <p class="text-muted">LATAM Airlines - Sistema de venta de pasajes</p> 
        </div> 
 
    </div> 
 
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script> 
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> 
 
</body> 
</html> 