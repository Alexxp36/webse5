package com.tecsup.demo.model.daos.impl;

import com.tecsup.demo.model.daos.PeriodoAcademicoDao;
import com.tecsup.demo.model.entities.PeriodoAcademico;
import com.tecsup.demo.util.DBConn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PeriodoAcademicoDaoPreparedStatement implements PeriodoAcademicoDao {

    @Override
    public void create(PeriodoAcademico periodo) {
        try {
            Connection con = DBConn.getConnection();
            PreparedStatement pst = con.prepareStatement(
                "INSERT INTO PeriodoAcademico (nombrePeriodo, fechaInicio, fechaFin, estado) VALUES (?,?,?,?)"
            );
            pst.setString(1, periodo.getNombrePeriodo());
            pst.setDate(2, new java.sql.Date(periodo.getFechaInicio().getTime()));
            pst.setDate(3, new java.sql.Date(periodo.getFechaFin().getTime()));
            pst.setString(4, periodo.getEstado());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error en la inserción: " + e.getMessage());
        }
    }

    @Override
    public PeriodoAcademico find(Integer id) {
        PeriodoAcademico periodo = null;
        try {
            Connection con = DBConn.getConnection();
            PreparedStatement pst = con.prepareStatement("SELECT * FROM PeriodoAcademico WHERE idPeriodo=?");
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                periodo = new PeriodoAcademico();
                periodo.setIdPeriodo(rs.getInt("idPeriodo"));
                periodo.setNombrePeriodo(rs.getString("nombrePeriodo"));
                periodo.setFechaInicio(rs.getDate("fechaInicio"));
                periodo.setFechaFin(rs.getDate("fechaFin"));
                periodo.setEstado(rs.getString("estado"));
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta: " + e.getMessage());
        }
        return periodo;
    }

    @Override
    public List<PeriodoAcademico> findAll() {
        List<PeriodoAcademico> periodos = new ArrayList<>();
        try {
            Connection con = DBConn.getConnection();
            PreparedStatement pst = con.prepareStatement("SELECT * FROM PeriodoAcademico ORDER BY fechaInicio DESC");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                PeriodoAcademico periodo = new PeriodoAcademico();
                periodo.setIdPeriodo(rs.getInt("idPeriodo"));
                periodo.setNombrePeriodo(rs.getString("nombrePeriodo"));
                periodo.setFechaInicio(rs.getDate("fechaInicio"));
                periodo.setFechaFin(rs.getDate("fechaFin"));
                periodo.setEstado(rs.getString("estado"));
                periodos.add(periodo);
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta: " + e.getMessage());
        }
        return periodos;
    }

    @Override
    public void update(PeriodoAcademico periodo) {
        try {
            Connection con = DBConn.getConnection();
            PreparedStatement pst = con.prepareStatement(
                "UPDATE PeriodoAcademico SET nombrePeriodo=?, fechaInicio=?, fechaFin=?, estado=? WHERE idPeriodo=?"
            );
            pst.setString(1, periodo.getNombrePeriodo());
            pst.setDate(2, new java.sql.Date(periodo.getFechaInicio().getTime()));
            pst.setDate(3, new java.sql.Date(periodo.getFechaFin().getTime()));
            pst.setString(4, periodo.getEstado());
            pst.setInt(5, periodo.getIdPeriodo());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error en la actualización: " + e.getMessage());
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            Connection con = DBConn.getConnection();
            PreparedStatement pst = con.prepareStatement("DELETE FROM PeriodoAcademico WHERE idPeriodo=?");
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error en la eliminación: " + e.getMessage());
        }
    }

    @Override
    public List<PeriodoAcademico> findByEstado(String estado) {
        List<PeriodoAcademico> periodos = new ArrayList<>();
        try {
            Connection con = DBConn.getConnection();
            PreparedStatement pst = con.prepareStatement("SELECT * FROM PeriodoAcademico WHERE estado=? ORDER BY fechaInicio DESC");
            pst.setString(1, estado);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                PeriodoAcademico periodo = new PeriodoAcademico();
                periodo.setIdPeriodo(rs.getInt("idPeriodo"));
                periodo.setNombrePeriodo(rs.getString("nombrePeriodo"));
                periodo.setFechaInicio(rs.getDate("fechaInicio"));
                periodo.setFechaFin(rs.getDate("fechaFin"));
                periodo.setEstado(rs.getString("estado"));
                periodos.add(periodo);
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta: " + e.getMessage());
        }
        return periodos;
    }
}
