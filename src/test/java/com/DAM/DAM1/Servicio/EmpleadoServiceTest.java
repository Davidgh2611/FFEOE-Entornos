package com.DAM.DAM1.Servicio;

import com.DAM.DAM1.Dominio.Empleado;
import com.DAM.DAM1.Repositorio.EmpleadoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

// CORREGIDO: Añadido 'public' para que el ejecutable del IDE lo reconozca siempre
public class EmpleadoServiceTest {

    @Mock
    private EmpleadoRepository empleadoRepository;

    @InjectMocks
    private EmpleadoService empleadoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Test 1: Guardar empleado exitosamente")
    void testGuardarEmpleadoExitoso() {
        // Arrange
        Empleado empleado = new Empleado(null, "Ana", "ana@empresa.com", 3000.0);
        Empleado empleadoGuardado = new Empleado(1L, "Ana", "ana@empresa.com", 3000.0);

        when(empleadoRepository.findByEmail("ana@empresa.com")).thenReturn(Optional.empty());
        when(empleadoRepository.save(empleado)).thenReturn(empleadoGuardado);

        // Act
        Empleado resultado = empleadoService.guardarEmpleado(empleado);

        // Assert
        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Ana", resultado.getNombre());
        verify(empleadoRepository, times(1)).save(empleado);
    }

    @Test
    @DisplayName("Test 2: Error al guardar empleado con Email Duplicado")
    void testGuardarEmpleadoEmailDuplicado() {
        // Arrange
        Empleado empleadoExistente = new Empleado(1L, "Ana", "ana@empresa.com", 3000.0);
        Empleado nuevoEmpleado = new Empleado(null, "Ana López", "ana@empresa.com", 3200.0);

        when(empleadoRepository.findByEmail("ana@empresa.com")).thenReturn(Optional.of(empleadoExistente));

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            empleadoService.guardarEmpleado(nuevoEmpleado);
        });

        assertEquals("El email ya está registrado", exception.getMessage());
        verify(empleadoRepository, never()).save(any(Empleado.class));
    }

    @Test
    @DisplayName("Test 3: Eliminar un empleado inexistente lanza Error")
    void testEliminarEmpleadoInexistente() {
        // Arrange
        Long idInexistente = 99L;
        when(empleadoRepository.existsById(idInexistente)).thenReturn(false);

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> {
            empleadoService.eliminarEmpleado(idInexistente);
        });

        assertEquals("Empleado no existe", exception.getMessage());
        verify(empleadoRepository, never()).deleteById(anyLong());
    }
}