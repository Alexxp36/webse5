package com.tecsup.demo.controllers;

import com.tecsup.demo.model.entities.Administrador;
import com.tecsup.demo.services.AdministradorService;
import com.tecsup.demo.services.impl.AdministradorServiceImpl;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AdministradorController", urlPatterns = {"/validador", "/admin"})
public class AdministradorController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String sUsuario = request.getParameter("txtUsuario");
        String sPassword = request.getParameter("txtPassword");

        AdministradorService servicio = new AdministradorServiceImpl();

        Administrador adm = servicio.validar(sUsuario, sPassword);

        if(adm!=null){
            request.getSession().setAttribute("eladministrador", adm);
            response.sendRedirect("principal.jsp");
        }else{
            response.sendRedirect("error.jsp");
        }
    }
}
