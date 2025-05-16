package com.estudiante.servicio;

import java.util.List;

import com.estudiante.entidad.Estudiante;

public interface EstudianteServicio {

	public List<Estudiante> listar();

	public Estudiante guardar(Estudiante estudiante);

	public Estudiante buscarById(Long id);

	public Estudiante actualizar(Estudiante estudiante);

	public void eliminar(Long id);
}
