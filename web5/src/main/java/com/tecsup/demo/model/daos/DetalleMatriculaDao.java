package com.tecsup.demo.model.daos;

import com.tecsup.demo.model.entities.DetalleMatricula;
import com.tecsup.demo.model.entities.DetalleMatriculaDetallado;
import java.util.List;

public interface DetalleMatriculaDao extends EntidadDao<DetalleMatricula, Integer> {
    List<DetalleMatricula> findByMatricula(int idMatricula);
    List<DetalleMatricula> findByCurso(String idCurso);
    List<DetalleMatriculaDetallado> findDetalladoByMatricula(int idMatricula);
    List<DetalleMatriculaDetallado> findAllDetallado();
}
