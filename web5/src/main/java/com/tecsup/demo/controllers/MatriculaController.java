package com.tecsup.demo.controllers;

import com.tecsup.demo.model.daos.impl.*;
import com.tecsup.demo.model.entities.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(name = "MatriculaController", urlPatterns = {"/matricula"})
public class MatriculaController extends HttpServlet {

    private MatriculaDaoPreparedStatement matriculaDao = new MatriculaDaoPreparedStatement();
    private DetalleMatriculaDaoPreparedStatement detalleDao = new DetalleMatriculaDaoPreparedStatement();
    private PeriodoAcademicoDaoPreparedStatement periodoDao = new PeriodoAcademicoDaoPreparedStatement();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");

        if (accion == null) {
            listarMatriculas(request, response);
        } else {
            switch (accion) {
                case "listar":
                    listarMatriculas(request, response);
                    break;
                case "nuevo":
                    mostrarFormulario(request, response);
                    break;
                case "ver":
                    verDetalle(request, response);
                    break;
                case "listarPorAlumno":
                    listarPorAlumno(request, response);
                    break;
                default:
                    listarMatriculas(request, response);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");

        switch (accion) {
            case "insertar":
                insertarMatricula(request, response);
                break;
            case "actualizar":
                actualizarMatricula(request, response);
                break;
            case "cambiarEstado":
                cambiarEstado(request, response);
                break;
            case "agregarCurso":
                agregarCurso(request, response);
                break;
            case "retirarCurso":
                retirarCurso(request, response);
                break;
            default:
                listarMatriculas(request, response);
        }
    }

    private void listarMatriculas(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Matricula> matriculas = matriculaDao.findAll();
        request.setAttribute("matriculas", matriculas);
        request.getRequestDispatcher("/views/matricula/listar.jsp").forward(request, response);
    }

    private void mostrarFormulario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<PeriodoAcademico> periodos = periodoDao.findByEstado("activo");
        request.setAttribute("periodos", periodos);
        request.getRequestDispatcher("/views/matricula/formulario.jsp").forward(request, response);
    }

    private void insertarMatricula(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Matricula matricula = new Matricula();
            matricula.setIdAlumno(request.getParameter("idAlumno"));
            matricula.setIdPeriodo(Integer.parseInt(request.getParameter("idPeriodo")));
            matricula.setFechaMatricula(new Date());
            matricula.setEstado("activo");

            matriculaDao.create(matricula);
            response.sendRedirect("matricula?accion=listar");
        } catch (Exception e) {
            request.setAttribute("error", "Error al insertar matrícula: " + e.getMessage());
            mostrarFormulario(request, response);
        }
    }

    private void actualizarMatricula(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int idMatricula = Integer.parseInt(request.getParameter("idMatricula"));
            Matricula matricula = matriculaDao.find(idMatricula);

            if (matricula != null) {
                matricula.setEstado(request.getParameter("estado"));
                matriculaDao.update(matricula);
            }

            response.sendRedirect("matricula?accion=listar");
        } catch (Exception e) {
            request.setAttribute("error", "Error al actualizar matrícula: " + e.getMessage());
            listarMatriculas(request, response);
        }
    }

    private void cambiarEstado(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int idMatricula = Integer.parseInt(request.getParameter("idMatricula"));
            String nuevoEstado = request.getParameter("nuevoEstado");

            Matricula matricula = matriculaDao.find(idMatricula);
            if (matricula != null) {
                matricula.setEstado(nuevoEstado);
                matriculaDao.update(matricula);
            }

            response.sendRedirect("matricula?accion=listar");
        } catch (Exception e) {
            response.sendRedirect("matricula?accion=listar&error=" + e.getMessage());
        }
    }

    private void agregarCurso(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            DetalleMatricula detalle = new DetalleMatricula();
            detalle.setIdMatricula(Integer.parseInt(request.getParameter("idMatricula")));
            detalle.setIdCurso(request.getParameter("idCurso"));
            detalle.setEstado("matriculado");

            detalleDao.create(detalle);
            response.sendRedirect("matricula?accion=ver&id=" + request.getParameter("idMatricula"));
        } catch (Exception e) {
            response.sendRedirect("matricula?accion=ver&id=" + request.getParameter("idMatricula") + "&error=" + e.getMessage());
        }
    }

    private void retirarCurso(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int idDetalle = Integer.parseInt(request.getParameter("idDetalle"));
            DetalleMatricula detalle = detalleDao.find(idDetalle);

            if (detalle != null) {
                detalle.setEstado("retirado");
                detalleDao.update(detalle);
            }

            response.sendRedirect("matricula?accion=ver&id=" + request.getParameter("idMatricula"));
        } catch (Exception e) {
            response.sendRedirect("matricula?accion=listar&error=" + e.getMessage());
        }
    }

    private void verDetalle(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int idMatricula = Integer.parseInt(request.getParameter("id"));
            Matricula matricula = matriculaDao.find(idMatricula);
            List<DetalleMatricula> detalles = detalleDao.findByMatricula(idMatricula);

            request.setAttribute("matricula", matricula);
            request.setAttribute("detalles", detalles);
            request.getRequestDispatcher("/views/matricula/detalle.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", "Error al ver detalle: " + e.getMessage());
            listarMatriculas(request, response);
        }
    }

    private void listarPorAlumno(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idAlumno = request.getParameter("idAlumno");
        List<Matricula> matriculas = matriculaDao.findByAlumno(idAlumno);
        request.setAttribute("matriculas", matriculas);
        request.setAttribute("idAlumno", idAlumno);
        request.getRequestDispatcher("/views/matricula/listarPorAlumno.jsp").forward(request, response);
    }
}
