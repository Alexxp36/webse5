<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
<style>
    body {
        padding-top: 80px;
    }
</style>

<nav class="navbar bg-primary navbar-expand-lg fixed-top">
    <div class="container">
        <a class="navbar-brand link-warning fs-3" href="principal.jsp">Stack</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarScroll"
                aria-controls="navbarScroll" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarScroll">
            <ol class="navbar-nav mx-auto my-2 my-lg-0 navbar-nav-scroll" style="--bs-scroll-height: 100px;">
                <li class="nav-item">
                    <a class="nav-link link-light" aria-current="page" href="/">Inicio</a>
                </li>

                <li class="nav-item dropdown">
                    <a class="nav-link link-light dropdown-toggle link-light" href="#" role="button" data-bs-toggle="dropdown"
                       aria-expanded="false">
                        Gestión Cursos
                    </a>
                    <ul class="dropdown-menu ">
                        <li><a class="dropdown-item" href="cursoMan.jsp">Cursos</a></li>
                        <li><a class="dropdown-item" href="#">Crédito</a></li>
                    </ul>
                </li>

                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle link-light" href="#" role="button" data-bs-toggle="dropdown"
                       aria-expanded="false">
                        <i class="fas fa-graduation-cap"></i> Matrículas
                    </a>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="matricula?accion=listar">Gestión de Matrículas</a></li>
                        <li><a class="dropdown-item" href="matricula?accion=nuevo">Nueva Matrícula</a></li>
                        <li><hr class="dropdown-divider"></li>
                        <li><a class="dropdown-item" href="#">Periodos Académicos</a></li>
                    </ul>
                </li>

                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle link-light" href="#" role="button" data-bs-toggle="dropdown"
                       aria-expanded="false">
                        <i class="fas fa-clipboard-list"></i> Notas
                    </a>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="nota?accion=listar">Gestión de Notas</a></li>
                        <li><a class="dropdown-item" href="nota?accion=registrar">Registrar Nota</a></li>
                        <li><hr class="dropdown-divider"></li>
                        <li><a class="dropdown-item" href="nota?accion=evaluaciones">Evaluaciones</a></li>
                    </ul>
                </li>

                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle link-light" href="#" role="button" data-bs-toggle="dropdown"
                       aria-expanded="false">
                        <i class="fas fa-calendar-check"></i> Asistencia
                    </a>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="asistencia?accion=listar">Gestión de Asistencias</a></li>
                        <li><a class="dropdown-item" href="asistencia?accion=sesiones">Sesiones de Clase</a></li>
                        <li><hr class="dropdown-divider"></li>
                        <li><a class="dropdown-item" href="asistencia?accion=nuevaSesion">Nueva Sesión</a></li>
                    </ul>
                </li>
            </ol>
        </div>

        <form class="d-flex" role="search">
            <input class="form-control me-2" type="search" placeholder="Buscar" aria-label="search">
            <button class="btn btn-outline-light" type="submit">Búsqueda</button>
        </form>
    </div>
</nav>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
