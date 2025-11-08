<%@ page import="com.tecsup.demo.model.entities.*" %>
<%@ page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Gestión de Notas</title>
</head>
<body>
<jsp:include page="/master.jsp" />

<div class="container mt-4">
    <h1>Gestión de Notas</h1>

    <div class="mb-3">
        <a href="nota?accion=evaluaciones" class="btn btn-primary">
            <i class="fas fa-list"></i> Gestionar Evaluaciones
        </a>
        <a href="nota?accion=registrar" class="btn btn-success">
            <i class="fas fa-plus"></i> Registrar Nota
        </a>
    </div>

    <%
        List<Nota> notas = (List<Nota>) request.getAttribute("notas");
        if (notas != null && !notas.isEmpty()) {
    %>

    <table class="table table-striped table-hover">
        <thead class="table-dark">
            <tr>
                <th>ID</th>
                <th>ID DETALLE</th>
                <th>ID EVALUACIÓN</th>
                <th>NOTA</th>
                <th>ACCIONES</th>
            </tr>
        </thead>
        <tbody>
            <% for (Nota nota : notas) { %>
            <tr>
                <td><%=nota.getIdNota() %></td>
                <td><%=nota.getIdDetalle() %></td>
                <td><%=nota.getIdEvaluacion() %></td>
                <td><strong><%=String.format("%.2f", nota.getNota()) %></strong></td>
                <td>
                    <button class="btn btn-sm btn-primary" onclick="editarNota(<%=nota.getIdNota() %>, <%=nota.getNota() %>)">
                        <i class="fas fa-edit"></i> Editar
                    </button>
                </td>
            </tr>
            <% } %>
        </tbody>
    </table>

    <% } else { %>
        <div class="alert alert-info">
            <i class="fas fa-info-circle"></i> No hay notas registradas.
        </div>
    <% } %>
</div>

<script>
function editarNota(idNota, notaActual) {
    const nuevaNota = prompt('Ingrese la nueva nota:', notaActual);
    if (nuevaNota !== null && !isNaN(nuevaNota)) {
        const form = document.createElement('form');
        form.method = 'POST';
        form.action = 'nota';

        const accionInput = document.createElement('input');
        accionInput.type = 'hidden';
        accionInput.name = 'accion';
        accionInput.value = 'actualizar';

        const idInput = document.createElement('input');
        idInput.type = 'hidden';
        idInput.name = 'idNota';
        idInput.value = idNota;

        const notaInput = document.createElement('input');
        notaInput.type = 'hidden';
        notaInput.name = 'nota';
        notaInput.value = nuevaNota;

        form.appendChild(accionInput);
        form.appendChild(idInput);
        form.appendChild(notaInput);
        document.body.appendChild(form);
        form.submit();
    }
}
</script>

</body>
</html>
