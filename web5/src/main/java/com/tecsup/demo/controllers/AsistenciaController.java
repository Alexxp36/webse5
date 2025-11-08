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

@WebServlet(name = "AsistenciaController", urlPatterns = {"/asistencia"})
public class AsistenciaController extends HttpServlet {

    private AsistenciaDaoPreparedStatement asistenciaDao = new AsistenciaDaoPreparedStatement();
    private SesionClaseDaoPreparedStatement sesionDao = new SesionClaseDaoPreparedStatement();
    private DetalleMatriculaDaoPreparedStatement detalleDao = new DetalleMatriculaDaoPreparedStatement();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");

        if (accion == null) {
            listarAsistencias(request, response);
        } else {
            switch (accion) {
                case "listar":
                    listarAsistencias(request, response);
                    break;
                case "registrar":
                    mostrarFormularioRegistro(request, response);
                    break;
                case "verPorSesion":
                    verPorSesion(request, response);
                    break;
                case "verPorAlumno":
                    verPorAlumno(request, response);
                    break;
                case "sesiones":
                    gestionarSesiones(request, response);
                    break;
                case "nuevaSesion":
                    mostrarFormularioSesion(request, response);
                    break;
                default:
                    listarAsistencias(request, response);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");

        switch (accion) {
            case "insertar":
                insertarAsistencia(request, response);
                break;
            case "actualizar":
                actualizarAsistencia(request, response);
                break;
            case "registrarMasivo":
                registrarAsistenciaMasiva(request, response);
                break;
            case "crearSesion":
                crearSesion(request, response);
                break;
            case "eliminarSesion":
                eliminarSesion(request, response);
                break;
            default:
                listarAsistencias(request, response);
        }
    }

    private void listarAsistencias(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<AsistenciaDetallada> asistencias = asistenciaDao.findAllDetallado();
        request.setAttribute("asistencias", asistencias);
        request.getRequestDispatcher("/views/asistencia/listar.jsp").forward(request, response);
    }

    private void mostrarFormularioRegistro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idSesion = request.getParameter("idSesion");
        if (idSesion != null) {
            SesionClase sesion = sesionDao.find(Integer.parseInt(idSesion));
            request.setAttribute("sesion", sesion);

            // Obtener alumnos matriculados en el curso
            List<DetalleMatricula> detalles = detalleDao.findByCurso(sesion.getIdCurso());
            request.setAttribute("alumnos", detalles);
        }

        request.getRequestDispatcher("/views/asistencia/formulario.jsp").forward(request, response);
    }

    private void insertarAsistencia(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Asistencia asistencia = new Asistencia();
            asistencia.setIdSesion(Integer.parseInt(request.getParameter("idSesion")));
            asistencia.setIdDetalle(Integer.parseInt(request.getParameter("idDetalle")));
            asistencia.setEstado(request.getParameter("estado"));

            asistenciaDao.create(asistencia);
            response.sendRedirect("asistencia?accion=verPorSesion&idSesion=" + request.getParameter("idSesion"));
        } catch (Exception e) {
            request.setAttribute("error", "Error al registrar asistencia: " + e.getMessage());
            mostrarFormularioRegistro(request, response);
        }
    }

    private void actualizarAsistencia(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int idAsistencia = Integer.parseInt(request.getParameter("idAsistencia"));
            Asistencia asistencia = asistenciaDao.find(idAsistencia);

            if (asistencia != null) {
                asistencia.setEstado(request.getParameter("estado"));
                asistenciaDao.update(asistencia);
            }

            response.sendRedirect("asistencia?accion=listar");
        } catch (Exception e) {
            request.setAttribute("error", "Error al actualizar asistencia: " + e.getMessage());
            listarAsistencias(request, response);
        }
    }

    private void registrarAsistenciaMasiva(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int idSesion = Integer.parseInt(request.getParameter("idSesion"));
            String[] idsDetalle = request.getParameterValues("idDetalle");
            String[] estados = request.getParameterValues("estado");

            if (idsDetalle != null && estados != null && idsDetalle.length == estados.length) {
                for (int i = 0; i < idsDetalle.length; i++) {
                    Asistencia asistencia = new Asistencia();
                    asistencia.setIdSesion(idSesion);
                    asistencia.setIdDetalle(Integer.parseInt(idsDetalle[i]));
                    asistencia.setEstado(estados[i]);
                    asistenciaDao.create(asistencia);
                }
            }

            response.sendRedirect("asistencia?accion=verPorSesion&idSesion=" + idSesion);
        } catch (Exception e) {
            request.setAttribute("error", "Error al registrar asistencias: " + e.getMessage());
            mostrarFormularioRegistro(request, response);
        }
    }

    private void verPorSesion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int idSesion = Integer.parseInt(request.getParameter("idSesion"));
            SesionClase sesion = sesionDao.find(idSesion);
            List<AsistenciaDetallada> asistencias = asistenciaDao.findDetalladoBySesion(idSesion);

            request.setAttribute("sesion", sesion);
            request.setAttribute("asistencias", asistencias);
            request.getRequestDispatcher("/views/asistencia/porSesion.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", "Error al consultar asistencias: " + e.getMessage());
            listarAsistencias(request, response);
        }
    }

    private void verPorAlumno(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int idDetalle = Integer.parseInt(request.getParameter("idDetalle"));
            List<Asistencia> asistencias = asistenciaDao.findByDetalle(idDetalle);

            request.setAttribute("asistencias", asistencias);
            request.setAttribute("idDetalle", idDetalle);
            request.getRequestDispatcher("/views/asistencia/porAlumno.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", "Error al consultar asistencias: " + e.getMessage());
            listarAsistencias(request, response);
        }
    }

    private void gestionarSesiones(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idCurso = request.getParameter("idCurso");

        if (idCurso != null && !idCurso.isEmpty()) {
            List<SesionClase> sesiones = sesionDao.findByCurso(idCurso);
            request.setAttribute("sesiones", sesiones);
        } else {
            List<SesionClaseDetallada> sesiones = sesionDao.findAllDetallado();
            request.setAttribute("sesiones", sesiones);
        }

        request.getRequestDispatcher("/views/asistencia/sesiones.jsp").forward(request, response);
    }

    private void mostrarFormularioSesion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/views/asistencia/formularioSesion.jsp").forward(request, response);
    }

    private void crearSesion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            SesionClase sesion = new SesionClase();
            sesion.setIdCurso(request.getParameter("idCurso"));
            sesion.setIdPeriodo(Integer.parseInt(request.getParameter("idPeriodo")));

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sesion.setFecha(sdf.parse(request.getParameter("fecha")));
            sesion.setTema(request.getParameter("tema"));

            sesionDao.create(sesion);
            response.sendRedirect("asistencia?accion=sesiones&idCurso=" + request.getParameter("idCurso"));
        } catch (Exception e) {
            request.setAttribute("error", "Error al crear sesión: " + e.getMessage());
            mostrarFormularioSesion(request, response);
        }
    }

    private void eliminarSesion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int idSesion = Integer.parseInt(request.getParameter("idSesion"));
            sesionDao.delete(idSesion);
            response.sendRedirect("asistencia?accion=sesiones");
        } catch (Exception e) {
            response.sendRedirect("asistencia?accion=sesiones&error=" + e.getMessage());
        }
    }
}
