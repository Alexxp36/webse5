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
    <p class="text-muted">Sistema de Gestión Académica</p>

    <div class="row mt-4">
        <div class="col-md-4 mb-3">
            <div class="card border-primary">
                <div class="card-body text-center">
                    <i class="fas fa-graduation-cap fa-3x text-primary mb-3"></i>
                    <h5 class="card-title">Matrículas</h5>
                    <p class="card-text">Gestión de matrículas por periodo académico y asignación de cursos.</p>
                    <a href="matricula?accion=listar" class="btn btn-primary">
                        <i class="fas fa-list"></i> Ver Matrículas
                    </a>
                    <a href="matricula?accion=nuevo" class="btn btn-outline-primary">
                        <i class="fas fa-plus"></i> Nueva
                    </a>
                </div>
            </div>
        </div>

        <div class="col-md-4 mb-3">
            <div class="card border-success">
                <div class="card-body text-center">
                    <i class="fas fa-clipboard-list fa-3x text-success mb-3"></i>
                    <h5 class="card-title">Notas</h5>
                    <p class="card-text">Registro de evaluaciones, calificaciones y cálculo de promedios.</p>
                    <a href="nota?accion=listar" class="btn btn-success">
                        <i class="fas fa-list"></i> Ver Notas
                    </a>
                    <a href="nota?accion=evaluaciones" class="btn btn-outline-success">
                        <i class="fas fa-cog"></i> Evaluaciones
                    </a>
                </div>
            </div>
        </div>

        <div class="col-md-4 mb-3">
            <div class="card border-warning">
                <div class="card-body text-center">
                    <i class="fas fa-calendar-check fa-3x text-warning mb-3"></i>
                    <h5 class="card-title">Asistencia</h5>
                    <p class="card-text">Control de asistencia por sesión de clase y seguimiento de faltas.</p>
                    <a href="asistencia?accion=listar" class="btn btn-warning">
                        <i class="fas fa-list"></i> Ver Asistencias
                    </a>
                    <a href="asistencia?accion=sesiones" class="btn btn-outline-warning">
                        <i class="fas fa-calendar"></i> Sesiones
                    </a>
                </div>
            </div>
        </div>
    </div>

    <div class="row mt-4">
        <div class="col-md-6 mb-3">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title"><i class="fas fa-book"></i> Gestión de Cursos</h5>
                    <p class="card-text">Administre los cursos del sistema.</p>
                    <a href="cursoMan.jsp" class="btn btn-secondary">
                        <i class="fas fa-arrow-right"></i> Ir a Cursos
                    </a>
                </div>
            </div>
        </div>

        <div class="col-md-6 mb-3">
            <div class="card bg-light">
                <div class="card-body">
                    <h5 class="card-title"><i class="fas fa-info-circle"></i> Accesos Rápidos</h5>
                    <div class="d-grid gap-2">
                        <a href="nota?accion=evaluaciones" class="btn btn-sm btn-outline-primary">
                            <i class="fas fa-tasks"></i> Gestionar Evaluaciones
                        </a>
                        <a href="asistencia?accion=sesiones" class="btn btn-sm btn-outline-primary">
                            <i class="fas fa-chalkboard-teacher"></i> Sesiones de Clase
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
<% } %>
</html>
