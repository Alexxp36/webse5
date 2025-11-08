<%@ page import="com.tecsup.demo.model.entities.*" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Gestión de Matrículas</title>
</head>
<body>
<jsp:include page="/master.jsp" />

<div class="container mt-4">
    <h1>Gestión de Matrículas</h1>

    <div class="mb-3">
        <a href="matricula?accion=nuevo" class="btn btn-success">
            <i class="fas fa-plus"></i> Nueva Matrícula
        </a>
    </div>

    <%
        List<Matricula> matriculas = (List<Matricula>) request.getAttribute("matriculas");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        if (matriculas != null && !matriculas.isEmpty()) {
    %>

    <table class="table table-striped table-hover">
        <thead class="table-dark">
            <tr>
                <th>ID</th>
                <th>ID ALUMNO</th>
                <th>ID PERIODO</th>
                <th>FECHA MATRÍCULA</th>
                <th>ESTADO</th>
                <th>ACCIONES</th>
            </tr>
        </thead>
        <tbody>
            <% for (Matricula matricula : matriculas) { %>
            <tr>
                <td><%=matricula.getIdMatricula() %></td>
                <td><%=matricula.getIdAlumno() %></td>
                <td><%=matricula.getIdPeriodo() %></td>
                <td><%=sdf.format(matricula.getFechaMatricula()) %></td>
                <td>
                    <span class="badge bg-<%=matricula.getEstado().equals("activo") ? "success" : "secondary"%>">
                        <%=matricula.getEstado().toUpperCase() %>
                    </span>
                </td>
                <td>
                    <a class="btn btn-sm btn-primary" href="matricula?accion=ver&id=<%=matricula.getIdMatricula() %>">
                        <i class="fas fa-eye"></i> Ver Detalle
                    </a>
                    <% if (matricula.getEstado().equals("activo")) { %>
                    <button class="btn btn-sm btn-warning" onclick="confirmarCambioEstado(<%=matricula.getIdMatricula() %>, 'retirado')">
                        <i class="fas fa-user-times"></i> Retirar
                    </button>
                    <% } %>
                </td>
            </tr>
            <% } %>
        </tbody>
    </table>

    <% } else { %>
        <div class="alert alert-info">
            <i class="fas fa-info-circle"></i> No hay matrículas registradas.
        </div>
    <% } %>
</div>

<script>
function confirmarCambioEstado(idMatricula, nuevoEstado) {
    if (confirm('¿Está seguro de cambiar el estado de esta matrícula?')) {
        const form = document.createElement('form');
        form.method = 'POST';
        form.action = 'matricula';

        const accionInput = document.createElement('input');
        accionInput.type = 'hidden';
        accionInput.name = 'accion';
        accionInput.value = 'cambiarEstado';

        const idInput = document.createElement('input');
        idInput.type = 'hidden';
        idInput.name = 'idMatricula';
        idInput.value = idMatricula;

        const estadoInput = document.createElement('input');
        estadoInput.type = 'hidden';
        estadoInput.name = 'nuevoEstado';
        estadoInput.value = nuevoEstado;

        form.appendChild(accionInput);
        form.appendChild(idInput);
        form.appendChild(estadoInput);
        document.body.appendChild(form);
        form.submit();
    }
}
</script>

</body>
</html>
