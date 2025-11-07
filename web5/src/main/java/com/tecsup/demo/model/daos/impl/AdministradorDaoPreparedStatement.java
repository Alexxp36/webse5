package com.tecsup.demo.model.daos.impl;

import com.tecsup.demo.model.daos.AdministradorDao;
import com.tecsup.demo.model.entities.Administrador;
import com.tecsup.demo.util.DBConn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdministradorDaoPreparedStatement implements AdministradorDao {

    private Connection conn;
    private ResultSet rs;
    private PreparedStatement pst;

    @Override
    public Administrador validar(String user, String password) {
        Administrador administrador = null;

        try{
            Connection con = DBConn.getConnection();
            pst = con.prepareStatement("SELECT * FROM Administrador WHERE chrAdmLogin=? AND chrAdmPassword=?");
            pst.setString(1, user);
            pst.setString(2, password);
            rs = pst.executeQuery();

            if(rs.next()){
                administrador = new Administrador();
                administrador.setCodigo(rs.getString("chrAdmCodigo"));
                administrador.setLogin(rs.getString("chrAdmLogin"));
                administrador.setPassword(rs.getString("chrAdmPassword"));
                administrador.setNombres(rs.getString("vchAdmNombres"));
                administrador.setApellidos(rs.getString("vchAdmApellidos"));
            }
        }catch(SQLException e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return administrador;
    }
}
