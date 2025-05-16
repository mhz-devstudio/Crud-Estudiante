package com.estudiante.entidadTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import com.estudiante.entidad.Estudiante;

class EstudianteTest {

    @Test
    void testGettersAndSetters() {
        // Arrange
        Estudiante estudiante = new Estudiante();
        estudiante.setId(1L);
        estudiante.setNombre("Juan");
        estudiante.setApellido("Pérez");
        estudiante.setEmail("juan@example.com");

        // Act & Assert
        assertEquals(1L, estudiante.getId());
        assertEquals("Juan", estudiante.getNombre());
        assertEquals("Pérez", estudiante.getApellido());
        assertEquals("juan@example.com", estudiante.getEmail());
    }

    @Test
    void testConstructorWithParameters() {
        // Arrange
        Estudiante estudiante = new Estudiante(1L, "Juan", "Pérez", "juan@example.com");

        // Act & Assert
        assertEquals(1L, estudiante.getId());
        assertEquals("Juan", estudiante.getNombre());
        assertEquals("Pérez", estudiante.getApellido());
        assertEquals("juan@example.com", estudiante.getEmail());
    }

    @Test
    void testToString() {
        // Arrange
        Estudiante estudiante = new Estudiante();
        estudiante.setId(1L);
        estudiante.setNombre("Juan");
        estudiante.setApellido("Pérez");
        estudiante.setEmail("juan@example.com");

        // Act
        String resultado = estudiante.toString();

        // Assert
        assertEquals("Estudiante{id=1, nombre='Juan', apellido='Pérez', email='juan@example.com'}", resultado);
    }

}
