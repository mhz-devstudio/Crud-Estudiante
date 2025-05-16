package com.estudiante.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.estudiante.entidad.Estudiante;
import com.estudiante.servicio.EstudianteServicio;
import static com.estudiante.constantes.AppConstants.REDIRECT_ESTUDIANTES;
import static com.estudiante.constantes.AppConstants.ESTUDIANTES_ENDPOINT;

@Controller
public class EstudianteControlador {

	private EstudianteServicio servicio;

	public EstudianteControlador(EstudianteServicio servicio) {
		this.servicio = servicio;
	}

	@GetMapping(ESTUDIANTES_ENDPOINT)
	public String listar(Model model) {
		model.addAttribute("estudiantes", servicio.listar());
		return "estudiantes";
	}

	@GetMapping("/estudiantes/nuevo")
	public String formularioEstudiante(Model model) {
		Estudiante estudiante = new Estudiante();
		model.addAttribute("estudiante", estudiante);
		return "crear_estudiante";
	}

	@PostMapping(ESTUDIANTES_ENDPOINT)
	public String guardarEstudiante(@ModelAttribute Estudiante estudiante) {
		servicio.guardar(estudiante);
		return REDIRECT_ESTUDIANTES;
	}

	@GetMapping("/estudiantes/editar/{id}")
	public String formularioEditar(@PathVariable Long id, Model modelo) {
		modelo.addAttribute("estudiante", servicio.buscarById(id));
		return "editar_estudiante";
	}

	@PostMapping("/estudiantes/{id}")
	public String actualizarEstudiante(@PathVariable Long id, @ModelAttribute Estudiante estudiante,
			Model modelo) {
		Estudiante estudianteExistente = servicio.buscarById(id);
		estudianteExistente.setId(id);
		estudianteExistente.setNombre(estudiante.getNombre());
		estudianteExistente.setApellido(estudiante.getApellido());
		estudianteExistente.setEmail(estudiante.getEmail());

		servicio.actualizar(estudianteExistente);
		return REDIRECT_ESTUDIANTES;
	}

	@GetMapping("/estudiantes/{id}")
	public String eliminar(@PathVariable Long id) {
		servicio.eliminar(id);
		return REDIRECT_ESTUDIANTES;
	}
}
