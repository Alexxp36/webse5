package com.tecsup.demo.model.daos;

import com.tecsup.demo.model.entities.Nota;
import java.util.List;

public interface NotaDao extends EntidadDao<Nota, Integer> {
    List<Nota> findByDetalle(int idDetalle);
    List<Nota> findByEvaluacion(int idEvaluacion);
}
