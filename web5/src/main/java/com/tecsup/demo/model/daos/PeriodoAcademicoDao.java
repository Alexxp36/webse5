package com.tecsup.demo.model.daos;

import com.tecsup.demo.model.entities.PeriodoAcademico;
import java.util.List;

public interface PeriodoAcademicoDao extends EntidadDao<PeriodoAcademico, Integer> {
    List<PeriodoAcademico> findByEstado(String estado);
}
