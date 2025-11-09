<%@ page import="com.tecsup.demo.model.entities.*" %>
<%@ page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Registrar Nota</title>
</head>
<body>
<jsp:include page="/master.jsp" />

<div class="container mt-4">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card">
                <div class="card-header bg-primary text-white">
                    <h4><i class="fas fa-plus-circle"></i> Registrar Nueva Nota</h4>
                </div>
                <div class="card-body">
                    <%
                        String error = (String) request.getAttribute("error");
                        if (error != null) {
                    %>
                        <div class="alert alert-danger alert-dismissible fade show" role="alert">
                            <i class="fas fa-exclamation-triangle"></i> <%=error %>
                            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                        </div>
                    <% } %>

                    <form method="POST" action="nota" id="formNota">
                        <input type="hidden" name="accion" value="insertar">

                        <div class="mb-3">
                            <label for="idDetalle" class="form-label">
                                <i class="fas fa-user-graduate"></i> ID Detalle Matrícula (Alumno-Curso):
                            </label>
                            <input type="number"
                                   class="form-control"
                                   id="idDetalle"
                                   name="idDetalle"
                                   placeholder="Ingrese el ID del detalle de matrícula"
                                   required>
                            <small class="form-text text-muted">
                                Este es el ID que relaciona un alumno con un curso específico en una matrícula.
                            </small>
                        </div>

                        <div class="mb-3">
                            <label for="idEvaluacion" class="form-label">
                                <i class="fas fa-clipboard-check"></i> Evaluación:
                            </label>
                            <%
                                List<Evaluacion> evaluaciones = (List<Evaluacion>) request.getAttribute("evaluaciones");
                                if (evaluaciones != null && !evaluaciones.isEmpty()) {
                            %>
                                <select class="form-select" id="idEvaluacion" name="idEvaluacion" required>
                                    <option value="">-- Seleccione una evaluación --</option>
                                    <% for (Evaluacion eval : evaluaciones) { %>
                                        <option value="<%=eval.getIdEvaluacion() %>">
                                            <%=eval.getNombre() %> - Curso: <%=eval.getIdCurso() %> (Peso: <%=String.format("%.0f%%", eval.getPeso()) %>)
                                        </option>
                                    <% } %>
                                </select>
                            <%
                                } else {
                            %>
                                <div class="alert alert-warning">
                                    <i class="fas fa-exclamation-circle"></i>
                                    No hay evaluaciones disponibles.
                                    <a href="nota?accion=evaluaciones">Crear evaluaciones primero</a>.
                                </div>
                            <% } %>
                        </div>

                        <div class="mb-3">
                            <label for="nota" class="form-label">
                                <i class="fas fa-star"></i> Nota:
                            </label>
                            <input type="number"
                                   class="form-control"
                                   id="nota"
                                   name="nota"
                                   step="0.01"
                                   min="0"
                                   max="20"
                                   placeholder="Ingrese la nota (0-20)"
                                   required>
                            <small class="form-text text-muted">
                                Ingrese un valor entre 0 y 20.
                            </small>
                        </div>

                        <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                            <a href="nota?accion=listar" class="btn btn-secondary">
                                <i class="fas fa-times"></i> Cancelar
                            </a>
                            <button type="submit" class="btn btn-primary">
                                <i class="fas fa-save"></i> Guardar Nota
                            </button>
                        </div>
                    </form>
                </div>
            </div>

            <!-- Ayuda adicional -->
            <div class="card mt-3">
                <div class="card-header bg-info text-white">
                    <h5><i class="fas fa-info-circle"></i> Ayuda</h5>
                </div>
                <div class="card-body">
                    <p><strong>¿Cómo obtener el ID Detalle Matrícula?</strong></p>
                    <ol>
                        <li>Ve a <a href="matricula?accion=listar">Matrículas</a></li>
                        <li>Selecciona "Ver Detalle" en la matrícula del alumno</li>
                        <li>Verás la tabla de cursos - cada fila tiene un ID Detalle</li>
                        <li>Usa ese ID aquí para registrar la nota del alumno en ese curso</li>
                    </ol>

                    <p class="mt-3"><strong>Recomendación:</strong></p>
                    <p>Para una experiencia más fluida, es mejor registrar notas desde la vista de matrículas o desde la gestión de evaluaciones por curso.</p>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
