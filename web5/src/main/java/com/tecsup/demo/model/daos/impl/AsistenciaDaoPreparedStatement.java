package com.tecsup.demo.model.daos.impl;

import com.tecsup.demo.model.daos.AsistenciaDao;
import com.tecsup.demo.model.entities.Asistencia;
import com.tecsup.demo.model.entities.AsistenciaDetallada;
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

    @Override
    public List<AsistenciaDetallada> findAllDetallado() {
        List<AsistenciaDetallada> asistencias = new ArrayList<>();
        try {
            Connection con = DBConn.getConnection();
            String sql = "SELECT ast.idAsistencia, ast.idSesion, ast.idDetalle, ast.estado, " +
                        "sc.fecha, sc.tema, " +
                        "c.chrCurCodigo, c.vchCurNombre, " +
                        "a.chrAluCodigo, a.vchAluNombres, a.vchAluApellidos " +
                        "FROM Asistencia ast " +
                        "INNER JOIN SesionClase sc ON ast.idSesion = sc.idSesion " +
                        "INNER JOIN Curso c ON sc.idCurso = c.chrCurCodigo " +
                        "INNER JOIN DetalleMatricula dm ON ast.idDetalle = dm.idDetalle " +
                        "INNER JOIN Matricula m ON dm.idMatricula = m.idMatricula " +
                        "INNER JOIN Alumno a ON m.idAlumno = a.chrAluCodigo " +
                        "ORDER BY sc.fecha DESC, a.vchAluApellidos";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                AsistenciaDetallada asistencia = new AsistenciaDetallada();
                asistencia.setIdAsistencia(rs.getInt("idAsistencia"));
                asistencia.setIdSesion(rs.getInt("idSesion"));
                asistencia.setIdDetalle(rs.getInt("idDetalle"));
                asistencia.setEstado(rs.getString("estado"));
                asistencia.setFechaSesion(rs.getDate("fecha"));
                asistencia.setTemaSesion(rs.getString("tema"));
                asistencia.setCodigoCurso(rs.getString("chrCurCodigo"));
                asistencia.setNombreCurso(rs.getString("vchCurNombre"));
                asistencia.setCodigoAlumno(rs.getString("chrAluCodigo"));
                asistencia.setNombreAlumno(rs.getString("vchAluNombres"));
                asistencia.setApellidoAlumno(rs.getString("vchAluApellidos"));
                asistencias.add(asistencia);
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta: " + e.getMessage());
        }
        return asistencias;
    }

    @Override
    public List<AsistenciaDetallada> findDetalladoBySesion(int idSesion) {
        List<AsistenciaDetallada> asistencias = new ArrayList<>();
        try {
            Connection con = DBConn.getConnection();
            String sql = "SELECT ast.idAsistencia, ast.idSesion, ast.idDetalle, ast.estado, " +
                        "sc.fecha, sc.tema, " +
                        "c.chrCurCodigo, c.vchCurNombre, " +
                        "a.chrAluCodigo, a.vchAluNombres, a.vchAluApellidos " +
                        "FROM Asistencia ast " +
                        "INNER JOIN SesionClase sc ON ast.idSesion = sc.idSesion " +
                        "INNER JOIN Curso c ON sc.idCurso = c.chrCurCodigo " +
                        "INNER JOIN DetalleMatricula dm ON ast.idDetalle = dm.idDetalle " +
                        "INNER JOIN Matricula m ON dm.idMatricula = m.idMatricula " +
                        "INNER JOIN Alumno a ON m.idAlumno = a.chrAluCodigo " +
                        "WHERE ast.idSesion = ? " +
                        "ORDER BY a.vchAluApellidos, a.vchAluNombres";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, idSesion);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                AsistenciaDetallada asistencia = new AsistenciaDetallada();
                asistencia.setIdAsistencia(rs.getInt("idAsistencia"));
                asistencia.setIdSesion(rs.getInt("idSesion"));
                asistencia.setIdDetalle(rs.getInt("idDetalle"));
                asistencia.setEstado(rs.getString("estado"));
                asistencia.setFechaSesion(rs.getDate("fecha"));
                asistencia.setTemaSesion(rs.getString("tema"));
                asistencia.setCodigoCurso(rs.getString("chrCurCodigo"));
                asistencia.setNombreCurso(rs.getString("vchCurNombre"));
                asistencia.setCodigoAlumno(rs.getString("chrAluCodigo"));
                asistencia.setNombreAlumno(rs.getString("vchAluNombres"));
                asistencia.setApellidoAlumno(rs.getString("vchAluApellidos"));
                asistencias.add(asistencia);
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta: " + e.getMessage());
        }
        return asistencias;
    }
}
