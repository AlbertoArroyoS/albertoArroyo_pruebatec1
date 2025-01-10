package org.example.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Validaciones {

    private static final Scanner leer = new Scanner(System.in);


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

}
