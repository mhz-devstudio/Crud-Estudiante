package com.estudiante.servicioTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.estudiante.entidad.Estudiante;
import com.estudiante.repositorio.EstudianteRepositorio;
import com.estudiante.servicio.EstudianteServicioImp;

class EstudianteServiceTest {

    @Mock
    private EstudianteRepositorio repositorio;

    @InjectMocks
    private EstudianteServicioImp servicio;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListar() {
        Estudiante estudiante = new Estudiante();
        estudiante.setId(1L);
        estudiante.setNombre("Juan");
        Estudiante estudiante1 = new Estudiante();
        estudiante1.setId(2L);
        estudiante1.setNombre("Ana");
        Estudiante estudiante2 = new Estudiante();
        estudiante2.setId(3L);
        estudiante2.setNombre("Luis");
        List<Estudiante> estudiantes = Arrays.asList(estudiante, estudiante1, estudiante2);
        when(repositorio.findAll()).thenReturn(estudiantes);

        List<Estudiante> resultado = servicio.listar();
        assertEquals(3, resultado.size());
        assertEquals("Juan", resultado.get(0).getNombre());
        assertEquals("Ana", resultado.get(1).getNombre());
        assertEquals("Luis", resultado.get(2).getNombre());
        verify(repositorio).findAll();
    }

    @Test
    void testGuardar() {
        Estudiante estudiante = new Estudiante();
        estudiante.setId(1L);
        estudiante.setNombre("Juan");
        when(repositorio.save(any(Estudiante.class))).thenReturn(estudiante);

        Estudiante resultado = servicio.guardar(estudiante);

        assertEquals(estudiante, resultado);
        assertEquals("Juan", resultado.getNombre());
        verify(repositorio).save(estudiante);
    }

    @Test
    void testBuscarByIdCuandoExiste() {
        Long id = 1L;
        Estudiante estudiante = new Estudiante();
        estudiante.setId(id);
        estudiante.setNombre("Juan");
        when(repositorio.findById(id)).thenReturn(Optional.of(estudiante));

        Estudiante resultado = servicio.buscarById(id);

        assertEquals(estudiante, resultado);
        assertEquals("Juan", resultado.getNombre());
        verify(repositorio).findById(id);
    }

    @Test
    void testBuscarByIdCuandoNoExiste() {
        Long id = 2L;
        when(repositorio.findById(id)).thenReturn(Optional.empty());

        Estudiante resultado = servicio.buscarById(id);

        assertNull(resultado);
        verify(repositorio).findById(id);
    }

    @Test
    void testActualizar() {
        Estudiante estudiante = new Estudiante();
        estudiante.setId(1L);
        estudiante.setNombre("Juan");
        when(repositorio.save(any(Estudiante.class))).thenReturn(estudiante);

        Estudiante resultado = servicio.actualizar(estudiante);

        assertEquals(estudiante, resultado);
        assertEquals("Juan", resultado.getNombre());
        verify(repositorio).save(estudiante);
    }

    @Test
    void testEliminar() {
        Long id = 1L;
        doNothing().when(repositorio).deleteById(id);

        servicio.eliminar(id);

        verify(repositorio).deleteById(id);
    }

}
