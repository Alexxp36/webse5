package com.tecsup.demo.model.daos;

import com.tecsup.demo.model.entities.Matricula;
import java.util.List;

public interface MatriculaDao extends EntidadDao<Matricula, Integer> {
    List<Matricula> findByAlumno(String idAlumno);
    List<Matricula> findByPeriodo(int idPeriodo);
}
