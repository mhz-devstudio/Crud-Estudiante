package com.estudiante.controladorTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import com.estudiante.controlador.EstudianteControlador;
import com.estudiante.entidad.Estudiante;
import com.estudiante.servicio.EstudianteServicio;

class EstudianteControllerTest {

    @Mock
    private EstudianteServicio servicio;

    @Mock
    private Model modelo;

    @InjectMocks
    private EstudianteControlador controlador;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListarEstudiantes() {
        Estudiante estudiante1 = new Estudiante();
        estudiante1.setId(1L);
        estudiante1.setNombre("Juan");
        estudiante1.setApellido("Pérez");
        Estudiante estudiante2 = new Estudiante();
        estudiante2.setId(2L);
        estudiante2.setNombre("Ana");
        estudiante2.setApellido("Gómez");
        List<Estudiante> estudiantes = new ArrayList<>();
        when(servicio.listar()).thenReturn(estudiantes);

        String resultado = controlador.listar(modelo);
        assertEquals("estudiantes", resultado);
        verify(servicio).listar();
        verify(modelo).addAttribute("estudiantes", estudiantes);
    }

    @Test
    void testFormularioEstudiante() {
        Estudiante estudiante = new Estudiante();
        when(servicio.buscarById(1L)).thenReturn(estudiante);
        String resultado = controlador.formularioEditar(1L, modelo);

        assertEquals("editar_estudiante", resultado);
        verify(servicio).buscarById(1L);
        verify(modelo).addAttribute("estudiante", estudiante);
    }

    @Test
    void testGuardarEstudiante() {
        Estudiante estudiante = new Estudiante();
        estudiante.setId(1L);
        estudiante.setNombre("Juan");
        estudiante.setApellido("Pérez");
        estudiante.setEmail("jp@email.com");
        when(servicio.guardar(estudiante)).thenReturn(estudiante);
        String resultado = controlador.guardarEstudiante(estudiante);

        assertEquals("redirect:/estudiantes", resultado);
        verify(servicio).guardar(estudiante);
    }

    @Test
    void testFormularioEditar() {
        Long id = 1L;
        Estudiante estudiante = new Estudiante();
        estudiante.setId(id);
        estudiante.setNombre("Juan");
        when(servicio.buscarById(id)).thenReturn(estudiante);

        String resultado = controlador.formularioEditar(id, modelo);

        assertEquals("editar_estudiante", resultado);
        verify(servicio).buscarById(id);
        verify(modelo).addAttribute("estudiante", estudiante);
    }

     @Test
    void testFormEstudiante() {
        String resultado = controlador.formularioEstudiante(modelo);

        assertEquals("crear_estudiante", resultado);
        verify(modelo).addAttribute(eq("estudiante"), any(Estudiante.class));
    }

    @Test
    void testActualizarEstudiante() {
        Long id = 1L;
        Estudiante estudiante = new Estudiante();
        estudiante.setNombre("Juan");
        estudiante.setApellido("Pérez");
        estudiante.setEmail("juan@example.com");
        Estudiante estudianteExistente = new Estudiante();
        estudianteExistente.setId(id);
        when(servicio.buscarById(id)).thenReturn(estudianteExistente);
        when(servicio.actualizar(any(Estudiante.class))).thenReturn(estudianteExistente);

        String resultado = controlador.actualizarEstudiante(id, estudiante, modelo);

        assertEquals("redirect:/estudiantes", resultado);
        verify(servicio).buscarById(id);
        verify(servicio).actualizar(estudianteExistente);
    }

    @Test
    void testElminarEstudiante() {
        Long id = 1L;
        String resultado = controlador.eliminar(id);

        assertEquals("redirect:/estudiantes", resultado);
        verify(servicio).eliminar(id);
    }

}
