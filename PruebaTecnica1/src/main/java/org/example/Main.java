package org.example;

import org.example.logica.Empleado;
import org.example.persistencia.controlador.ControladoraPersistencia;

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

            //Cargamos el menu inicial y recuperamos la opción elegida
            int opcion = menu();
            //Validacion para que se repita el menu en caso de que la opción esté fuera de rango
            while (opcion<1 || opcion>6){
                opcion = menu();
            }
            switch (opcion) {
                case 1:

                    break;
                case 2:
                    // Lógica para Listar todos los empleados
                    break;
                case 3:
                    // Lógica para Actualizar información de un empleado
                    break;
                case 4:
                    // Lógica para Eliminar un empleado
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
}