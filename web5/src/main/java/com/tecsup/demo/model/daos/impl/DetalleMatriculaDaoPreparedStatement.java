package com.tecsup.demo.model.daos.impl;

import com.tecsup.demo.model.daos.DetalleMatriculaDao;
import com.tecsup.demo.model.entities.DetalleMatricula;
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
}
