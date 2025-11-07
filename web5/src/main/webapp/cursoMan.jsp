<%@ page import="com.tecsup.demo.model.entities.Administrador" %>
<%@ page import="com.tecsup.demo.services.CursoService" %>
<%@ page import="com.tecsup.demo.services.impl.CursoServiceImpl" %>
<%@ page import="com.tecsup.demo.model.entities.Curso" %>
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
        CursoService servicio = new CursoServiceImpl();
%>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Mantenimiento de Cursos! <%=nombre %></title>
</head>
<body>
<jsp:include page="master.jsp" />

<div class="container mt-4">
    <h1>Mantenimiento de Cursos</h1>
    <div class="mb-3">
        <a href="cursoInsertar.jsp" class="btn btn-success">
            <i class="fas fa-plus"></i> Nuevo Curso
        </a>
    </div>

    <table class="table table-striped table-hover">
        <thead class="table-dark">
            <tr>
                <th>CÓDIGO</th>
                <th>NOMBRE</th>
                <th>CRÉDITOS</th>
                <th>ACCIONES</th>
            </tr>
        </thead>
        <tbody>
            <% for (Curso curso : servicio.listar()) { %>
            <tr>
                <td><%=curso.getCodigo() %></td>
                <td><%=curso.getNombre() %></td>
                <td><%=curso.getCreditos() %></td>
                <td>
                    <a class="btn btn-sm btn-primary" href="cursoActualizar.jsp?id=<%=curso.getCodigo() %>">
                        <i class="fas fa-edit"></i> Editar
                    </a>
                    <a class="btn btn-sm btn-danger" href="cursoEliminar.jsp?id=<%=curso.getCodigo() %>">
                        <i class="fas fa-trash"></i> Eliminar
                    </a>
                </td>
            </tr>
            <% } %>
        </tbody>
    </table>
</div>

</body>
<% } %>
</html>
