package com.DAM.DAM1.Servicio;

import com.DAM.DAM1.Dominio.Empleado;
import com.DAM.DAM1.Repositorio.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    public Empleado guardarEmpleado(Empleado empleado) {
        // Lógica de negocio: No permitir emails duplicados
        Optional<Empleado> empleadoExistente = empleadoRepository.findByEmail(empleado.getEmail());
        if(empleadoExistente.isPresent()) {
            throw new IllegalArgumentException("El email ya está registrado");
        }
        return empleadoRepository.save(empleado);
    }

    public List<Empleado> obtenerTodos() {
        return empleadoRepository.findAll();
    }

    public Optional<Empleado> obtenerPorId(Long id) {
        return empleadoRepository.findById(id);
    }

    public Empleado actualizarEmpleado(Long id, Empleado datosNuevos) {
        return empleadoRepository.findById(id).map(empleado -> {
            empleado.setNombre(datosNuevos.getNombre());
            empleado.setSalario(datosNuevos.getSalario());
            return empleadoRepository.save(empleado);
        }).orElseThrow(() -> new RuntimeException("Empleado no encontrado"));
    }

    public void eliminarEmpleado(Long id) {
        if(!empleadoRepository.existsById(id)) {
            throw new RuntimeException("Empleado no existe");
        }
        empleadoRepository.deleteById(id);
    }
}