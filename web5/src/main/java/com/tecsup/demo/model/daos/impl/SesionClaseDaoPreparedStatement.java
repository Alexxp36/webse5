package com.tecsup.demo.model.daos.impl;

import com.tecsup.demo.model.daos.SesionClaseDao;
import com.tecsup.demo.model.entities.SesionClase;
import com.tecsup.demo.util.DBConn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SesionClaseDaoPreparedStatement implements SesionClaseDao {

    @Override
    public void create(SesionClase sesion) {
        try {
            Connection con = DBConn.getConnection();
            PreparedStatement pst = con.prepareStatement(
                "INSERT INTO SesionClase (idCurso, idPeriodo, fecha, tema) VALUES (?,?,?,?)"
            );
            pst.setString(1, sesion.getIdCurso());
            pst.setInt(2, sesion.getIdPeriodo());
            pst.setDate(3, new java.sql.Date(sesion.getFecha().getTime()));
            pst.setString(4, sesion.getTema());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error en la inserción: " + e.getMessage());
        }
    }

    @Override
    public SesionClase find(Integer id) {
        SesionClase sesion = null;
        try {
            Connection con = DBConn.getConnection();
            PreparedStatement pst = con.prepareStatement("SELECT * FROM SesionClase WHERE idSesion=?");
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                sesion = new SesionClase();
                sesion.setIdSesion(rs.getInt("idSesion"));
                sesion.setIdCurso(rs.getString("idCurso"));
                sesion.setIdPeriodo(rs.getInt("idPeriodo"));
                sesion.setFecha(rs.getDate("fecha"));
                sesion.setTema(rs.getString("tema"));
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta: " + e.getMessage());
        }
        return sesion;
    }

    @Override
    public List<SesionClase> findAll() {
        List<SesionClase> sesiones = new ArrayList<>();
        try {
            Connection con = DBConn.getConnection();
            PreparedStatement pst = con.prepareStatement("SELECT * FROM SesionClase ORDER BY fecha DESC");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                SesionClase sesion = new SesionClase();
                sesion.setIdSesion(rs.getInt("idSesion"));
                sesion.setIdCurso(rs.getString("idCurso"));
                sesion.setIdPeriodo(rs.getInt("idPeriodo"));
                sesion.setFecha(rs.getDate("fecha"));
                sesion.setTema(rs.getString("tema"));
                sesiones.add(sesion);
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta: " + e.getMessage());
        }
        return sesiones;
    }

    @Override
    public void update(SesionClase sesion) {
        try {
            Connection con = DBConn.getConnection();
            PreparedStatement pst = con.prepareStatement(
                "UPDATE SesionClase SET idCurso=?, idPeriodo=?, fecha=?, tema=? WHERE idSesion=?"
            );
            pst.setString(1, sesion.getIdCurso());
            pst.setInt(2, sesion.getIdPeriodo());
            pst.setDate(3, new java.sql.Date(sesion.getFecha().getTime()));
            pst.setString(4, sesion.getTema());
            pst.setInt(5, sesion.getIdSesion());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error en la actualización: " + e.getMessage());
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            Connection con = DBConn.getConnection();
            PreparedStatement pst = con.prepareStatement("DELETE FROM SesionClase WHERE idSesion=?");
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error en la eliminación: " + e.getMessage());
        }
    }

    @Override
    public List<SesionClase> findByCurso(String idCurso) {
        List<SesionClase> sesiones = new ArrayList<>();
        try {
            Connection con = DBConn.getConnection();
            PreparedStatement pst = con.prepareStatement("SELECT * FROM SesionClase WHERE idCurso=? ORDER BY fecha");
            pst.setString(1, idCurso);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                SesionClase sesion = new SesionClase();
                sesion.setIdSesion(rs.getInt("idSesion"));
                sesion.setIdCurso(rs.getString("idCurso"));
                sesion.setIdPeriodo(rs.getInt("idPeriodo"));
                sesion.setFecha(rs.getDate("fecha"));
                sesion.setTema(rs.getString("tema"));
                sesiones.add(sesion);
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta: " + e.getMessage());
        }
        return sesiones;
    }

    @Override
    public List<SesionClase> findByPeriodo(int idPeriodo) {
        List<SesionClase> sesiones = new ArrayList<>();
        try {
            Connection con = DBConn.getConnection();
            PreparedStatement pst = con.prepareStatement("SELECT * FROM SesionClase WHERE idPeriodo=? ORDER BY fecha");
            pst.setInt(1, idPeriodo);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                SesionClase sesion = new SesionClase();
                sesion.setIdSesion(rs.getInt("idSesion"));
                sesion.setIdCurso(rs.getString("idCurso"));
                sesion.setIdPeriodo(rs.getInt("idPeriodo"));
                sesion.setFecha(rs.getDate("fecha"));
                sesion.setTema(rs.getString("tema"));
                sesiones.add(sesion);
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta: " + e.getMessage());
        }
        return sesiones;
    }
}
