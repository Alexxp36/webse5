package com.tecsup.demo.controllers;

import com.tecsup.demo.model.daos.impl.*;
import com.tecsup.demo.model.entities.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "NotaController", urlPatterns = {"/nota"})
public class NotaController extends HttpServlet {

    private NotaDaoPreparedStatement notaDao = new NotaDaoPreparedStatement();
    private EvaluacionDaoPreparedStatement evaluacionDao = new EvaluacionDaoPreparedStatement();
    private DetalleMatriculaDaoPreparedStatement detalleDao = new DetalleMatriculaDaoPreparedStatement();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");

        if (accion == null) {
            listarNotas(request, response);
        } else {
            switch (accion) {
                case "listar":
                    listarNotas(request, response);
                    break;
                case "registrar":
                    mostrarFormulario(request, response);
                    break;
                case "verPorAlumno":
                    verNotasPorAlumno(request, response);
                    break;
                case "verPorCurso":
                    verNotasPorCurso(request, response);
                    break;
                case "evaluaciones":
                    gestionarEvaluaciones(request, response);
                    break;
                default:
                    listarNotas(request, response);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");

        switch (accion) {
            case "insertar":
                insertarNota(request, response);
                break;
            case "actualizar":
                actualizarNota(request, response);
                break;
            case "crearEvaluacion":
                crearEvaluacion(request, response);
                break;
            case "eliminarEvaluacion":
                eliminarEvaluacion(request, response);
                break;
            default:
                listarNotas(request, response);
        }
    }

    private void listarNotas(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<NotaDetallada> notas = notaDao.findAllDetallado();
        request.setAttribute("notas", notas);
        request.getRequestDispatcher("/views/nota/listar.jsp").forward(request, response);
    }

    private void mostrarFormulario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idDetalle = request.getParameter("idDetalle");
        if (idDetalle != null) {
            DetalleMatricula detalle = detalleDao.find(Integer.parseInt(idDetalle));
            request.setAttribute("detalle", detalle);
        }

        List<Evaluacion> evaluaciones = evaluacionDao.findAll();
        request.setAttribute("evaluaciones", evaluaciones);
        request.getRequestDispatcher("/views/nota/formulario.jsp").forward(request, response);
    }

    private void insertarNota(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Nota nota = new Nota();
            nota.setIdDetalle(Integer.parseInt(request.getParameter("idDetalle")));
            nota.setIdEvaluacion(Integer.parseInt(request.getParameter("idEvaluacion")));
            nota.setNota(Double.parseDouble(request.getParameter("nota")));

            notaDao.create(nota);
            response.sendRedirect("nota?accion=listar");
        } catch (Exception e) {
            request.setAttribute("error", "Error al insertar nota: " + e.getMessage());
            mostrarFormulario(request, response);
        }
    }

    private void actualizarNota(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int idNota = Integer.parseInt(request.getParameter("idNota"));
            Nota nota = notaDao.find(idNota);

            if (nota != null) {
                nota.setNota(Double.parseDouble(request.getParameter("nota")));
                notaDao.update(nota);
            }

            response.sendRedirect("nota?accion=listar");
        } catch (Exception e) {
            request.setAttribute("error", "Error al actualizar nota: " + e.getMessage());
            listarNotas(request, response);
        }
    }

    private void verNotasPorAlumno(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int idDetalle = Integer.parseInt(request.getParameter("idDetalle"));
            List<Nota> notas = notaDao.findByDetalle(idDetalle);

            request.setAttribute("notas", notas);
            request.setAttribute("idDetalle", idDetalle);
            request.getRequestDispatcher("/views/nota/porAlumno.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", "Error al consultar notas: " + e.getMessage());
            listarNotas(request, response);
        }
    }

    private void verNotasPorCurso(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String idCurso = request.getParameter("idCurso");
            List<Evaluacion> evaluaciones = evaluacionDao.findByCurso(idCurso);

            request.setAttribute("evaluaciones", evaluaciones);
            request.setAttribute("idCurso", idCurso);
            request.getRequestDispatcher("/views/nota/porCurso.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", "Error al consultar notas: " + e.getMessage());
            listarNotas(request, response);
        }
    }

    private void gestionarEvaluaciones(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idCurso = request.getParameter("idCurso");
        List<Evaluacion> evaluaciones;

        if (idCurso != null && !idCurso.isEmpty()) {
            evaluaciones = evaluacionDao.findByCurso(idCurso);
        } else {
            evaluaciones = evaluacionDao.findAll();
        }

        request.setAttribute("evaluaciones", evaluaciones);
        request.getRequestDispatcher("/views/nota/evaluaciones.jsp").forward(request, response);
    }

    private void crearEvaluacion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Evaluacion evaluacion = new Evaluacion();
            evaluacion.setIdCurso(request.getParameter("idCurso"));
            evaluacion.setNombre(request.getParameter("nombre"));
            evaluacion.setPeso(Double.parseDouble(request.getParameter("peso")));

            evaluacionDao.create(evaluacion);
            response.sendRedirect("nota?accion=evaluaciones&idCurso=" + request.getParameter("idCurso"));
        } catch (Exception e) {
            request.setAttribute("error", "Error al crear evaluación: " + e.getMessage());
            gestionarEvaluaciones(request, response);
        }
    }

    private void eliminarEvaluacion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int idEvaluacion = Integer.parseInt(request.getParameter("idEvaluacion"));
            evaluacionDao.delete(idEvaluacion);
            response.sendRedirect("nota?accion=evaluaciones");
        } catch (Exception e) {
            response.sendRedirect("nota?accion=evaluaciones&error=" + e.getMessage());
        }
    }
}
