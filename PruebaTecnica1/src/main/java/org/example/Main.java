package org.example;

import org.example.logica.Empleado;
import org.example.persistencia.controlador.ControladoraPersistencia;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static Scanner leer;

    //Para poder leer las opciones del menu	que estan en un metodo estatico
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
                    // Lógica para crear empleado
                    Empleado empleado = controlPersis.pedirDatosEmpleado();
                    controlPersis.crearEmpleado(empleado);
                    break;
                case 2:
                    // Lógica para Listar todos los empleados
                    List<Empleado> listaEmpleados = controlPersis.traerEmpleado();
                    System.out.println("----Lista de empleados----");
                    for (Empleado per : listaEmpleados) {
                        System.out.println(per.toString());
                    }
                    break;
                case 3:
                    // Lógica para Actualizar información de un empleado
                    Long idEmpleado = controlPersis.validarEntradaLong("Introduzca id del empleado a editar");
                    Empleado empleadoPorId = controlPersis.obtenerEmpleadoPorId(idEmpleado);
                    System.out.println("Datos del empleado con id: " + idEmpleado);
                    System.out.println(empleadoPorId);
                    boolean continuarEditando = true;
                    do {
                        //Carga el menu inicial y se recupera la opción elegida
                        int opcionEditar = menuEditar();
                        //Validacion para que se repita el menu en caso de que la opción esté fuera de rango 1-6 de tipo entero
                        while (opcionEditar < 1 || opcionEditar > 7) {
                            opcion = menu();
                        }
                        switch (opcionEditar) {
                            case 1:
                                // Lógica para editar nombre
                                String nombre = controlPersis.validacionEntradaTexto("Introduzca el nombre del empleado:");
                                empleadoPorId.setNombre(nombre);
                                break;
                            case 2:
                                // Lógica para Listar todos los empleados
                                String apellido = controlPersis.validacionEntradaTexto("Introduzca el apellido del empleado:");
                                empleadoPorId.setApellido(apellido);

                                break;
                            case 3:
                                // Lógica para Actualizar información de un empleado
                                String cargo = controlPersis.validacionEntradaTexto("Introduzca el cargo del empleado:");
                                empleadoPorId.setCargo(cargo);

                                break;
                            case 4:
                                // Lógica para Eliminar un empleado
                                Double salario = controlPersis.validarEntradaDecimal("Introduzca el salario del empleado:");
                                empleadoPorId.setSalario(salario);

                                break;
                            case 5:
                                // Lógica para Buscar empleados por cargo
                                Date fechaInicio = controlPersis.obtenerEntradaFecha("Introduzca la fecha de inicio del empleado (Formato: dd/mm/yyyy):");
                                empleadoPorId.setFechaInicio(fechaInicio);

                                break;
                            case 6:
                                // Salir de la aplicación
                                System.out.println("Guardando información...");
                                if (empleadoPorId != null) {
                                    controlPersis.modificarEmpleado(empleadoPorId);
                                }
                                continuarEditando = false;
                                break;
                            case 7:
                                // Salir de la aplicación
                                System.out.println("Volver sin guardar...");
                                continuarEditando = false;
                                break;
                            default:
                                System.out.println("Opción no válida, intente de nuevo.");
                                break;
                        }
                    } while (continuarEditando);
                    break;
                case 4:
                    // Lógica para Eliminar un empleado
                    controlPersis.borrarEmpleado();
                    break;
                case 5:
                    // Lógica para Buscar empleados por cargo
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
        System.out.println("Introduzca una opción del 1 al 6");
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