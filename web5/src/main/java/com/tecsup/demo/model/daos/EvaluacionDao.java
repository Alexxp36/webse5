package com.tecsup.demo.model.daos;

import com.tecsup.demo.model.entities.Evaluacion;
import java.util.List;

public interface EvaluacionDao extends EntidadDao<Evaluacion, Integer> {
    List<Evaluacion> findByCurso(String idCurso);
}
