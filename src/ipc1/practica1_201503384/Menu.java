/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ipc1.practica1_201503384;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author diego
 */
public class Menu {

    Scanner teclado = new Scanner(System.in);

    Dificultad dificultad = new Dificultad();
    Jugador[] jugador;
    Subidas[] subidas;
    Bajones[] bajones;

    private int opcion;
    private int cant_jugadores;
    private int cant_subidas;
    private int cant_bajones;
    private boolean posicion_correcta_inicio;
    private boolean posicion_correcta_final;
    private int random_inicio, random_final;
    private int turno_jugador;

    Tablero tablero = new Tablero();

    public Menu() {
        opcion = 0;
        turno_jugador = 0;
    }

    public void mostrarMenuPrincipal() {
        do {
            try {
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
                        if (dificultad.getDificultad() == null) {
                            System.out.println("\nDebe de seleccionar primero la dificultad");
                            System.out.println("");
                            mostrarMenuPrincipal();
                        } else if (cant_jugadores == 0) {
                            System.out.println("\nDebe de seleccionar primero la cantidad de jugadores");
                            System.out.println("");
                            mostrarMenuPrincipal();
                        } else if (cant_subidas == 0) {
                            System.out.println("\nDebe de seleccionar primero la cantidad de subidas y bajones");
                            System.out.println("");
                            mostrarMenuPrincipal();
                        } else {
                            crearSubidas(dificultad.getDificultad());
                            crearBajones(dificultad.getDificultad());
                            tablero.crearTablero();
                            iniciarJuego();
                        }
                        break;
                    case 4:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("\nOpcion incorrecta, vuelva a intentarlo");
                        System.out.println("");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nOpcion incorrecta, vuelva a intentarlo");
                teclado.nextLine();
                mostrarMenuPrincipal();
            }
        } while (opcion < 1 || opcion > 4);
    }

    public void mostrarMenuDificultad() {
        do {
            try {
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
            } catch (InputMismatchException e) {
                System.out.println("\nOpcion incorrecta, vuelva a intentarlo");
                teclado.nextLine();
                mostrarMenuDificultad();
            }
        } while (opcion < 1 || opcion > 3);
    }

    public void mostrarMenuParametros() {
        do {
            try {
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
            } catch (InputMismatchException e) {
                System.out.println("\nOpcion incorrecta, vuelva a intentarlo");
                teclado.nextLine();
                mostrarMenuParametros();
            }
        } while (opcion < 1 || opcion > 3);
    }

