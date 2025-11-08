<%@ page import="com.tecsup.demo.model.entities.*" %>
<%@ page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Nueva Matrícula</title>
</head>
<body>
<jsp:include page="/master.jsp" />

<div class="container mt-4">
    <h1>Nueva Matrícula</h1>

    <% String error = (String) request.getAttribute("error");
       if (error != null) { %>
        <div class="alert alert-danger">
            <i class="fas fa-exclamation-triangle"></i> <%=error %>
        </div>
    <% } %>

    <form method="POST" action="matricula" class="needs-validation">
        <input type="hidden" name="accion" value="insertar">

        <div class="mb-3">
            <label for="idAlumno" class="form-label">Código del Alumno:</label>
            <input type="text" class="form-control" id="idAlumno" name="idAlumno" required>
        </div>

        <div class="mb-3">
            <label for="idPeriodo" class="form-label">Periodo Académico:</label>
            <select class="form-select" id="idPeriodo" name="idPeriodo" required>
                <option value="">Seleccione un periodo...</option>
                <%
                    List<PeriodoAcademico> periodos = (List<PeriodoAcademico>) request.getAttribute("periodos");
                    if (periodos != null) {
                        for (PeriodoAcademico periodo : periodos) {
                %>
                    <option value="<%=periodo.getIdPeriodo() %>">
                        <%=periodo.getNombrePeriodo() %> (<%=periodo.getEstado() %>)
                    </option>
                <%
                        }
                    }
                %>
            </select>
        </div>

        <div class="mb-3">
            <button type="submit" class="btn btn-primary">
                <i class="fas fa-save"></i> Registrar Matrícula
            </button>
            <a href="matricula?accion=listar" class="btn btn-secondary">
                <i class="fas fa-times"></i> Cancelar
            </a>
        </div>
    </form>
</div>

</body>
</html>
