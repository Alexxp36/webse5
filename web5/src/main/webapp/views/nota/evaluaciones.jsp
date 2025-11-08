<%@ page import="com.tecsup.demo.model.entities.*" %>
<%@ page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Gestión de Evaluaciones</title>
</head>
<body>
<jsp:include page="/master.jsp" />

<div class="container mt-4">
    <h1>Gestión de Evaluaciones</h1>

    <div class="mb-3">
        <button class="btn btn-success" data-bs-toggle="modal" data-bs-target="#modalNuevaEvaluacion">
            <i class="fas fa-plus"></i> Nueva Evaluación
        </button>
        <a href="nota?accion=listar" class="btn btn-secondary">
            <i class="fas fa-arrow-left"></i> Volver
        </a>
    </div>

    <%
        List<Evaluacion> evaluaciones = (List<Evaluacion>) request.getAttribute("evaluaciones");
        if (evaluaciones != null && !evaluaciones.isEmpty()) {
    %>

    <table class="table table-striped table-hover">
        <thead class="table-dark">
            <tr>
                <th>ID</th>
                <th>CURSO</th>
                <th>NOMBRE</th>
                <th>PESO (%)</th>
                <th>ACCIONES</th>
            </tr>
        </thead>
        <tbody>
            <% for (Evaluacion eval : evaluaciones) { %>
            <tr>
                <td><%=eval.getIdEvaluacion() %></td>
                <td><%=eval.getIdCurso() %></td>
                <td><%=eval.getNombre() %></td>
                <td><%=String.format("%.2f", eval.getPeso()) %>%</td>
                <td>
                    <button class="btn btn-sm btn-danger" onclick="eliminarEvaluacion(<%=eval.getIdEvaluacion() %>)">
                        <i class="fas fa-trash"></i> Eliminar
                    </button>
                </td>
            </tr>
            <% } %>
        </tbody>
    </table>

    <% } else { %>
        <div class="alert alert-info">
            <i class="fas fa-info-circle"></i> No hay evaluaciones registradas.
        </div>
    <% } %>
</div>

<!-- Modal Nueva Evaluación -->
<div class="modal fade" id="modalNuevaEvaluacion" tabindex="-1">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Nueva Evaluación</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
            </div>
            <form method="POST" action="nota">
                <div class="modal-body">
                    <input type="hidden" name="accion" value="crearEvaluacion">

                    <div class="mb-3">
                        <label for="idCurso" class="form-label">Código Curso:</label>
                        <input type="text" class="form-control" id="idCurso" name="idCurso" required>
                    </div>

                    <div class="mb-3">
                        <label for="nombre" class="form-label">Nombre Evaluación:</label>
                        <input type="text" class="form-control" id="nombre" name="nombre" required>
                    </div>

                    <div class="mb-3">
                        <label for="peso" class="form-label">Peso (%):</label>
                        <input type="number" step="0.01" class="form-control" id="peso" name="peso" required>
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

<script>
function eliminarEvaluacion(idEvaluacion) {
    if (confirm('¿Está seguro de eliminar esta evaluación?')) {
        const form = document.createElement('form');
        form.method = 'POST';
        form.action = 'nota';

        const accionInput = document.createElement('input');
        accionInput.type = 'hidden';
        accionInput.name = 'accion';
        accionInput.value = 'eliminarEvaluacion';

        const idInput = document.createElement('input');
        idInput.type = 'hidden';
        idInput.name = 'idEvaluacion';
        idInput.value = idEvaluacion;

        form.appendChild(accionInput);
        form.appendChild(idInput);
        document.body.appendChild(form);
        form.submit();
    }
}
</script>

</body>
</html>
