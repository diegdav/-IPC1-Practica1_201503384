/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ipc1.practica1_201503384;

import java.util.Scanner;

/**
 *
 * @author diego
 */
public class Menu {

    Scanner teclado = new Scanner(System.in);

    public void menuPrincipal() {
        int opcion;
        do {
            System.out.println("------------------MENU PRINCIPAL------------------");
            System.out.println("1. Dificultad del juego");
            System.out.println("2. Parametros iniciales");
            System.out.println("3. Iniciar juego");
            System.out.println("4. Salir");
            opcion = teclado.nextInt();

            switch (opcion) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    System.out.println("\nOpcion incorrecta, vuelva a intentarlo");
            }
        } while (opcion < 1 || opcion > 4);
    }
}
