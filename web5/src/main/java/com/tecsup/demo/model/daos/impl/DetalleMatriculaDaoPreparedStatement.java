package com.tecsup.demo.model.daos.impl;

import com.tecsup.demo.model.daos.DetalleMatriculaDao;
import com.tecsup.demo.model.entities.DetalleMatricula;
import com.tecsup.demo.model.entities.DetalleMatriculaDetallado;
import com.tecsup.demo.util.DBConn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DetalleMatriculaDaoPreparedStatement implements DetalleMatriculaDao {

    @Override
    public void create(DetalleMatricula detalle) {
        try {
            Connection con = DBConn.getConnection();
            PreparedStatement pst = con.prepareStatement(
                "INSERT INTO DetalleMatricula (idMatricula, idCurso, estado) VALUES (?,?,?)"
            );
            pst.setInt(1, detalle.getIdMatricula());
            pst.setString(2, detalle.getIdCurso());
            pst.setString(3, detalle.getEstado());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error en la inserción: " + e.getMessage());
        }
    }

    @Override
    public DetalleMatricula find(Integer id) {
        DetalleMatricula detalle = null;
        try {
            Connection con = DBConn.getConnection();
            PreparedStatement pst = con.prepareStatement("SELECT * FROM DetalleMatricula WHERE idDetalle=?");
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                detalle = new DetalleMatricula();
                detalle.setIdDetalle(rs.getInt("idDetalle"));
                detalle.setIdMatricula(rs.getInt("idMatricula"));
                detalle.setIdCurso(rs.getString("idCurso"));
                detalle.setEstado(rs.getString("estado"));
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta: " + e.getMessage());
        }
        return detalle;
    }

    @Override
    public List<DetalleMatricula> findAll() {
        List<DetalleMatricula> detalles = new ArrayList<>();
        try {
            Connection con = DBConn.getConnection();
            PreparedStatement pst = con.prepareStatement("SELECT * FROM DetalleMatricula");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                DetalleMatricula detalle = new DetalleMatricula();
                detalle.setIdDetalle(rs.getInt("idDetalle"));
                detalle.setIdMatricula(rs.getInt("idMatricula"));
                detalle.setIdCurso(rs.getString("idCurso"));
                detalle.setEstado(rs.getString("estado"));
                detalles.add(detalle);
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta: " + e.getMessage());
        }
        return detalles;
    }

    @Override
    public void update(DetalleMatricula detalle) {
        try {
            Connection con = DBConn.getConnection();
            PreparedStatement pst = con.prepareStatement(
                "UPDATE DetalleMatricula SET idMatricula=?, idCurso=?, estado=? WHERE idDetalle=?"
            );
            pst.setInt(1, detalle.getIdMatricula());
            pst.setString(2, detalle.getIdCurso());
            pst.setString(3, detalle.getEstado());
            pst.setInt(4, detalle.getIdDetalle());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error en la actualización: " + e.getMessage());
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            Connection con = DBConn.getConnection();
            PreparedStatement pst = con.prepareStatement("DELETE FROM DetalleMatricula WHERE idDetalle=?");
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error en la eliminación: " + e.getMessage());
        }
    }

    @Override
    public List<DetalleMatricula> findByMatricula(int idMatricula) {
        List<DetalleMatricula> detalles = new ArrayList<>();
        try {
            Connection con = DBConn.getConnection();
            PreparedStatement pst = con.prepareStatement("SELECT * FROM DetalleMatricula WHERE idMatricula=?");
            pst.setInt(1, idMatricula);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                DetalleMatricula detalle = new DetalleMatricula();
                detalle.setIdDetalle(rs.getInt("idDetalle"));
                detalle.setIdMatricula(rs.getInt("idMatricula"));
                detalle.setIdCurso(rs.getString("idCurso"));
                detalle.setEstado(rs.getString("estado"));
                detalles.add(detalle);
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta: " + e.getMessage());
        }
        return detalles;
    }

    @Override
    public List<DetalleMatricula> findByCurso(String idCurso) {
        List<DetalleMatricula> detalles = new ArrayList<>();
        try {
            Connection con = DBConn.getConnection();
            PreparedStatement pst = con.prepareStatement("SELECT * FROM DetalleMatricula WHERE idCurso=?");
            pst.setString(1, idCurso);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                DetalleMatricula detalle = new DetalleMatricula();
                detalle.setIdDetalle(rs.getInt("idDetalle"));
                detalle.setIdMatricula(rs.getInt("idMatricula"));
                detalle.setIdCurso(rs.getString("idCurso"));
                detalle.setEstado(rs.getString("estado"));
                detalles.add(detalle);
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta: " + e.getMessage());
        }
        return detalles;
    }

    @Override
    public List<DetalleMatriculaDetallado> findDetalladoByMatricula(int idMatricula) {
        List<DetalleMatriculaDetallado> detalles = new ArrayList<>();
        try {
            Connection con = DBConn.getConnection();
            String sql = "SELECT dm.idDetalle, dm.idMatricula, dm.idCurso, c.vchCurNombre, " +
                        "c.intCurCreditos, dm.estado " +
                        "FROM DetalleMatricula dm " +
                        "INNER JOIN Curso c ON dm.idCurso = c.chrCurCodigo " +
                        "WHERE dm.idMatricula = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, idMatricula);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                DetalleMatriculaDetallado detalle = new DetalleMatriculaDetallado();
                detalle.setIdDetalle(rs.getInt("idDetalle"));
                detalle.setIdMatricula(rs.getInt("idMatricula"));
                detalle.setIdCurso(rs.getString("idCurso"));
                detalle.setNombreCurso(rs.getString("vchCurNombre"));
                detalle.setCreditos(rs.getInt("intCurCreditos"));
                detalle.setEstado(rs.getString("estado"));
                detalles.add(detalle);
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta: " + e.getMessage());
        }
        return detalles;
    }

    @Override
    public List<DetalleMatriculaDetallado> findAllDetallado() {
        List<DetalleMatriculaDetallado> detalles = new ArrayList<>();
        try {
            Connection con = DBConn.getConnection();
            String sql = "SELECT dm.idDetalle, dm.idMatricula, dm.idCurso, c.vchCurNombre, " +
                        "c.intCurCreditos, dm.estado, a.chrAluCodigo, a.vchAluNombres, a.vchAluApellidos " +
                        "FROM DetalleMatricula dm " +
                        "INNER JOIN Curso c ON dm.idCurso = c.chrCurCodigo " +
                        "INNER JOIN Matricula m ON dm.idMatricula = m.idMatricula " +
                        "INNER JOIN Alumno a ON m.idAlumno = a.chrAluCodigo " +
                        "WHERE dm.estado = 'matriculado' AND m.estado = 'activo' " +
                        "ORDER BY a.vchAluApellidos, a.vchAluNombres, c.vchCurNombre";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                DetalleMatriculaDetallado detalle = new DetalleMatriculaDetallado();
                detalle.setIdDetalle(rs.getInt("idDetalle"));
                detalle.setIdMatricula(rs.getInt("idMatricula"));
                detalle.setIdCurso(rs.getString("idCurso"));
                detalle.setNombreCurso(rs.getString("vchCurNombre"));
                detalle.setCreditos(rs.getInt("intCurCreditos"));
                detalle.setEstado(rs.getString("estado"));
                detalle.setIdAlumno(rs.getString("chrAluCodigo"));
                detalle.setNombreAlumno(rs.getString("vchAluNombres"));
                detalle.setApellidoAlumno(rs.getString("vchAluApellidos"));
                detalles.add(detalle);
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta: " + e.getMessage());
        }
        return detalles;
    }
}
