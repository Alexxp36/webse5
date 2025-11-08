package com.tecsup.demo.model.daos.impl;

import com.tecsup.demo.model.daos.EvaluacionDao;
import com.tecsup.demo.model.entities.Evaluacion;
import com.tecsup.demo.util.DBConn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EvaluacionDaoPreparedStatement implements EvaluacionDao {

    @Override
    public void create(Evaluacion evaluacion) {
        try {
            Connection con = DBConn.getConnection();
            PreparedStatement pst = con.prepareStatement(
                "INSERT INTO Evaluacion (idCurso, nombre, peso) VALUES (?,?,?)"
            );
            pst.setString(1, evaluacion.getIdCurso());
            pst.setString(2, evaluacion.getNombre());
            pst.setDouble(3, evaluacion.getPeso());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error en la inserción: " + e.getMessage());
        }
    }

    @Override
    public Evaluacion find(Integer id) {
        Evaluacion evaluacion = null;
        try {
            Connection con = DBConn.getConnection();
            PreparedStatement pst = con.prepareStatement("SELECT * FROM Evaluacion WHERE idEvaluacion=?");
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                evaluacion = new Evaluacion();
                evaluacion.setIdEvaluacion(rs.getInt("idEvaluacion"));
                evaluacion.setIdCurso(rs.getString("idCurso"));
                evaluacion.setNombre(rs.getString("nombre"));
                evaluacion.setPeso(rs.getDouble("peso"));
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta: " + e.getMessage());
        }
        return evaluacion;
    }

    @Override
    public List<Evaluacion> findAll() {
        List<Evaluacion> evaluaciones = new ArrayList<>();
        try {
            Connection con = DBConn.getConnection();
            PreparedStatement pst = con.prepareStatement("SELECT * FROM Evaluacion");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Evaluacion evaluacion = new Evaluacion();
                evaluacion.setIdEvaluacion(rs.getInt("idEvaluacion"));
                evaluacion.setIdCurso(rs.getString("idCurso"));
                evaluacion.setNombre(rs.getString("nombre"));
                evaluacion.setPeso(rs.getDouble("peso"));
                evaluaciones.add(evaluacion);
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta: " + e.getMessage());
        }
        return evaluaciones;
    }

    @Override
    public void update(Evaluacion evaluacion) {
        try {
            Connection con = DBConn.getConnection();
            PreparedStatement pst = con.prepareStatement(
                "UPDATE Evaluacion SET idCurso=?, nombre=?, peso=? WHERE idEvaluacion=?"
            );
            pst.setString(1, evaluacion.getIdCurso());
            pst.setString(2, evaluacion.getNombre());
            pst.setDouble(3, evaluacion.getPeso());
            pst.setInt(4, evaluacion.getIdEvaluacion());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error en la actualización: " + e.getMessage());
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            Connection con = DBConn.getConnection();
            PreparedStatement pst = con.prepareStatement("DELETE FROM Evaluacion WHERE idEvaluacion=?");
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error en la eliminación: " + e.getMessage());
        }
    }

    @Override
    public List<Evaluacion> findByCurso(String idCurso) {
        List<Evaluacion> evaluaciones = new ArrayList<>();
        try {
            Connection con = DBConn.getConnection();
            PreparedStatement pst = con.prepareStatement("SELECT * FROM Evaluacion WHERE idCurso=?");
            pst.setString(1, idCurso);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Evaluacion evaluacion = new Evaluacion();
                evaluacion.setIdEvaluacion(rs.getInt("idEvaluacion"));
                evaluacion.setIdCurso(rs.getString("idCurso"));
                evaluacion.setNombre(rs.getString("nombre"));
                evaluacion.setPeso(rs.getDouble("peso"));
                evaluaciones.add(evaluacion);
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta: " + e.getMessage());
        }
        return evaluaciones;
    }
}
