package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AnalizadorLexico analizador = new AnalizadorLexico();

        while (true) {
            mostrarMenu();
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("Ingrese el código fuente:");
                    String codigoFuente = scanner.nextLine();
                    analizador.analizar(codigoFuente);
                    break;
                case 0:
                    System.out.println("Saliendo del programa. ¡Hasta luego!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }

    // Método para mostrar el menú de opciones al usuario
    private static void mostrarMenu() {
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("----------------------------Seleccione una opción para que el analizador lo lea:------------------------------");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("------ Oprima   1.   Para Ingresar el código fuente-----------");
        System.out.println("-------Oprima   0.  si dea Salir---------------------------------");
    }
}
