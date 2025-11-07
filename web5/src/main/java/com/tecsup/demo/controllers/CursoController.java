package com.tecsup.demo.controllers;

import com.tecsup.demo.model.entities.Curso;
import com.tecsup.demo.services.CursoService;
import com.tecsup.demo.services.impl.CursoServiceImpl;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CursoController", urlPatterns = {"/cController", "/curso", "/cursoController"})
public class CursoController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Curso curso = new Curso();
        curso.setCodigo(request.getParameter("txtCodigo"));
        curso.setNombre(request.getParameter("txtNombre"));
        curso.setCreditos(Integer.parseInt(request.getParameter("txtCreditos")));

        System.out.println(curso);
        CursoService servicio = new CursoServiceImpl();

        String accion = request.getParameter("accion");
        switch(accion){
            case "insertar": servicio.grabar(curso);break;
            case "actualizar": servicio.actualizar(curso);break;
            case "eliminar": servicio.borrar(curso.getCodigo());break;
        }
        response.sendRedirect("cursoMan.jsp");
    }
}
