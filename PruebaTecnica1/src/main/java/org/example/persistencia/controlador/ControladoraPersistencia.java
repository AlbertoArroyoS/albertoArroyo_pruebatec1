package org.example.persistencia.controlador;

import org.example.logica.Empleado;
import org.example.persistencia.dao.EmpleadoJpaController;
import org.example.persistencia.exceptions.NonexistentEntityException;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControladoraPersistencia {

    EmpleadoJpaController empleadoJPA = new EmpleadoJpaController();


    public void crearEmpleado(Empleado empleado) {
        empleadoJPA.create(empleado);
    }

    public void borrarEmpleado(Long id) {
        try {
            empleadoJPA.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Empleado> traerEmpleado() {
        return empleadoJPA.findEmpleadoEntities();
    }

    public void modificarEmpleado (Empleado empleado) {

        try {
            empleadoJPA.edit(empleado);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Metodo adicional para obtener proyectos por tipo
    public List<Empleado> traerEmpleadosPorTipo(String tipo) {
        List<Empleado> empleadosFiltrados = new ArrayList<>();
        try {
            // Obtener todos los proyectos
            List<Empleado> listaEmpleados = empleadoJPA.findEmpleadoEntities();

            // Recorrer la lista de proyectos y filtrar por el tipo
            for (Empleado empleado : listaEmpleados) {
                if (empleado.getCargo() != null && empleado.getCargo().equals(tipo)) {
                    empleadosFiltrados.add(empleado);
                }
            }
        }catch (Exception e) {
            System.out.println("Error al obtener los empleados por tipo: " + e.getMessage());
            e.printStackTrace();
        }
        return empleadosFiltrados;
    }


}
