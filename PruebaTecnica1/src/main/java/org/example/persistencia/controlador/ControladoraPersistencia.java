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

public class ControladoraPersistencia {

    EmpleadoJpaController empleadoJPA = new EmpleadoJpaController();

    Scanner leer = new Scanner(System.in);


    public void crearEmpleado(Empleado empleado) {
        // Validación para el nombre
        String nombre = validacionEntradaTexto("Introduzca el nombre del empleado:");

        // Validación para el apellido
        String apellido = validacionEntradaTexto("Introduzca el apellido del empleado:");

        // Validación para el cargo
        String cargo = validacionEntradaTexto("Introduzca el cargo del empleado:");

        // Validación para el salario
        Double salario = validarEntradaDecimal("Introduzca el salario del empleado:");

        // Validación para la fecha de inicio (Formato: dd/mm/yyyy)
        Date fechaInicio = obtenerEntradaFecha("Introduzca la fecha de inicio del empleado (Formato: dd/mm/yyyy):");

        // Asignación de los valores al empleado
        empleado.setId(id);
        empleado.setNombre(nombre);
        empleado.setApellido(apellido);
        empleado.setCargo(cargo);
        empleado.setSalario(salario);
        empleado.setFechaInicio(fechaInicio);




        //**********************
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

    //METODOS DE VALIDACION DE ENTRADA DE DATOS

    //Metodo para validar una entrada de tipo String
    public String validacionEntradaTexto(String mensaje) {
        String entrada = "";
        do {
            System.out.println(mensaje);
            entrada = leer.nextLine();
            if (entrada.trim().isEmpty()) {
                System.out.println("*** El campo no puede estar vacío ni contener solo espacios en blanco ***");
            }
        } while (entrada.trim().isEmpty());
        return entrada;
    }

    // Metodo para validar una entrada decimal (como el salario)
    public Double validarEntradaDecimal(String mensaje) {
        Double entrada = 0.0;
        boolean entradaValida = false;
        while (!entradaValida) {
            try {
                System.out.println(mensaje);
                entrada = Double.parseDouble(leer.nextLine());
                entradaValida = true;
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Ingrese un valor numérico.");
            }
        }
        return entrada;
    }

    // Metodo para validar una entrada de fecha (Formato: dd/mm/yyyy)
    public Date obtenerEntradaFecha(String mensaje) {
        Date fecha = null;
        boolean entradaValida = false;
        while (!entradaValida) {
            try {
                System.out.println(mensaje);
                String fechaString = leer.nextLine();
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


}
