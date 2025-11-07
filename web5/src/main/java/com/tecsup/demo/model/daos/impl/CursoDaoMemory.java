package com.tecsup.demo.model.daos.impl;

import com.tecsup.demo.model.daos.CursoDao;
import com.tecsup.demo.model.entities.Curso;

import java.util.ArrayList;
import java.util.List;

public class CursoDaoMemory implements CursoDao {

    private static List<Curso> cursos = new ArrayList<>();

    static {
        cursos.add(new Curso("c01", "HTML 5", 5));
        cursos.add(new Curso("c02", "CSS 3", 5));
        cursos.add(new Curso("c03", "JavaScript", 5));
        cursos.add(new Curso("c04", "demo", 5));
    }

    @Override
    public void create(Curso curso) {
        cursos.add(curso);
    }

    @Override
    public Curso find(String id) {
        for (Curso curso : cursos) {
            if (curso.getCodigo().equals(id)) {
                return curso;
            }
        }
        return null;
    }

    @Override
    public List<Curso> findAll() {
        return cursos;
    }

    @Override
    public void update(Curso curso) {
        for (int i = 0; i < cursos.size(); i++) {
            if (cursos.get(i).getCodigo().equals(curso.getCodigo())) {
                cursos.set(i, curso);
                break;
            }
        }
    }

    @Override
    public void delete(String id) {
        cursos.removeIf(curso -> curso.getCodigo().equals(id));
    }

    @Override
    public List<Curso> findByRangeCreditos(int min, int max) {
        List<Curso> result = new ArrayList<>();
        for (Curso curso : cursos) {
            if (curso.getCreditos() >= min && curso.getCreditos() <= max) {
                result.add(curso);
            }
        }
        return result;
    }

    @Override
    public List<Curso> findByNombre(String nombre) {
        List<Curso> result = new ArrayList<>();
        for (Curso curso : cursos) {
            if (curso.getNombre().contains(nombre)) {
                result.add(curso);
            }
        }
        return result;
    }
}
