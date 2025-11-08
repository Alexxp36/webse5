package com.tecsup.demo.model.daos;

import com.tecsup.demo.model.entities.Asistencia;
import com.tecsup.demo.model.entities.AsistenciaDetallada;
import java.util.List;

public interface AsistenciaDao extends EntidadDao<Asistencia, Integer> {
    List<Asistencia> findBySesion(int idSesion);
    List<Asistencia> findByDetalle(int idDetalle);
    List<AsistenciaDetallada> findAllDetallado();
    List<AsistenciaDetallada> findDetalladoBySesion(int idSesion);
}