    public void ingresarCantidadJugadores(String dificultad) {
        if (dificultad.equals("Facil")) {
            do {
                try {
                    System.out.print("\nIngrese la cantidad de jugadores (2-3):");
                    cant_jugadores = teclado.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("\nOpcion incorrecta, vuelva a intentarlo");
                    teclado.nextLine();
                    ingresarCantidadJugadores(dificultad);
                }
            } while (cant_jugadores < 2 || cant_jugadores > 3);
        } else {
            do {
                try {
                    System.out.print("\nIngrese la cantidad de jugadores (2-4):");
                    cant_jugadores = teclado.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("\nOpcion incorrecta, vuelva a intentarlo");
                    teclado.nextLine();
                    ingresarCantidadJugadores(dificultad);
                }
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

        for (i = 0; i < cant_jugadores; i++) {
            System.out.print("\nJugador " + (i + 1) + ": " + jugador[i].getSimbolo());
        }
        System.out.println("");
        mostrarMenuParametros();
    }

    public void generarTurno(int cant_jugadores) {
        int i, random;
        boolean turno;
        for (i = 0; i < cant_jugadores; i++) {
            do {
                turno = true;
                random = (int) (Math.random() * cant_jugadores) + 1;

                if (random == jugador[random - 1].getNum_jugador()) {
                    turno = false;
                }
            } while (!turno);
            jugador[random - 1].setNum_jugador(random);
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

        crearTablero(dificultad);
        tablero.crearTamanio();

        System.out.println("");
        mostrarMenuPrincipal();
    }

    public void pedirSubidas(String dificultad) {
        if (dificultad.equals("Facil")) {
            do {
                try {
                    System.out.print("Ingrese la cantidad de subidas (5-10): ");
                    cant_subidas = teclado.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("\nOpcion incorrecta, vuelva a intentarlo");
                    teclado.nextLine();
                }
            } while (cant_subidas < 5 || cant_subidas > 10);
        } else {
            do {
                try {
                    System.out.print("Ingrese la cantidad de subidas (20-40): ");
                    cant_subidas = teclado.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("\nOpcion incorrecta, vuelva a intentarlo");
                    teclado.nextLine();
                }
            } while (cant_subidas < 20 || cant_subidas > 40);
        }
        subidas = new Subidas[cant_subidas];
        System.out.println("");
    }

    public void pedirBajones(String dificultad) {
        if (dificultad.equals("Facil")) {
            do {
                try {
                    System.out.print("Ingrese la cantidad de bajones (5-10): ");
                    cant_bajones = teclado.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("\nOpcion incorrecta, vuelva a intentarlo");
                    teclado.nextLine();
                }
            } while (cant_bajones < 5 || cant_bajones > 10);
        } else {
            do {
                try {
                    System.out.print("Ingrese la cantidad de bajones (20-40): ");
                    cant_bajones = teclado.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("\nOpcion incorrecta, vuelva a intentarlo");
                    teclado.nextLine();
                }
            } while (cant_bajones < 20 || cant_bajones > 40);
        }
        bajones = new Bajones[cant_bajones + 1];
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

    public void analizarPosicionSubidaInicio(int random_inicio, int i) {
        int j;
        for (j = 0; j < i; j++) {
            if (random_inicio == subidas[j].getInicio()) {
                posicion_correcta_inicio = false;
            } else if (random_inicio == subidas[j].getInicio() - 1) {
                posicion_correcta_inicio = false;
            } else if (random_inicio == subidas[j].getInicio() + 1) {
                posicion_correcta_inicio = false;
            }
        }
    }

    public void analizarPosicionSubidaFinal(int random_final, int i) {
        int j;
        for (j = 0; j < i; j++) {
            if (random_final == subidas[j].getFinaliza()) {
                posicion_correcta_final = false;
            } else if (random_final == subidas[j].getFinaliza() - 1) {
                posicion_correcta_final = false;
            } else if (random_final == subidas[j].getFinaliza() + 1) {
                posicion_correcta_final = false;
            } else if (random_final == subidas[j].getInicio()) {
                posicion_correcta_final = false;
            } else if (random_final == subidas[j].getInicio() - 1) {
                posicion_correcta_final = false;
            } else if (random_final == subidas[j].getInicio() + 1) {
                posicion_correcta_final = false;
            }
        }
    }

    public void crearSubidas(String dificultad) {
        if (dificultad.equals("Facil")) {
            int i;
            for (i = 0; i < subidas.length; i++) {
                subidas[i] = new Subidas();
                do {
                    posicion_correcta_inicio = true;
                    this.random_inicio = (int) (Math.random() * 32) + 1;
                    analizarPosicionSubidaInicio(this.random_inicio, i);
                } while (!posicion_correcta_inicio);
                do {
                    posicion_correcta_final = true;
                    this.random_final = (int) (Math.random() * 38) + this.random_inicio;
                    analizarPosicionSubidaFinal(this.random_final, i);
                } while (!posicion_correcta_final);
                subidas[i].setInicio(this.random_inicio);
                subidas[i].setFinaliza(this.random_final);

                tablero.agregarSubidas(subidas[i].getInicio(), subidas[i].getFinaliza(), cant_subidas);
            }
        } else {
            int i;
            for (i = 0; i < subidas.length; i++) {
                subidas[i] = new Subidas();
                do {
                    posicion_correcta_inicio = true;
                    this.random_inicio = (int) (Math.random() * 190) + 1;
                    analizarPosicionSubidaInicio(this.random_inicio, i);
                } while (!posicion_correcta_inicio);
                do {
                    posicion_correcta_final = true;
                    this.random_final = (int) (Math.random() * 198) + this.random_inicio;
                    analizarPosicionSubidaFinal(this.random_final, i);
                } while (!posicion_correcta_final);
                subidas[i].setInicio(this.random_inicio);
                subidas[i].setFinaliza(this.random_final);

                tablero.agregarSubidas(subidas[i].getInicio(), subidas[i].getFinaliza(), cant_subidas);
            }
        }
    }

    public void analizarPosicionBajonesInicio(int random_inicio, int i) {
        int j, k;
        for (j = 0; j < i; j++) {
            for (k = 0; k < subidas.length; k++) {
                if (random_inicio == bajones[j].getInicio()) {
                    posicion_correcta_inicio = false;
                } else if (random_inicio == bajones[j].getInicio() - 1) {
                    posicion_correcta_inicio = false;
                } else if (random_inicio == bajones[j].getInicio() + 1) {
                    posicion_correcta_inicio = false;
                } else if (random_inicio == subidas[k].getInicio()) {
                    posicion_correcta_inicio = false;
                } else if (random_inicio == subidas[k].getInicio() - 1) {
                    posicion_correcta_inicio = false;
                } else if (random_inicio == subidas[k].getInicio() + 1) {
                    posicion_correcta_inicio = false;
                }
            }
        }
    }

    public void analizarPosicionBajonesFinal(int random_final, int i) {
        int j, k;
        for (j = 0; j < i; j++) {
            for (k = 0; k < subidas.length; k++) {
                if (random_final == bajones[j].getFinaliza()) {
                    posicion_correcta_final = false;
                } else if (random_final == bajones[j].getFinaliza() - 1) {
                    posicion_correcta_final = false;
                } else if (random_final == bajones[j].getFinaliza() + 1) {
                    posicion_correcta_final = false;
                } else if (random_final == bajones[j].getInicio()) {
                    posicion_correcta_final = false;
                } else if (random_final == bajones[j].getInicio() - 1) {
                    posicion_correcta_final = false;
                } else if (random_final == bajones[j].getInicio() + 1) {
                    posicion_correcta_final = false;
                } else if (random_final == subidas[k].getFinaliza()) {
                    posicion_correcta_final = false;
                } else if (random_final == subidas[k].getFinaliza() - 1) {
                    posicion_correcta_final = false;
                } else if (random_final == subidas[k].getFinaliza() + 1) {
                    posicion_correcta_final = false;
                }
            }
        }
    }

    public void crearBajones(String dificultad) {
        int i;
        if (dificultad.equals("Facil")) {
            for (i = 0; i < bajones.length; i++) {
                bajones[i] = new Bajones();
                do {
                    posicion_correcta_inicio = true;
                    random_inicio = (int) (Math.random() * 38) + 9;
                    analizarPosicionBajonesInicio(random_inicio, i);
                } while (!posicion_correcta_inicio);
                do {
                    posicion_correcta_final = true;
                    random_final = (int) (Math.random() * random_inicio - 1) + 1;
                    analizarPosicionBajonesFinal(random_final, i);
                } while (!posicion_correcta_final);
                bajones[i].setInicio(random_inicio);
                bajones[i].setFinaliza(random_final);

                tablero.agregarBajones(bajones[i].getInicio(), bajones[i].getFinaliza(), cant_jugadores);
            }
        } else {
            for (i = 0; i < bajones.length; i++) {
                bajones[i] = new Bajones();
                do {
                    posicion_correcta_inicio = true;
                    random_inicio = (int) (Math.random() * 198) + 11;
                    analizarPosicionBajonesInicio(random_inicio, i);
                } while (!posicion_correcta_inicio);
                do {
                    posicion_correcta_final = true;
                    random_final = (int) (Math.random() * 190) + 1;
                    analizarPosicionBajonesFinal(random_final, i);
                } while (!posicion_correcta_final);
                bajones[i].setInicio(random_inicio);
                bajones[i].setFinaliza(random_final);

                tablero.agregarBajones(bajones[i].getInicio(), bajones[i].getFinaliza(), cant_jugadores);
            }
        }
    }

    public void iniciarJuego() {
        do {
            System.out.print("\nTurno del jugador " + (turno_jugador + 1) + ": ");
            System.out.print("Lanzar dado");
            teclado.nextLine();
            jugador[turno_jugador].lanzarDado(dificultad.getDificultad());
            System.out.println("Se mueve " + jugador[turno_jugador].getDado() + " posiciones");
            System.out.println("");
            tablero.agregarJugador(String.valueOf(jugador[turno_jugador].getSimbolo()), jugador[turno_jugador].getDado(), turno_jugador);
            tablero.crearTablero();
            turno_jugador++;
            if (turno_jugador == jugador.length) {
                turno_jugador = 0;
            }
        } while (1 == 1);

    }
}
