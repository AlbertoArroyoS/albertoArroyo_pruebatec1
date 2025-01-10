package org.example.persistencia.controlador;

import org.example.logica.Empleado;
import org.example.utils.Validaciones;
import org.example.persistencia.dao.EmpleadoJpaController;
import org.example.persistencia.exceptions.NonexistentEntityException;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Clase ControladoraPersistencia
 *
 * @author Alberto Arroyo Santofimia
 */
public class ControladoraPersistencia {

    EmpleadoJpaController empleadoJPA = new EmpleadoJpaController();
    Validaciones validaciones = new Validaciones();


    /**
     * Método para crear un empleado
     *
     * @param empleado que representa el empleado a dar de alta
     */
    public void crearEmpleado(Empleado empleado) {
        if (empleado != null) {
            empleadoJPA.create(empleado); // Crear el empleado en la base de datos
            System.out.println("Empleado añadido correctamente");
            System.out.println(empleado);
        } else {
            System.out.println("No se pudo añadir el empleado."); // Mensaje en caso de error
        }
    }

    /**
     * Método para borrar un empleado
     */
    public void borrarEmpleado() {
        // Validación del id como Long
        Long idEmpleado = validaciones.validarEntradaLong("Introduzca id del empleado a borrar");
        try {
            empleadoJPA.destroy(idEmpleado);
            System.out.println("El empleado con ID " + idEmpleado + " ha sido borrado exitosamente.");
        } catch (NonexistentEntityException ex) {
            // Mensaje al usuario en caso de que el ID no exista
            System.out.println("Error: No se encontró un empleado con el ID " + idEmpleado + ". Por favor, verifique e intente nuevamente.");
        } catch (Exception ex) {
            // Manejo genérico para cualquier otra excepción
            System.out.println("Ocurrió un error inesperado al intentar borrar el empleado.");
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, "Error inesperado", ex);
        }
    }

    /**
     * Método para traer todos los empleados
     *
     * @return lista de empleados
     */
    public List<Empleado> traerEmpleado() {
        return empleadoJPA.findEmpleadoEntities();
    }

    /**
     * Método para modificar un empleado
     *
     * @param empleado que representa el empleado a modificar
     */
    public void modificarEmpleado(Empleado empleado) {

        try {
            empleadoJPA.edit(empleado);
            System.out.println("Empleado modificado correctamente");
            System.out.println(empleado);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Método para traer empleados por tipo
     *
     * @param tipo que representa el tipo de empleado a filtrar
     * @return lista de empleados filtrados por tipo
     */
    public List<Empleado> traerEmpleadosPorTipo(String tipo) {
        try {
            // Usamos streams para filtrar la lista de empleados
            return empleadoJPA.findEmpleadoEntities()
                    .stream()
                    .filter(empleado -> empleado.getCargo() != null && empleado.getCargo().equalsIgnoreCase(tipo))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println("Error al obtener los empleados por tipo: " + e.getMessage());
            e.printStackTrace();
            return Collections.emptyList(); // Devolver una lista vacía en caso de error
        }
    }


    /**
     * Método para obtener un empleado por su ID
     *
     * @param id que representa el ID del empleado a buscar
     * @return empleado encontrado
     */
    public Empleado obtenerEmpleadoPorId(Long id) {

        Empleado empleadoPorId = null;
        try {
            empleadoPorId = empleadoJPA.findEmpleado(id);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
        return empleadoPorId;
    }

    /**
     * Método para comprobar si ya existe un empleado con el mismo nombre y apellido.
     * Los datos los recibimos en una ArrayList de empleados y comprobamos si ya existe un empleado con el mismo nombre y apellido.
     *
     * @param nombre   que representa el nombre del empleado
     * @param apellido que representa el apellido del empleado
     * @return true si ya existe un empleado con el mismo nombre y apellido, false si no existe
     */
    public boolean existeEmpleadoConNombreYApellido(String nombre, String apellido) {
        try {
            return empleadoJPA.findEmpleadoEntities()
                    .stream()
                    .anyMatch(e -> e.getNombre().equalsIgnoreCase(nombre) && e.getApellido().equalsIgnoreCase(apellido));
        } catch (Exception e) {
            System.out.println("Error al verificar si existe un empleado con el nombre y apellido especificados: " + e.getMessage());
            return false; // En caso de error, se asume que no existe
        }
    }

}
