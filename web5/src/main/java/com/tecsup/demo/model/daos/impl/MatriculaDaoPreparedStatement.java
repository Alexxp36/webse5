package com.tecsup.demo.model.daos.impl;

import com.tecsup.demo.model.daos.MatriculaDao;
import com.tecsup.demo.model.entities.Matricula;
import com.tecsup.demo.model.entities.MatriculaDetallada;
import com.tecsup.demo.util.DBConn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MatriculaDaoPreparedStatement implements MatriculaDao {

    @Override
    public void create(Matricula matricula) {
        try {
            Connection con = DBConn.getConnection();
            PreparedStatement pst = con.prepareStatement(
                "INSERT INTO Matricula (idAlumno, idPeriodo, fechaMatricula, estado) VALUES (?,?,?,?)"
            );
            pst.setString(1, matricula.getIdAlumno());
            pst.setInt(2, matricula.getIdPeriodo());
            pst.setDate(3, new java.sql.Date(matricula.getFechaMatricula().getTime()));
            pst.setString(4, matricula.getEstado());
            pst.executeUpdate();
            System.out.println("Matrícula insertada correctamente");
        } catch (SQLException e) {
            System.out.println("Error en la inserción de matrícula: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Error al crear matrícula: " + e.getMessage(), e);
        }
    }

    @Override
    public Matricula find(Integer id) {
        Matricula matricula = null;
        try {
            Connection con = DBConn.getConnection();
            PreparedStatement pst = con.prepareStatement("SELECT * FROM Matricula WHERE idMatricula=?");
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                matricula = new Matricula();
                matricula.setIdMatricula(rs.getInt("idMatricula"));
                matricula.setIdAlumno(rs.getString("idAlumno"));
                matricula.setIdPeriodo(rs.getInt("idPeriodo"));
                matricula.setFechaMatricula(rs.getDate("fechaMatricula"));
                matricula.setEstado(rs.getString("estado"));
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta: " + e.getMessage());
        }
        return matricula;
    }

    @Override
    public List<Matricula> findAll() {
        List<Matricula> matriculas = new ArrayList<>();
        try {
            Connection con = DBConn.getConnection();
            PreparedStatement pst = con.prepareStatement("SELECT * FROM Matricula ORDER BY fechaMatricula DESC");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Matricula matricula = new Matricula();
                matricula.setIdMatricula(rs.getInt("idMatricula"));
                matricula.setIdAlumno(rs.getString("idAlumno"));
                matricula.setIdPeriodo(rs.getInt("idPeriodo"));
                matricula.setFechaMatricula(rs.getDate("fechaMatricula"));
                matricula.setEstado(rs.getString("estado"));
                matriculas.add(matricula);
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta: " + e.getMessage());
        }
        return matriculas;
    }

    @Override
    public void update(Matricula matricula) {
        try {
            Connection con = DBConn.getConnection();
            PreparedStatement pst = con.prepareStatement(
                "UPDATE Matricula SET idAlumno=?, idPeriodo=?, fechaMatricula=?, estado=? WHERE idMatricula=?"
            );
            pst.setString(1, matricula.getIdAlumno());
            pst.setInt(2, matricula.getIdPeriodo());
            pst.setDate(3, new java.sql.Date(matricula.getFechaMatricula().getTime()));
            pst.setString(4, matricula.getEstado());
            pst.setInt(5, matricula.getIdMatricula());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error en la actualización: " + e.getMessage());
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            Connection con = DBConn.getConnection();
            PreparedStatement pst = con.prepareStatement("DELETE FROM Matricula WHERE idMatricula=?");
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error en la eliminación: " + e.getMessage());
        }
    }

    @Override
    public List<Matricula> findByAlumno(String idAlumno) {
        List<Matricula> matriculas = new ArrayList<>();
        try {
            Connection con = DBConn.getConnection();
            PreparedStatement pst = con.prepareStatement("SELECT * FROM Matricula WHERE idAlumno=? ORDER BY fechaMatricula DESC");
            pst.setString(1, idAlumno);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Matricula matricula = new Matricula();
                matricula.setIdMatricula(rs.getInt("idMatricula"));
                matricula.setIdAlumno(rs.getString("idAlumno"));
                matricula.setIdPeriodo(rs.getInt("idPeriodo"));
                matricula.setFechaMatricula(rs.getDate("fechaMatricula"));
                matricula.setEstado(rs.getString("estado"));
                matriculas.add(matricula);
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta: " + e.getMessage());
        }
        return matriculas;
    }

    @Override
    public List<Matricula> findByPeriodo(int idPeriodo) {
        List<Matricula> matriculas = new ArrayList<>();
        try {
            Connection con = DBConn.getConnection();
            PreparedStatement pst = con.prepareStatement("SELECT * FROM Matricula WHERE idPeriodo=?");
            pst.setInt(1, idPeriodo);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Matricula matricula = new Matricula();
                matricula.setIdMatricula(rs.getInt("idMatricula"));
                matricula.setIdAlumno(rs.getString("idAlumno"));
                matricula.setIdPeriodo(rs.getInt("idPeriodo"));
                matricula.setFechaMatricula(rs.getDate("fechaMatricula"));
                matricula.setEstado(rs.getString("estado"));
                matriculas.add(matricula);
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta: " + e.getMessage());
        }
        return matriculas;
    }

    @Override
    public List<MatriculaDetallada> findAllDetallado() {
        List<MatriculaDetallada> matriculas = new ArrayList<>();
        try {
            Connection con = DBConn.getConnection();
            String sql = "SELECT m.idMatricula, m.idAlumno, a.vchAluNombres, a.vchAluApellidos, " +
                        "m.idPeriodo, p.nombrePeriodo, m.fechaMatricula, m.estado " +
                        "FROM Matricula m " +
                        "INNER JOIN Alumno a ON m.idAlumno = a.chrAluCodigo " +
                        "INNER JOIN PeriodoAcademico p ON m.idPeriodo = p.idPeriodo " +
                        "ORDER BY m.fechaMatricula DESC";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                MatriculaDetallada matricula = new MatriculaDetallada();
                matricula.setIdMatricula(rs.getInt("idMatricula"));
                matricula.setIdAlumno(rs.getString("idAlumno"));
                matricula.setNombreAlumno(rs.getString("vchAluNombres"));
                matricula.setApellidoAlumno(rs.getString("vchAluApellidos"));
                matricula.setIdPeriodo(rs.getInt("idPeriodo"));
                matricula.setNombrePeriodo(rs.getString("nombrePeriodo"));
                matricula.setFechaMatricula(rs.getDate("fechaMatricula"));
                matricula.setEstado(rs.getString("estado"));
                matriculas.add(matricula);
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta: " + e.getMessage());
        }
        return matriculas;
    }

    @Override
    public MatriculaDetallada findDetalladoById(int idMatricula) {
        MatriculaDetallada matricula = null;
        try {
            Connection con = DBConn.getConnection();
            String sql = "SELECT m.idMatricula, m.idAlumno, a.vchAluNombres, a.vchAluApellidos, " +
                        "m.idPeriodo, p.nombrePeriodo, m.fechaMatricula, m.estado " +
                        "FROM Matricula m " +
                        "INNER JOIN Alumno a ON m.idAlumno = a.chrAluCodigo " +
                        "INNER JOIN PeriodoAcademico p ON m.idPeriodo = p.idPeriodo " +
                        "WHERE m.idMatricula = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, idMatricula);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                matricula = new MatriculaDetallada();
                matricula.setIdMatricula(rs.getInt("idMatricula"));
                matricula.setIdAlumno(rs.getString("idAlumno"));
                matricula.setNombreAlumno(rs.getString("vchAluNombres"));
                matricula.setApellidoAlumno(rs.getString("vchAluApellidos"));
                matricula.setIdPeriodo(rs.getInt("idPeriodo"));
                matricula.setNombrePeriodo(rs.getString("nombrePeriodo"));
                matricula.setFechaMatricula(rs.getDate("fechaMatricula"));
                matricula.setEstado(rs.getString("estado"));
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta: " + e.getMessage());
        }
        return matricula;
    }
}
