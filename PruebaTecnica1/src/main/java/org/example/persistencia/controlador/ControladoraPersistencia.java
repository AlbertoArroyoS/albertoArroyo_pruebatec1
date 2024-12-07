package org.example.persistencia.controlador;

import org.example.logica.Empleado;
import org.example.persistencia.dao.EmpleadoJpaController;
import org.example.persistencia.exceptions.NonexistentEntityException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase ControladoraPersistencia
 *
 * @author Alberto Arroyo Santofimia
 */
public class ControladoraPersistencia {

    EmpleadoJpaController empleadoJPA = new EmpleadoJpaController();

    Scanner leer = new Scanner(System.in);

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
        Long idEmpleado = validarEntradaLong("Introduzca id del empleado a borrar");

        try {
            empleadoJPA.destroy(idEmpleado);
            System.out.println("El empleado con ID " + idEmpleado + " ha sido borrado exitosamente.");
        } catch (NonexistentEntityException ex) {
            // Mensaje al usuario en caso de que el ID no exista
            System.out.println("Error: No se encontró un empleado con el ID " + idEmpleado + ". Por favor, verifique e intente nuevamente.");
            //Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, "El empleado con ID " + idEmpleado + " no existe.", ex);
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
        List<Empleado> empleadosFiltrados = new ArrayList<>();
        try {
            // Obtener todos los empleados
            List<Empleado> listaEmpleados = empleadoJPA.findEmpleadoEntities();

            // Recorrer la lista de empleados y filtrar por el tipo
            for (Empleado empleado : listaEmpleados) {
                if (empleado.getCargo() != null && empleado.getCargo().equalsIgnoreCase(tipo)) {
                    empleadosFiltrados.add(empleado);
                }
            }
        } catch (Exception e) {
            System.out.println("Error al obtener los empleados por tipo: " + e.getMessage());
            e.printStackTrace();
        }
        return empleadosFiltrados;
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
        List<Empleado> listaEmpleados = empleadoJPA.findEmpleadoEntities();
        for (Empleado e : listaEmpleados) {
            if (e.getNombre().equalsIgnoreCase(nombre) && e.getApellido().equalsIgnoreCase(apellido)) {
                return true; // Encontrado un empleado con el mismo nombre y apellido
            }
        }
        return false; // No hay duplicados
    }
    //******* METODOS DE VALIDACION DE ENTRADA DE DATOS ***************

    /**
     * Método para validar una entrada de tipo String, no puede estar vacía ni contener solo espacios en blanco
     *
     * @param mensaje que representa el mensaje a mostrar al usuario
     * @return entrada de tipo String
     */
    public String validacionEntradaTexto(String mensaje) {
        String entrada = "";
        do {
            System.out.println(mensaje);
            entrada = leer.nextLine().trim();//trim para quitar los espacios al principio y final en caso de que los hayan puesto
            if (entrada.isEmpty()) {
                System.out.println("*** El campo no puede estar vacío ni contener solo espacios en blanco ***");
            }
        } while (entrada.trim().isEmpty());
        return entrada;
    }

    /**
     * Método para validar una entrada de tipo Double, solo se aceptan valores numéricos
     *
     * @param mensaje que representa el mensaje a mostrar al usuario
     * @return entrada de tipo Double
     */
    public Double validarEntradaDecimal(String mensaje) {
        Double entrada = 0.0;
        boolean entradaValida = false;
        while (!entradaValida) {
            try {
                System.out.println(mensaje);
                entrada = Double.parseDouble(leer.nextLine().trim());
                entradaValida = true;
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Ingrese un valor numérico.");
            }
        }
        return entrada;
    }

    /**
     * Método para validar una entrada de tipo Date, solo se aceptan valores en el formato dd/mm/yyyy
     *
     * @param mensaje que representa el mensaje a mostrar al usuario
     * @return entrada de tipo Date
     */
    public Date obtenerEntradaFecha(String mensaje) {
        Date fecha = null;
        boolean entradaValida = false;
        while (!entradaValida) {
            try {
                System.out.println(mensaje);
                String fechaString = leer.nextLine().trim();
                // Formato esperado: dd/mm/yyyy
                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                fecha = formato.parse(fechaString);
                entradaValida = true;
            } catch (ParseException e) {
                System.out.println("Fecha no válida. Asegúrese de ingresar la fecha en el formato dd/mm/yyyy.");
            }
        }
        return fecha;
    }

    /**
     * Método para validar una entrada de tipo Long, solo se aceptan valores numéricos enteros
     *
     * @param mensaje que representa el mensaje a mostrar al usuario
     * @return entrada de tipo Long
     */
    public Long validarEntradaLong(String mensaje) {
        Long entrada = 0L;
        boolean entradaValida = false;
        while (!entradaValida) {
            try {
                System.out.println(mensaje);
                entrada = Long.parseLong(leer.nextLine().trim()); // Convertir la entrada a long
                entradaValida = true; // Si no hay excepción, la entrada es válida
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Ingrese un valor numérico entero de tipo long."); // Manejo de error
            }
        }
        return entrada;
    }

    /**
     * Método para pedir los datos de un empleado al usuario, con validaciones de entrada
     *
     * @return empleado con los datos ingresados
     */
    public Empleado pedirDatosEmpleado() {
        // Validación para el nombre
        String nombre = validacionEntradaTexto("Introduzca el nombre del empleado:");

        // Validación para el apellido
        String apellido = validacionEntradaTexto("Introduzca el apellido del empleado:");

        // Verificar si ya existe un empleado con el mismo nombre y apellido
        if (existeEmpleadoConNombreYApellido(nombre, apellido)) {
            System.out.println("Ya existe un empleado con el mismo nombre y apellido. No se puede dar de alta.");
            return null; // Retornar null si el empleado ya existe
        }

        // Validación para el cargo
        String cargo = validacionEntradaTexto("Introduzca el cargo del empleado:");

        // Validación para el salario
        Double salario = validarEntradaDecimal("Introduzca el salario del empleado:");

        // Validación para la fecha de inicio (Formato: dd/mm/yyyy)
        Date fechaInicio = obtenerEntradaFecha("Introduzca la fecha de inicio del empleado (Formato: dd/mm/yyyy):");

        // Crear y devolver el empleado con los datos ingresados
        return new Empleado(nombre, apellido, cargo, salario, fechaInicio);
    }


}
