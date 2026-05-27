package com.DAM.DAM1.Servicio;

import com.DAM.DAM1.Dominio.Empleado;
import com.DAM.DAM1.Repositorio.EmpleadoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders; // IMPORT ALTERNATIVO
import org.springframework.web.context.WebApplicationContext; // IMPORT ALTERNATIVO

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
public class EmpleadoIntegrationTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext; // Carga el contexto web automáticamente

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @BeforeEach
    void setUp() {
        empleadoRepository.deleteAll();
        // Esto inicializa MockMvc manualmente sin necesidad del `@AutoConfigureMockMvc` que te falla
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    void testFlujoCompletoCrearYConsultarEmpleado() throws Exception {
        String empleadoJson = "{\"nombre\":\"Luis\",\"email\":\"luis@empresa.com\",\"salario\":2200.0}";

        // 1. Simular POST
        mockMvc.perform(post("/api/empleados")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(empleadoJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.nombre").value("Luis"));

        Empleado empleadoGuardado = empleadoRepository.findAll().get(0);
        Long idGenerado = empleadoGuardado.getId();

        // 2. Simular GET
        mockMvc.perform(get("/api/empleados/" + idGenerado))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("luis@empresa.com"));
    }
}