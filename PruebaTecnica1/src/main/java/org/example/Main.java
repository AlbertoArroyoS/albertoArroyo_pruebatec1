package org.example;

import org.example.logica.Empleado;
import org.example.persistencia.controlador.ControladoraPersistencia;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * Clase Main que contiene el método main para ejecutar la aplicación.
 * Se encarga de mostrar un menú de opciones para gestionar empleados.
 *
 * @author Alberto Arroyo Santofimia
 */
public class Main {

    private static Scanner leer;

    //Para poder leer las opciones del menu	que están en un metodo estatico
    static {
        leer = new Scanner(System.in);
    }

    public static void main(String[] args) {

        ControladoraPersistencia controlPersis = new ControladoraPersistencia();

        boolean continuar = true;
        do {
            //Carga el menu inicial y se recupera la opción elegida
            int opcion = menu();
            //Validacion para que se repita el menu en caso de que la opción esté fuera de rango 1-6 de tipo entero
            while (opcion < 1 || opcion > 6) {
                opcion = menu();
            }
            switch (opcion) {
                case 1:
                    // Opcion para Agregar un nuevo empleado
                    Empleado empleado = controlPersis.pedirDatosEmpleado();
                    controlPersis.crearEmpleado(empleado);
                    break;
                case 2:
                    // Opción para Listar todos los empleados
                    List<Empleado> listaEmpleados = controlPersis.traerEmpleado();
                    if (listaEmpleados.isEmpty()) {
                        System.out.println("No hay empleados registrados.");
                    } else {
                        System.out.println("----Lista de empleados----");
                        for (Empleado per : listaEmpleados) {
                            System.out.println(per.toString());
                        }
                    }
                    break;
                case 3:
                    // Opcion para Editar un empleado
                    Long idEmpleado = controlPersis.validarEntradaLong("Introduzca id del empleado a editar");
                    Empleado empleadoPorId = controlPersis.obtenerEmpleadoPorId(idEmpleado);
                    if (empleadoPorId == null) {
                        System.out.println("No se encontró ningún empleado con el id: " + idEmpleado);
                        break;
                    } else {
                        System.out.println("Datos del empleado con id: " + idEmpleado);
                        System.out.println(empleadoPorId);
                        boolean continuarEditando = true;
                        do {
                            //Carga el menu para Editar y se recupera la opción elegida
                            int opcionEditar = menuEditar();
                            //Validacion para que se repita el menu en caso de que la opción esté fuera de rango 1-6 de tipo entero
                            while (opcionEditar < 1 || opcionEditar > 7) {
                                opcion = menu();
                            }
                            switch (opcionEditar) {
                                case 1:
                                    // Opción para Editar nombre del empleado
                                    String nombre = controlPersis.validacionEntradaTexto("Introduzca el nombre del empleado:");
                                    empleadoPorId.setNombre(nombre);
                                    break;
                                case 2:
                                    // Opción para Editar apellido del empleado
                                    String apellido = controlPersis.validacionEntradaTexto("Introduzca el apellido del empleado:");
                                    empleadoPorId.setApellido(apellido);

                                    break;
                                case 3:
                                    // Opcción para Editar cargo del empleado
                                    String cargo = controlPersis.validacionEntradaTexto("Introduzca el cargo del empleado:");
                                    empleadoPorId.setCargo(cargo);

                                    break;
                                case 4:
                                    // Opcción para Editar salario del empleado
                                    Double salario = controlPersis.validarEntradaDecimal("Introduzca el salario del empleado:");
                                    empleadoPorId.setSalario(salario);

                                    break;
                                case 5:
                                    // Opcción para Editar fecha de inicio del empleado
                                    Date fechaInicio = controlPersis.obtenerEntradaFecha("Introduzca la fecha de inicio del empleado (Formato: dd/mm/yyyy):");
                                    empleadoPorId.setFechaInicio(fechaInicio);

                                    break;
                                case 6:
                                    // Opción para Guardar los cambios y volver al menú principal
                                    System.out.println("Guardando información...");
                                    if (empleadoPorId != null) {
                                        controlPersis.modificarEmpleado(empleadoPorId);
                                    }
                                    continuarEditando = false;
                                    break;
                                case 7:
                                    // Opción para Volver sin guardar los cambios
                                    System.out.println("Volver sin guardar...");
                                    continuarEditando = false;
                                    break;
                                default:
                                    System.out.println("Opción no válida, intente de nuevo.");
                                    break;
                            }
                        } while (continuarEditando);

                    }
                    break;
                case 4:
                    // Opción para Eliminar un empleado
                    controlPersis.borrarEmpleado();
                    break;
                case 5:
                    // Opcción para Buscar empleados por cargo
                    String cargoEmpleado = controlPersis.validacionEntradaTexto("Introduzca el cargo de los empleados a buscar:");
                    List<Empleado> listaEmpleadosTipo = controlPersis.traerEmpleadosPorTipo(cargoEmpleado);
                    if (listaEmpleadosTipo.isEmpty()) {
                        System.out.println("No se encontraron empleados con el cargo: " + cargoEmpleado);
                    } else {
                        System.out.println("----Lista de empleados de tipo " + cargoEmpleado + "----");
                        for (Empleado per : listaEmpleadosTipo) {
                            System.out.println(per.toString());
                        }
                    }
                    break;
                case 6:
                    // Salir de la aplicación
                    System.out.println("Saliendo de la aplicación...");
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción no válida, intente de nuevo.");
                    break;
            }
        } while (continuar);
    }

