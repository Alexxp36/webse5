package com.tecsup.demo.model.entities;

import java.io.Serializable;
import java.util.Date;

public class SesionClase implements Serializable {

    private int idSesion;
    private String idCurso;
    private int idPeriodo;
    private Date fecha;
    private String tema;

    public SesionClase() {
    }

    public SesionClase(int idSesion, String idCurso, int idPeriodo, Date fecha, String tema) {
        this.idSesion = idSesion;
        this.idCurso = idCurso;
        this.idPeriodo = idPeriodo;
        this.fecha = fecha;
        this.tema = tema;
    }

    public int getIdSesion() {
        return idSesion;
    }

    public void setIdSesion(int idSesion) {
        this.idSesion = idSesion;
    }

    public String getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(String idCurso) {
        this.idCurso = idCurso;
    }

    public int getIdPeriodo() {
        return idPeriodo;
    }

    public void setIdPeriodo(int idPeriodo) {
        this.idPeriodo = idPeriodo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    @Override
    public String toString() {
        return "SesionClase{" +
                "idSesion=" + idSesion +
                ", idCurso='" + idCurso + '\'' +
                ", idPeriodo=" + idPeriodo +
                ", fecha=" + fecha +
                ", tema='" + tema + '\'' +
                '}';
    }
}
