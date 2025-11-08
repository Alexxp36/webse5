<%@ page import="com.tecsup.demo.model.entities.SesionClase" %>
<%@ page import="com.tecsup.demo.model.entities.SesionClaseDetallada" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Gestión de Sesiones de Clase</title>
</head>
<body>
<jsp:include page="/master.jsp" />

<div class="container mt-4">
    <h1>Gestión de Sesiones de Clase</h1>

    <div class="mb-3">
        <button class="btn btn-success" data-bs-toggle="modal" data-bs-target="#modalNuevaSesion">
            <i class="fas fa-plus"></i> Nueva Sesión
        </button>
        <a href="asistencia?accion=listar" class="btn btn-secondary">
            <i class="fas fa-arrow-left"></i> Volver
        </a>
    </div>

    <%
        Object sesionesObj = request.getAttribute("sesiones");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        boolean hayDatos = false;

        if (sesionesObj instanceof List) {
            List<?> sesiones = (List<?>) sesionesObj;
            hayDatos = !sesiones.isEmpty();

            if (hayDatos) {
    %>

    <table class="table table-striped table-hover">
        <thead class="table-dark">
            <tr>
                <th>ID</th>
                <th>CURSO</th>
                <th>PERIODO</th>
                <th>FECHA</th>
                <th>TEMA</th>
                <th>ACCIONES</th>
            </tr>
        </thead>
        <tbody>
            <%
                for (Object obj : sesiones) {
                    if (obj instanceof SesionClaseDetallada) {
                        SesionClaseDetallada sesion = (SesionClaseDetallada) obj;
            %>
            <tr>
                <td><%=sesion.getIdSesion() %></td>
                <td>
                    <span class="badge bg-primary"><%=sesion.getIdCurso() %></span><br>
                    <small><%=sesion.getNombreCurso() %></small>
                </td>
                <td><span class="badge bg-info"><%=sesion.getNombrePeriodo() %></span></td>
                <td><%=sdf.format(sesion.getFecha()) %></td>
                <td><%=sesion.getTema() %></td>
                <td>
                    <a class="btn btn-sm btn-primary" href="asistencia?accion=verPorSesion&idSesion=<%=sesion.getIdSesion() %>">
                        <i class="fas fa-list"></i> Ver Asistencias
                    </a>
                    <a class="btn btn-sm btn-success" href="asistencia?accion=registrar&idSesion=<%=sesion.getIdSesion() %>">
                        <i class="fas fa-check"></i> Registrar
                    </a>
                </td>
            </tr>
            <%
                    } else if (obj instanceof SesionClase) {
                        SesionClase sesion = (SesionClase) obj;
            %>
            <tr>
                <td><%=sesion.getIdSesion() %></td>
                <td><%=sesion.getIdCurso() %></td>
                <td><%=sesion.getIdPeriodo() %></td>
                <td><%=sdf.format(sesion.getFecha()) %></td>
                <td><%=sesion.getTema() %></td>
                <td>
                    <a class="btn btn-sm btn-primary" href="asistencia?accion=verPorSesion&idSesion=<%=sesion.getIdSesion() %>">
                        <i class="fas fa-list"></i> Ver Asistencias
                    </a>
                    <a class="btn btn-sm btn-success" href="asistencia?accion=registrar&idSesion=<%=sesion.getIdSesion() %>">
                        <i class="fas fa-check"></i> Registrar
                    </a>
                </td>
            </tr>
            <%
                    }
                }
            %>
        </tbody>
    </table>

    <%
            }
        }

        if (!hayDatos) {
    %>
        <div class="alert alert-info">
            <i class="fas fa-info-circle"></i> No hay sesiones registradas.
        </div>
    <% } %>
</div>

<!-- Modal Nueva Sesión -->
<div class="modal fade" id="modalNuevaSesion" tabindex="-1">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Nueva Sesión de Clase</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
            </div>
            <form method="POST" action="asistencia">
                <div class="modal-body">
                    <input type="hidden" name="accion" value="crearSesion">

                    <div class="mb-3">
                        <label for="idCurso" class="form-label">Código Curso:</label>
                        <input type="text" class="form-control" id="idCurso" name="idCurso" required>
                    </div>

                    <div class="mb-3">
                        <label for="idPeriodo" class="form-label">ID Periodo:</label>
                        <input type="number" class="form-control" id="idPeriodo" name="idPeriodo" required>
                    </div>

                    <div class="mb-3">
                        <label for="fecha" class="form-label">Fecha:</label>
                        <input type="date" class="form-control" id="fecha" name="fecha" required>
                    </div>

                    <div class="mb-3">
                        <label for="tema" class="form-label">Tema:</label>
                        <input type="text" class="form-control" id="tema" name="tema" required>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                    <button type="submit" class="btn btn-primary">Guardar</button>
                </div>
            </form>
        </div>
    </div>
</div>

</body>
</html>
