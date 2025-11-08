package com.tecsup.demo.model.daos.impl;

import com.tecsup.demo.model.daos.NotaDao;
import com.tecsup.demo.model.entities.Nota;
import com.tecsup.demo.model.entities.NotaDetallada;
import com.tecsup.demo.util.DBConn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NotaDaoPreparedStatement implements NotaDao {

    @Override
    public void create(Nota nota) {
        try {
            Connection con = DBConn.getConnection();
            PreparedStatement pst = con.prepareStatement(
                "INSERT INTO Nota (idDetalle, idEvaluacion, nota) VALUES (?,?,?)"
            );
            pst.setInt(1, nota.getIdDetalle());
            pst.setInt(2, nota.getIdEvaluacion());
            pst.setDouble(3, nota.getNota());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error en la inserción: " + e.getMessage());
        }
    }

    @Override
    public Nota find(Integer id) {
        Nota nota = null;
        try {
            Connection con = DBConn.getConnection();
            PreparedStatement pst = con.prepareStatement("SELECT * FROM Nota WHERE idNota=?");
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                nota = new Nota();
                nota.setIdNota(rs.getInt("idNota"));
                nota.setIdDetalle(rs.getInt("idDetalle"));
                nota.setIdEvaluacion(rs.getInt("idEvaluacion"));
                nota.setNota(rs.getDouble("nota"));
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta: " + e.getMessage());
        }
        return nota;
    }

    @Override
    public List<Nota> findAll() {
        List<Nota> notas = new ArrayList<>();
        try {
            Connection con = DBConn.getConnection();
            PreparedStatement pst = con.prepareStatement("SELECT * FROM Nota");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Nota nota = new Nota();
                nota.setIdNota(rs.getInt("idNota"));
                nota.setIdDetalle(rs.getInt("idDetalle"));
                nota.setIdEvaluacion(rs.getInt("idEvaluacion"));
                nota.setNota(rs.getDouble("nota"));
                notas.add(nota);
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta: " + e.getMessage());
        }
        return notas;
    }

    @Override
    public void update(Nota nota) {
        try {
            Connection con = DBConn.getConnection();
            PreparedStatement pst = con.prepareStatement(
                "UPDATE Nota SET idDetalle=?, idEvaluacion=?, nota=? WHERE idNota=?"
            );
            pst.setInt(1, nota.getIdDetalle());
            pst.setInt(2, nota.getIdEvaluacion());
            pst.setDouble(3, nota.getNota());
            pst.setInt(4, nota.getIdNota());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error en la actualización: " + e.getMessage());
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            Connection con = DBConn.getConnection();
            PreparedStatement pst = con.prepareStatement("DELETE FROM Nota WHERE idNota=?");
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error en la eliminación: " + e.getMessage());
        }
    }

    @Override
    public List<Nota> findByDetalle(int idDetalle) {
        List<Nota> notas = new ArrayList<>();
        try {
            Connection con = DBConn.getConnection();
            PreparedStatement pst = con.prepareStatement("SELECT * FROM Nota WHERE idDetalle=?");
            pst.setInt(1, idDetalle);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Nota nota = new Nota();
                nota.setIdNota(rs.getInt("idNota"));
                nota.setIdDetalle(rs.getInt("idDetalle"));
                nota.setIdEvaluacion(rs.getInt("idEvaluacion"));
                nota.setNota(rs.getDouble("nota"));
                notas.add(nota);
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta: " + e.getMessage());
        }
        return notas;
    }

    @Override
    public List<Nota> findByEvaluacion(int idEvaluacion) {
        List<Nota> notas = new ArrayList<>();
        try {
            Connection con = DBConn.getConnection();
            PreparedStatement pst = con.prepareStatement("SELECT * FROM Nota WHERE idEvaluacion=?");
            pst.setInt(1, idEvaluacion);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Nota nota = new Nota();
                nota.setIdNota(rs.getInt("idNota"));
                nota.setIdDetalle(rs.getInt("idDetalle"));
                nota.setIdEvaluacion(rs.getInt("idEvaluacion"));
                nota.setNota(rs.getDouble("nota"));
                notas.add(nota);
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta: " + e.getMessage());
        }
        return notas;
    }

    @Override
    public List<NotaDetallada> findAllDetallado() {
        List<NotaDetallada> notas = new ArrayList<>();
        try {
            Connection con = DBConn.getConnection();
            String sql = "SELECT n.idNota, n.idDetalle, n.idEvaluacion, n.nota, " +
                        "a.chrAluCodigo, a.vchAluNombres, a.vchAluApellidos, " +
                        "c.chrCurCodigo, c.vchCurNombre, " +
                        "e.nombre AS nombreEvaluacion, e.peso " +
                        "FROM Nota n " +
                        "INNER JOIN DetalleMatricula dm ON n.idDetalle = dm.idDetalle " +
                        "INNER JOIN Matricula m ON dm.idMatricula = m.idMatricula " +
                        "INNER JOIN Alumno a ON m.idAlumno = a.chrAluCodigo " +
                        "INNER JOIN Curso c ON dm.idCurso = c.chrCurCodigo " +
                        "INNER JOIN Evaluacion e ON n.idEvaluacion = e.idEvaluacion " +
                        "ORDER BY a.vchAluApellidos, a.vchAluNombres";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                NotaDetallada nota = new NotaDetallada();
                nota.setIdNota(rs.getInt("idNota"));
                nota.setIdDetalle(rs.getInt("idDetalle"));
                nota.setIdEvaluacion(rs.getInt("idEvaluacion"));
                nota.setNota(rs.getDouble("nota"));
                nota.setCodigoAlumno(rs.getString("chrAluCodigo"));
                nota.setNombreAlumno(rs.getString("vchAluNombres"));
                nota.setApellidoAlumno(rs.getString("vchAluApellidos"));
                nota.setCodigoCurso(rs.getString("chrCurCodigo"));
                nota.setNombreCurso(rs.getString("vchCurNombre"));
                nota.setNombreEvaluacion(rs.getString("nombreEvaluacion"));
                nota.setPesoEvaluacion(rs.getDouble("peso"));
                notas.add(nota);
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta: " + e.getMessage());
        }
        return notas;
    }
}
