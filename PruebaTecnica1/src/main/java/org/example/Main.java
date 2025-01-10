package org.example;

import org.example.logica.Controladora;

/**
 * Clase Main que contiene el método main para ejecutar la aplicación.
 * Se encarga de mostrar un menú de opciones para gestionar empleados.
 *
 * @author Alberto Arroyo Santofimia
 */
public class Main {

    static Controladora controladoraLogica = new Controladora();

    public static void main(String[] args) {

        boolean continuar = true;
        do {
            continuar = controladoraLogica.opcionPrincipal();
        } while (continuar);
    }

}