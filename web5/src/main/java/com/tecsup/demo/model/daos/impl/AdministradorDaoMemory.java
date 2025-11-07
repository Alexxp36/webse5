package com.tecsup.demo.model.daos.impl;

import com.tecsup.demo.model.daos.AdministradorDao;
import com.tecsup.demo.model.entities.Administrador;

import java.util.ArrayList;
import java.util.List;

public class AdministradorDaoMemory implements AdministradorDao {

    private static List<Administrador> administradores = new ArrayList<>();

    static {
        Administrador adm1 = new Administrador();
        adm1.setCodigo("A0001");
        adm1.setLogin("admin");
        adm1.setPassword("admin");
        adm1.setNombres("Edwin");
        adm1.setApellidos("Maravi");

        Administrador adm2 = new Administrador();
        adm2.setCodigo("A0002");
        adm2.setLogin("user");
        adm2.setPassword("user");
        adm2.setNombres("Elvia");
        adm2.setApellidos("Rodriguez");

        administradores.add(adm1);
        administradores.add(adm2);
    }

    @Override
    public Administrador validar(String user, String password) {
        for (Administrador administrador : administradores) {
            if (administrador.getLogin().equals(user) && administrador.getPassword().equals(password)) {
                return administrador;
            }
        }
        return null;
    }
}
