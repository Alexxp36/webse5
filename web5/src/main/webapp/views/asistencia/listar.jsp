<%@ page import="com.tecsup.demo.model.entities.AsistenciaDetallada" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Gestión de Asistencias</title>
</head>
<body>
<jsp:include page="/master.jsp" />

<div class="container mt-4">
    <h1>Gestión de Asistencias</h1>

    <div class="mb-3">
        <a href="asistencia?accion=sesiones" class="btn btn-primary">
            <i class="fas fa-calendar"></i> Gestionar Sesiones
        </a>
    </div>

    <%
        List<AsistenciaDetallada> asistencias = (List<AsistenciaDetallada>) request.getAttribute("asistencias");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        if (asistencias != null && !asistencias.isEmpty()) {
    %>

    <table class="table table-striped table-hover">
        <thead class="table-dark">
            <tr>
                <th>ID</th>
                <th>ALUMNO</th>
                <th>CURSO</th>
                <th>SESIÓN</th>
                <th>FECHA</th>
                <th>ESTADO</th>
                <th>ACCIONES</th>
            </tr>
        </thead>
        <tbody>
            <% for (AsistenciaDetallada asistencia : asistencias) {
                String badgeColor = "secondary";
                if (asistencia.getEstado().equals("asistio")) badgeColor = "success";
                else if (asistencia.getEstado().equals("tardanza")) badgeColor = "warning";
                else if (asistencia.getEstado().equals("falta")) badgeColor = "danger";
                else if (asistencia.getEstado().equals("justificada")) badgeColor = "info";
            %>
            <tr>
                <td><%=asistencia.getIdAsistencia() %></td>
                <td>
                    <strong><%=asistencia.getNombreCompletoAlumno() %></strong><br>
                    <small class="text-muted"><%=asistencia.getCodigoAlumno() %></small>
                </td>
                <td>
                    <span class="badge bg-primary"><%=asistencia.getCodigoCurso() %></span><br>
                    <small><%=asistencia.getNombreCurso() %></small>
                </td>
                <td>
                    <small><%=asistencia.getTemaSesion() %></small>
                </td>
                <td><%=sdf.format(asistencia.getFechaSesion()) %></td>
                <td>
                    <span class="badge bg-<%=badgeColor%>">
                        <%=asistencia.getEstado().toUpperCase() %>
                    </span>
                </td>
                <td>
                    <button class="btn btn-sm btn-primary" onclick="cambiarEstado(<%=asistencia.getIdAsistencia() %>)">
                        <i class="fas fa-edit"></i> Cambiar Estado
                    </button>
                </td>
            </tr>
            <% } %>
        </tbody>
    </table>

    <% } else { %>
        <div class="alert alert-info">
            <i class="fas fa-info-circle"></i> No hay asistencias registradas.
        </div>
    <% } %>
</div>

<script>
function cambiarEstado(idAsistencia) {
    const nuevoEstado = prompt('Ingrese el nuevo estado (asistio/tardanza/falta/justificada):');
    const estadosValidos = ['asistio', 'tardanza', 'falta', 'justificada'];

    if (nuevoEstado && estadosValidos.includes(nuevoEstado.toLowerCase())) {
        const form = document.createElement('form');
        form.method = 'POST';
        form.action = 'asistencia';

        const accionInput = document.createElement('input');
        accionInput.type = 'hidden';
        accionInput.name = 'accion';
        accionInput.value = 'actualizar';

        const idInput = document.createElement('input');
        idInput.type = 'hidden';
        idInput.name = 'idAsistencia';
        idInput.value = idAsistencia;

        const estadoInput = document.createElement('input');
        estadoInput.type = 'hidden';
        estadoInput.name = 'estado';
        estadoInput.value = nuevoEstado.toLowerCase();

        form.appendChild(accionInput);
        form.appendChild(idInput);
        form.appendChild(estadoInput);
        document.body.appendChild(form);
        form.submit();
    } else if (nuevoEstado) {
        alert('Estado no válido. Use: asistio, tardanza, falta o justificada');
    }
}
</script>

</body>
</html>
