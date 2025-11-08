<%@ page import="com.tecsup.demo.model.entities.*" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Detalle de Matrícula</title>
</head>
<body>
<jsp:include page="/master.jsp" />

<div class="container mt-4">
    <%
        Matricula matricula = (Matricula) request.getAttribute("matricula");
        List<DetalleMatricula> detalles = (List<DetalleMatricula>) request.getAttribute("detalles");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        if (matricula != null) {
    %>

    <h1>Detalle de Matrícula #<%=matricula.getIdMatricula() %></h1>

    <div class="card mb-4">
        <div class="card-body">
            <p><strong>Alumno:</strong> <%=matricula.getIdAlumno() %></p>
            <p><strong>Periodo:</strong> <%=matricula.getIdPeriodo() %></p>
            <p><strong>Fecha Matrícula:</strong> <%=sdf.format(matricula.getFechaMatricula()) %></p>
            <p><strong>Estado:</strong>
                <span class="badge bg-<%=matricula.getEstado().equals("activo") ? "success" : "secondary"%>">
                    <%=matricula.getEstado().toUpperCase() %>
                </span>
            </p>
        </div>
    </div>

    <h3>Cursos Matriculados</h3>

    <% if (detalles != null && !detalles.isEmpty()) { %>
    <table class="table table-striped">
        <thead class="table-dark">
            <tr>
                <th>ID Detalle</th>
                <th>Código Curso</th>
                <th>Estado</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <% for (DetalleMatricula detalle : detalles) { %>
            <tr>
                <td><%=detalle.getIdDetalle() %></td>
                <td><%=detalle.getIdCurso() %></td>
                <td>
                    <span class="badge bg-<%=detalle.getEstado().equals("matriculado") ? "success" : "warning"%>">
                        <%=detalle.getEstado().toUpperCase() %>
                    </span>
                </td>
                <td>
                    <% if (detalle.getEstado().equals("matriculado")) { %>
                    <button class="btn btn-sm btn-warning" onclick="retirarCurso(<%=detalle.getIdDetalle() %>, <%=matricula.getIdMatricula() %>)">
                        <i class="fas fa-user-times"></i> Retirar
                    </button>
                    <% } %>
                    <a href="nota?accion=verPorAlumno&idDetalle=<%=detalle.getIdDetalle() %>" class="btn btn-sm btn-info">
                        <i class="fas fa-graduation-cap"></i> Ver Notas
                    </a>
                </td>
            </tr>
            <% } %>
        </tbody>
    </table>
    <% } else { %>
        <div class="alert alert-info">
            <i class="fas fa-info-circle"></i> No hay cursos matriculados aún.
        </div>
    <% } %>

    <div class="mt-3">
        <a href="matricula?accion=listar" class="btn btn-secondary">
            <i class="fas fa-arrow-left"></i> Volver
        </a>
    </div>

    <% } else { %>
        <div class="alert alert-danger">
            <i class="fas fa-exclamation-triangle"></i> Matrícula no encontrada.
        </div>
    <% } %>
</div>

<script>
function retirarCurso(idDetalle, idMatricula) {
    if (confirm('¿Está seguro de retirar este curso?')) {
        const form = document.createElement('form');
        form.method = 'POST';
        form.action = 'matricula';

        const accionInput = document.createElement('input');
        accionInput.type = 'hidden';
        accionInput.name = 'accion';
        accionInput.value = 'retirarCurso';

        const idDetalleInput = document.createElement('input');
        idDetalleInput.type = 'hidden';
        idDetalleInput.name = 'idDetalle';
        idDetalleInput.value = idDetalle;

        const idMatriculaInput = document.createElement('input');
        idMatriculaInput.type = 'hidden';
        idMatriculaInput.name = 'idMatricula';
        idMatriculaInput.value = idMatricula;

        form.appendChild(accionInput);
        form.appendChild(idDetalleInput);
        form.appendChild(idMatriculaInput);
        document.body.appendChild(form);
        form.submit();
    }
}
</script>

</body>
</html>
