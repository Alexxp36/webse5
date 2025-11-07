<%@ page import="com.tecsup.demo.model.entities.Administrador" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%
    HttpSession misesion = request.getSession();
    if(misesion.getAttribute("eladministrador")==null){
        response.sendRedirect("error.jsp");
    }else{
        Administrador adm = (Administrador)misesion.getAttribute("eladministrador");
        String nombre = adm.getNombres() + " " + adm.getApellidos();
%>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Intranet</title>
</head>
<body>
<jsp:include page="master.jsp" />

<div class="container mt-4">
    <h2>Bienvenido <%= nombre %></h2>
    <div class="card mt-3">
        <div class="card-body">
            <h5 class="card-title">Gestión del Sistema</h5>
            <p class="card-text">Seleccione una opción del menú superior para comenzar.</p>
            <a href="cursoMan.jsp" class="btn btn-primary">Ir a Mantenimiento de Cursos</a>
        </div>
    </div>
</div>

</body>
<% } %>
</html>
