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

    Dificultad dificultad = new Dificultad();
    Tablero tablero = new Tablero();
    Jugador[] jugador;
    Subidas[] subidas;
    Bajones[] bajones;
    Subidas colocar_subidas;

    private int cant_jugadores;
    private int cant_subidas;
    private int cant_bajones;

    public Menu() {
        colocar_subidas = new Subidas();
    }

    public void mostrarMenuPrincipal() {
        int opcion;
        do {
            System.out.println("-------------------MENU PRINCIPAL-------------------");
            System.out.println("1. Dificultad del juego");
            System.out.println("2. Parametros iniciales");
            System.out.println("3. Iniciar juego");
            System.out.println("4. Salir");
            System.out.print("Ingrese a una opcion: ");
            opcion = teclado.nextInt();

            switch (opcion) {
                case 1:
                    mostrarMenuDificultad();
                    break;
                case 2:
                    if (dificultad.getDificultad() == null) {
                        System.out.println("\nDebe de seleccionar primero la dificultad");
                        System.out.println("");
                        mostrarMenuPrincipal();
                    } else {
                        mostrarMenuParametros();
                    }
                    break;
                case 3:
                    crearTablero(dificultad.getDificultad());
                    crearSubidas(dificultad.getDificultad());
                    tablero.mostrarTablero();
                    mostrarMenuPrincipal();
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    System.out.println("\nOpcion incorrecta, vuelva a intentarlo");
                    System.out.println("");
            }
        } while (opcion < 1 || opcion > 4);
    }

    public void mostrarMenuDificultad() {
        int opcion;
        do {
            System.out.println("\n----------------------Dificultad----------------------");
            System.out.println("1. Facil");
            System.out.println("2. Dificil");
            System.out.println("3. Regresar");
            System.out.print("Ingrese a una opcion: ");
            opcion = teclado.nextInt();

            switch (opcion) {
                case 1:
                    dificultad.setDificultad("Facil");
                    mostrarRestricciones(dificultad.getDificultad());
                    break;
                case 2:
                    dificultad.setDificultad("Dificil");
                    mostrarRestricciones(dificultad.getDificultad());
                    break;
                case 3:
                    System.out.println("");
                    mostrarMenuPrincipal();
                    break;
                default:
                    System.out.println("\nOpcion incorrecta, vuelva a intentarlo");
            }
        } while (opcion < 1 || opcion > 3);
    }

    public void mostrarMenuParametros() {
        int opcion;
        do {
            System.out.println("\n-----------------Parametros Iniciales-----------------");
            System.out.println("1. Jugadores");
            System.out.println("2. Subidas y Bajones");
            System.out.println("3. Regresar");
            System.out.print("Ingrese a una opcion: ");
            opcion = teclado.nextInt();

            switch (opcion) {
                case 1:
                    ingresarCantidadJugadores(dificultad.getDificultad());
                    generarTurno(jugador.length);
                    break;
                case 2:
                    System.out.println("");
                    pedirSubidas(dificultad.getDificultad());
                    pedirBajones(dificultad.getDificultad());
                    mostrarMenuPrincipal();
                    break;
                case 3:
                    System.out.println("");
                    mostrarMenuPrincipal();
                    break;
                default:
                    System.out.println("\nOpcion incorrecta, vuelva a intentarlo");
            }
        } while (opcion < 1 || opcion > 3);
    }

    public void ingresarCantidadJugadores(String dificultad) {
        if (dificultad.equals("Facil")) {
            do {
                System.out.print("\nIngrese la cantidad de jugadores (2-3):");
                cant_jugadores = teclado.nextInt();
            } while (cant_jugadores < 2 || cant_jugadores > 3);
        } else {
            do {
                System.out.print("\nIngrese la cantidad de jugadores (2-4):");
                cant_jugadores = teclado.nextInt();
            } while (cant_jugadores < 2 || cant_jugadores > 4);
        }
        jugador = new Jugador[cant_jugadores];
        crearJugadores(jugador.length);
    }

    public void crearJugadores(int cant_jugadores) {
        int i;
        for (i = 0; i < cant_jugadores; i++) {
            if (jugador[i] == null) {
                jugador[i] = new Jugador();
            }
        }
        asignarSimbolo(jugador.length);
    }

    public void asignarSimbolo(int cant_jugadores) {
        char simbolo;
        boolean simbolo_valido;
        int i, j;
        for (i = 0; i < cant_jugadores; i++) {
            do {
                simbolo_valido = true;
                System.out.print("Ingrese el sibolo que representara al jugador " + (i + 1) + ": ");
                simbolo = teclado.next().charAt(0);
                for (j = 0; j < i; j++) {
                    if (simbolo == jugador[j].getSimbolo()) {
                        System.out.println("\nEste simbolo ya esta siendo utilizado por otro jugador. Favor de ingresar otro");
                        System.out.println("");
                        simbolo_valido = false;
                    }
                }
            } while (!simbolo_valido);
            jugador[i].setSimbolo(simbolo);
        }
        mostrarMenuParametros();
    }

    public void generarTurno(int cant_jugadores) {
        int i, j, random;
        boolean turno;
        for (i = 0; i < cant_jugadores; i++) {
            do {
                turno = true;
                random = (int) (Math.random() * cant_jugadores) + 1;
                for (j = 0; j < i; j++) {
                    if (random == jugador[j].getNum_jugador()) {
                        turno = false;
                    }
                }
            } while (!turno);
            jugador[i].setNum_jugador(random);
        }
    }

    public void mostrarRestricciones(String dificultad) {
        System.out.println("\nDificultad selecciona: " + dificultad);
        if (dificultad.equals("Facil")) {
            System.out.println("Jugadores de 2 a 3");
            System.out.println("Subidas de 5 a 10");
            System.out.println("Bajones de 5 a 10");
        } else {
            System.out.println("Jugadores de 2 a 4");
            System.out.println("Subidas de 20 a 40");
            System.out.println("Bajones de 20 a 40");
        }
        System.out.println("");
        mostrarMenuPrincipal();
    }

    public void pedirSubidas(String dificultad) {
        if (dificultad.equals("Facil")) {
            do {
                System.out.print("Ingrese la cantidad de subidas (5-10): ");
                cant_subidas = teclado.nextInt();
            } while (cant_subidas < 5 || cant_subidas > 10);
        } else {
            do {
                System.out.print("Ingrese la cantidad de subidas (20-40): ");
                cant_subidas = teclado.nextInt();
            } while (cant_subidas < 20 || cant_subidas > 40);
        }
        subidas = new Subidas[cant_subidas];
        System.out.println("");
    }

    public void pedirBajones(String dificultad) {
        if (dificultad.equals("Facil")) {
            do {
                System.out.print("Ingrese la cantidad de bajones (5-10): ");
                cant_bajones = teclado.nextInt();
            } while (cant_bajones < 5 || cant_bajones > 10);
        } else {
            do {
                System.out.print("Ingrese la cantidad de bajones (20-40): ");
                cant_bajones = teclado.nextInt();
            } while (cant_bajones < 20 || cant_bajones > 40);
        }
        bajones = new Bajones[cant_bajones];
        System.out.println("");
    }

    public void crearTablero(String dificultad) {
        System.out.println("");
        if (dificultad.equals("Facil")) {
            tablero.setFilas(5);
            tablero.setColumnas(8);
        } else {
            tablero.setFilas(20);
            tablero.setColumnas(10);
        }
    }

    public void analizarPosicionSubidaInicio(int random_ix, int random_iy, boolean posicion_correcta, int i) {
        int j;
        for (j = 0; j < i; j++) {
            if (random_ix == subidas[j].getInicio_x() && random_iy == subidas[j].getInicio_y()) {
                posicion_correcta = false;
            } else if (random_ix == subidas[j].getInicio_x() - 1 && random_iy == subidas[j].getInicio_y()) {
                posicion_correcta = false;
            } else if (random_ix == subidas[j].getInicio_x() + 1 && random_iy == subidas[j].getInicio_y()) {
                posicion_correcta = false;
            }
        }
    }

    public void analizarPosicionSubidaFinal(int random_fx, int random_fy, boolean posicion_correcta, int i) {

    }

    public void crearSubidas(String dificultad) {
        int i, random_ix, random_iy, random_fx, random_fy;
        boolean posicion_correcta;

        if (dificultad.equals("Facil")) {
            for (i = 0; i < subidas.length; i++) {
                subidas[i] = new Subidas();
                do {
                    posicion_correcta = true;
                    random_ix = (int) (Math.random() * 7);
                    random_iy = (int) (Math.random() * 3);
                    random_fx = (int) (Math.random() * 7);
                    random_fy = (int) (Math.random() * 4 + 1);
                    analizarPosicionSubidaInicio(random_ix, random_iy, posicion_correcta, i);
                    //analizarPosicionSubidaFinal(random_fx, random_fy, posicion_correcta, i);
                } while (!posicion_correcta);
                subidas[i].setInicio_x(random_ix);
                subidas[i].setInicio_y(random_iy);
                subidas[i].setFinal_x(random_fx);
                subidas[i].setFinal_y(random_fy);
            }
        } else {
            for (i = 0; i < subidas.length; i++) {
                subidas[i] = new Subidas();
                do {
                    posicion_correcta = true;
                    random_ix = (int) (Math.random() * 19);
                    random_iy = (int) (Math.random() * 8);
                    random_fx = (int) (Math.random() * 19);
                    random_fy = (int) (Math.random() * 9 + 1);
                    analizarPosicionSubidaInicio(random_ix, random_iy, posicion_correcta, i);
                    analizarPosicionSubidaFinal(random_fx, random_fy, posicion_correcta, i);
                } while (!posicion_correcta);
                subidas[i].setInicio_x(random_ix);
                subidas[i].setInicio_y(random_iy);
                subidas[i].setFinal_x(random_fx);
                subidas[i].setFinal_y(random_fy);
            }
        }
    }
}
