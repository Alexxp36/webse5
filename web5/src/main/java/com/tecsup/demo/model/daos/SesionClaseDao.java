package com.tecsup.demo.model.daos;

import com.tecsup.demo.model.entities.SesionClase;
import java.util.List;

public interface SesionClaseDao extends EntidadDao<SesionClase, Integer> {
    List<SesionClase> findByCurso(String idCurso);
    List<SesionClase> findByPeriodo(int idPeriodo);
}
