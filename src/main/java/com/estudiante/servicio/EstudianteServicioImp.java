package com.estudiante.servicio;

import java.util.List;

import org.springframework.stereotype.Service;

import com.estudiante.entidad.Estudiante;
import com.estudiante.repositorio.EstudianteRepositorio;

@Service
public class EstudianteServicioImp implements EstudianteServicio {

	private EstudianteRepositorio repositorio;

	EstudianteServicioImp(EstudianteRepositorio repositorio) {
		this.repositorio = repositorio;
	}

	@Override
	public List<Estudiante> listar() {
		return repositorio.findAll();
	}

	@Override
	public Estudiante guardar(Estudiante estudiante) {
		return repositorio.save(estudiante);
	}

	@Override
	public Estudiante buscarById(Long id) {
		return repositorio.findById(id).orElse(null);
	}

	@Override
	public Estudiante actualizar(Estudiante estudiante) {
		return repositorio.save(estudiante);
	}

	@Override
	public void eliminar(Long id) {
		repositorio.deleteById(id);

	}

}
