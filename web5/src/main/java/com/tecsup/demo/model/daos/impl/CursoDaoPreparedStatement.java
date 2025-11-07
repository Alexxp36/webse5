package com.tecsup.demo.model.daos.impl;

import com.tecsup.demo.model.daos.CursoDao;
import com.tecsup.demo.model.entities.Curso;
import com.tecsup.demo.util.DBConn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CursoDaoPreparedStatement implements CursoDao {

    private Connection conn;
    private ResultSet rs;
    private PreparedStatement pst;

    @Override
    public void create(Curso curso) {
        try{
            Connection con = DBConn.getConnection();
            PreparedStatement pst = con.prepareStatement("INSERT INTO curso VALUES(?,?,?)");
            pst.setString(1, curso.getCodigo());
            pst.setString(2, curso.getNombre());
            pst.setInt(3, curso.getCreditos());
            pst.executeUpdate();
        }catch(SQLException e){
            System.out.println("Error en la inserción");
        }
    }

    @Override
    public Curso find(String id) {
        Curso curso = null;
        try{
            Connection con = DBConn.getConnection();
            PreparedStatement pst = con.prepareStatement("SELECT * FROM curso WHERE chrCurCodigo=?");
            pst.setString(1, id);
            rs = pst.executeQuery();
            if(rs.next()){
                curso = new Curso(rs.getString("chrCurCodigo"), rs.getString("vchCurNombre"),
                    rs.getInt("intCurCreditos"));
            }
        }catch(SQLException e){
            System.out.println("Error en la consulta");
        }
        return curso;
    }

    @Override
    public List<Curso> findAll() {
        List<Curso> cursos = new ArrayList<>();
        try{
            Connection con = DBConn.getConnection();
            PreparedStatement pst = con.prepareStatement("SELECT * FROM curso");
            rs = pst.executeQuery();
            while(rs.next()){
                cursos.add(new Curso(rs.getString("chrCurCodigo"), rs.getString("vchCurNombre"),
                    rs.getInt("intCurCreditos")));
            }
        }catch(SQLException e){
            System.out.println("Error en la consulta");
        }
        return cursos;
    }

    @Override
    public void update(Curso curso) {
        try{
            Connection con = DBConn.getConnection();
            PreparedStatement pst = con.prepareStatement("UPDATE curso SET vchCurNombre=?, intCurCreditos=? WHERE chrCurCodigo=?");
            pst.setString(1, curso.getNombre());
            pst.setInt(2, curso.getCreditos());
            pst.setString(3, curso.getCodigo());
            pst.executeUpdate();
        }catch(SQLException e){
            System.out.println("Error en la actualización");
        }
    }

    @Override
    public void delete(String id) {
        try{
            Connection con = DBConn.getConnection();
            PreparedStatement pst = con.prepareStatement("DELETE FROM curso WHERE chrCurCodigo=?");
            pst.setString(1, id);
            pst.executeUpdate();
        }catch(SQLException e){
            System.out.println("Error en la eliminación");
        }
    }

    @Override
    public List<Curso> findByRangeCreditos(int min, int max) {
        List<Curso> cursos = new ArrayList<>();
        try{
            Connection con = DBConn.getConnection();
            PreparedStatement pst = con.prepareStatement("SELECT * FROM curso WHERE intCurCreditos BETWEEN ? AND ?");
            pst.setInt(1, min);
            pst.setInt(2, max);
            rs = pst.executeQuery();
            while(rs.next()){
                cursos.add(new Curso(rs.getString("chrCurCodigo"), rs.getString("vchCurNombre"),
                    rs.getInt("intCurCreditos")));
            }
        }catch(SQLException e){
            System.out.println("Error en la consulta");
        }
        return cursos;
    }

    @Override
    public List<Curso> findByNombre(String nombre) {
        List<Curso> cursos = new ArrayList<>();
        try{
            Connection con = DBConn.getConnection();
            PreparedStatement pst = con.prepareStatement("SELECT * FROM curso WHERE vchCurNombre LIKE ?");
            pst.setString(1, "%" + nombre + "%");
            rs = pst.executeQuery();
            while(rs.next()){
                cursos.add(new Curso(rs.getString("chrCurCodigo"), rs.getString("vchCurNombre"),
                    rs.getInt("intCurCreditos")));
            }
        }catch(SQLException e){
            System.out.println("Error en la consulta");
        }
        return cursos;
    }
}