    /**
     * El metodo menu() muestra un menú de opciones y permite al usuario seleccionar una.
     * Tiene control de excepciones por si se mete algún tipo de dato que no sea de tipo entero
     *
     * @return un número entero, que representa la opción seleccionada por el usuario.
     */
    public static int menu() {
        int opcion = 0;

        System.out.println("----------------------------------------------------");
        System.out.println("|            MENÚ DE GESTIÓN DE EMPLEADOS          |");
        System.out.println("----------------------------------------------------");
        System.out.println("1. Agregar un nuevo empleado");
        System.out.println("2. Listar todos los empleados");
        System.out.println("3. Actualizar información de un empleado");
        System.out.println("4. Eliminar un empleado");
        System.out.println("5. Buscar empleados por cargo");
        System.out.println("6. Salir de la aplicación");
        System.out.println("----------------------------------------------------");
        System.out.println("Introduzca una opción del 1 al 6");
        System.out.println("----------------------------------------------------");

        try {
            opcion = leer.nextInt();
        } catch (java.util.InputMismatchException e) {
            // Atrapar la excepción si se ingresa algo que no es un entero
            System.out.println("Entrada no válida. Ingrese un número entero.");
            leer.next(); // Limpiar el búfer de entrada para evitar un bucle infinito
        }

        if (opcion < 1 || opcion > 6) {
            System.out.println("OPCIÓN INCORRECTA");
        }

        return opcion;
    }

    /**
     * Metodo para mostrar un menú de edición de empleados.
     * Permite al usuario seleccionar una opción para editar los datos de un empleado.
     * Tiene control de excepciones por si se mete algún tipo de dato que no sea de tipo entero
     *
     * @return un número entero, que representa la opción seleccionada por el usuario.
     */
    public static int menuEditar() {
        int opcion = 0;

        System.out.println("----------------------------------------------------");
        System.out.println("|            MENÚ DE EDICION DE EMPLEADOS          |");
        System.out.println("----------------------------------------------------");
        System.out.println("1. Editar nombre del empleado");
        System.out.println("2. Editar apellido del empleado");
        System.out.println("3. Editar cargo del empleado");
        System.out.println("4. Editar salario del empleado");
        System.out.println("5. Editar fecha de inicio del empleado");
        System.out.println("6. GUARDAR DATOS y volver al menu principal");
        System.out.println("7. Volver SIN GUARDAR al menu principal");
        System.out.println("----------------------------------------------------");
        System.out.println("Introduzca una opción del 1 al 7");
        System.out.println("----------------------------------------------------");

        try {
            opcion = leer.nextInt();
        } catch (java.util.InputMismatchException e) {
            // Atrapar la excepción si se ingresa algo que no es un entero
            System.out.println("Entrada no válida. Ingrese un número entero.");
            leer.next(); // Limpiar el búfer de entrada para evitar un bucle infinito
        }

        if (opcion < 1 || opcion > 7) {
            System.out.println("OPCIÓN INCORRECTA");
        }

        return opcion;
    }
}