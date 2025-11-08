package com.tecsup.demo.model.daos.impl;

import com.tecsup.demo.model.daos.AsistenciaDao;
import com.tecsup.demo.model.entities.Asistencia;
import com.tecsup.demo.util.DBConn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AsistenciaDaoPreparedStatement implements AsistenciaDao {

    @Override
    public void create(Asistencia asistencia) {
        try {
            Connection con = DBConn.getConnection();
            PreparedStatement pst = con.prepareStatement(
                "INSERT INTO Asistencia (idSesion, idDetalle, estado) VALUES (?,?,?)"
            );
            pst.setInt(1, asistencia.getIdSesion());
            pst.setInt(2, asistencia.getIdDetalle());
            pst.setString(3, asistencia.getEstado());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error en la inserción: " + e.getMessage());
        }
    }

    @Override
    public Asistencia find(Integer id) {
        Asistencia asistencia = null;
        try {
            Connection con = DBConn.getConnection();
            PreparedStatement pst = con.prepareStatement("SELECT * FROM Asistencia WHERE idAsistencia=?");
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                asistencia = new Asistencia();
                asistencia.setIdAsistencia(rs.getInt("idAsistencia"));
                asistencia.setIdSesion(rs.getInt("idSesion"));
                asistencia.setIdDetalle(rs.getInt("idDetalle"));
                asistencia.setEstado(rs.getString("estado"));
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta: " + e.getMessage());
        }
        return asistencia;
    }

    @Override
    public List<Asistencia> findAll() {
        List<Asistencia> asistencias = new ArrayList<>();
        try {
            Connection con = DBConn.getConnection();
            PreparedStatement pst = con.prepareStatement("SELECT * FROM Asistencia");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Asistencia asistencia = new Asistencia();
                asistencia.setIdAsistencia(rs.getInt("idAsistencia"));
                asistencia.setIdSesion(rs.getInt("idSesion"));
                asistencia.setIdDetalle(rs.getInt("idDetalle"));
                asistencia.setEstado(rs.getString("estado"));
                asistencias.add(asistencia);
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta: " + e.getMessage());
        }
        return asistencias;
    }

    @Override
    public void update(Asistencia asistencia) {
        try {
            Connection con = DBConn.getConnection();
            PreparedStatement pst = con.prepareStatement(
                "UPDATE Asistencia SET idSesion=?, idDetalle=?, estado=? WHERE idAsistencia=?"
            );
            pst.setInt(1, asistencia.getIdSesion());
            pst.setInt(2, asistencia.getIdDetalle());
            pst.setString(3, asistencia.getEstado());
            pst.setInt(4, asistencia.getIdAsistencia());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error en la actualización: " + e.getMessage());
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            Connection con = DBConn.getConnection();
            PreparedStatement pst = con.prepareStatement("DELETE FROM Asistencia WHERE idAsistencia=?");
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error en la eliminación: " + e.getMessage());
        }
    }

    @Override
    public List<Asistencia> findBySesion(int idSesion) {
        List<Asistencia> asistencias = new ArrayList<>();
        try {
            Connection con = DBConn.getConnection();
            PreparedStatement pst = con.prepareStatement("SELECT * FROM Asistencia WHERE idSesion=?");
            pst.setInt(1, idSesion);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Asistencia asistencia = new Asistencia();
                asistencia.setIdAsistencia(rs.getInt("idAsistencia"));
                asistencia.setIdSesion(rs.getInt("idSesion"));
                asistencia.setIdDetalle(rs.getInt("idDetalle"));
                asistencia.setEstado(rs.getString("estado"));
                asistencias.add(asistencia);
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta: " + e.getMessage());
        }
        return asistencias;
    }

    @Override
    public List<Asistencia> findByDetalle(int idDetalle) {
        List<Asistencia> asistencias = new ArrayList<>();
        try {
            Connection con = DBConn.getConnection();
            PreparedStatement pst = con.prepareStatement("SELECT * FROM Asistencia WHERE idDetalle=?");
            pst.setInt(1, idDetalle);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Asistencia asistencia = new Asistencia();
                asistencia.setIdAsistencia(rs.getInt("idAsistencia"));
                asistencia.setIdSesion(rs.getInt("idSesion"));
                asistencia.setIdDetalle(rs.getInt("idDetalle"));
                asistencia.setEstado(rs.getString("estado"));
                asistencias.add(asistencia);
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta: " + e.getMessage());
        }
        return asistencias;
    }
}
