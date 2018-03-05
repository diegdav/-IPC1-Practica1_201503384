/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ipc1.practica1_201503384;

/**
 *
 * @author diego
 */
public class Tablero {

    private int columnas;
    private int filas;
    String[] subida;
    private String[][] mostrar_tablero;
    private int[][] posiciones_tablero;
    private int contador;
    private int[] posicion_actual;
    private int[] posicion_anterior;
    private int[] posicion_subida;
    private int[] posicion_subidaf;
    private int[] posicion_bajones;
    private int[] posicion_bajonesf;
    private String[] simbolo;
    private String[] comodin;
    private int contador_subida;
    private int contador_bajones;

    public Tablero() {
        contador_subida = 0;
        contador = 1;
        posicion_actual = new int[4];
        posicion_anterior = new int[4];
        comodin = new String[4];
        simbolo = new String[4];
        posicion_subida = new int[40];
        posicion_subidaf = new int[40];
        posicion_bajones = new int[40];
        posicion_bajonesf = new int[40];
    }

    /**
     * @return the columnas
     */
    public int getColumnas() {
        return columnas;
    }

    /**
     * @param columnas the columnas to set
     */
    public void setColumnas(int columnas) {
        this.columnas = columnas;
    }

    /**
     * @return the filas
     */
    public int getFilas() {
        return filas;
    }

    /**
     * @param filas the filas to set
     */
    public void setFilas(int filas) {
        this.filas = filas;
    }

    public void crearTamanio() {
        mostrar_tablero = new String[getFilas()][getColumnas()];
        posiciones_tablero = new int[getFilas()][getColumnas()];
        int fila, columna;
        for (fila = this.filas - 1; fila >= 0; fila--) {
            for (columna = 0; columna < this.columnas; columna++) {
                if (fila % 2 == 0) {
                    mostrar_tablero[fila][columna] = " ";
                    posiciones_tablero[fila][columna] = contador;
                    contador++;
                } else {
                    mostrar_tablero[fila][columna] = " ";
                    posiciones_tablero[fila][this.columnas - 1 - columna] = contador;
                    contador++;
                }
            }
        }
        contador = 1;
    }

    public void agregarSubidas(int inicio, int finaliza, int cant_subidas) {
        int fila, columna;
        for (fila = 0; fila < this.filas; fila++) {
            for (columna = 0; columna < this.columnas; columna++) {
                if (posiciones_tablero[fila][columna] == inicio) {
                    mostrar_tablero[fila][columna] = "+";
                    posicion_subida[contador_subida] = inicio;
                    posicion_subidaf[contador_subida] = finaliza;
                    contador_subida++;
                }
            }
        }
    }

    public void analizarSubidas(int posicion, int numero_jugador) {
        int i, fila, columna;
        for (i = 0; i < contador_subida; i++) {
            if (posicion == posicion_subida[i]) {
                System.out.println("\nHa caido en una subida. Sube " + (posicion_subidaf[i] - posicion_actual[numero_jugador]) + " casillas.");
                posicion_actual[numero_jugador] = posicion_subidaf[i];
            }
        }
        for (fila = 0; fila < this.filas; fila++) {
            for (columna = 0; columna < this.columnas; columna++) {
                if (posiciones_tablero[fila][columna] == posicion_actual[numero_jugador]) {
                    mostrar_tablero[fila][columna] = this.simbolo[numero_jugador];
                }
            }
        }
    }

    public void agregarBajones(int inicio, int finaliza, int cant_subidas) {
        int fila, columna;
        for (fila = 0; fila < this.filas; fila++) {
            for (columna = 0; columna < this.columnas; columna++) {
                if (posiciones_tablero[fila][columna] == inicio) {
                    mostrar_tablero[fila][columna] = "-";
                    posicion_bajones[contador_bajones] = inicio;
                    posicion_bajonesf[contador_bajones] = finaliza;
                    contador_bajones++;
                }
            }
        }
    }

    public void analizarBajones(int posicion, int numero_jugador) {
        int i, fila, columna;
        for (i = 0; i < contador_bajones; i++) {
            if (posicion == posicion_bajones[i]) {
                System.out.println("Ha caido en un bajon. Baja " + (posicion_actual[numero_jugador] - posicion_bajonesf[i]) + " casillas.");
                System.out.println("");
                posicion_actual[numero_jugador] = posicion_bajonesf[i];
            }
        }
        for (fila = 0; fila < this.filas; fila++) {
            for (columna = 0; columna < this.columnas; columna++) {
                if (posiciones_tablero[fila][columna] == posicion_actual[numero_jugador]) {
                    mostrar_tablero[fila][columna] = this.simbolo[numero_jugador];
                }
            }
        }
    }

    public void crearTablero() {
        int fila, columna;
        for (fila = 0; fila < this.filas; fila++) {
            for (columna = 0; columna < this.columnas; columna++) {
                if (fila == 0 && columna == this.columnas - 1) {
                    mostrar_tablero[fila][columna] = "$";
                }
                if (fila % 2 == 0) {
                    System.out.print("|_");
                    System.out.print(mostrar_tablero[fila][columna]);
                    System.out.print("_");
                } else {
                    System.out.print("|_");
                    System.out.print(mostrar_tablero[fila][columna]);
                    System.out.print("_");
                }
            }
            System.out.println("|");
        }
        System.out.println("");
    }

    public void agregarJugador(String simbolo, int posiciones, int numero_jugador) {
        int fila, columna, i;
        this.simbolo[numero_jugador] = simbolo;

        posicion_anterior[numero_jugador] = posicion_actual[numero_jugador];
        posicion_actual[numero_jugador] = posiciones + posicion_actual[numero_jugador];
        for (fila = 0; fila < this.filas; fila++) {
            for (fila = 0; fila < this.filas; fila++) {
                for (columna = 0; columna < this.columnas; columna++) {
                    if (posiciones_tablero[fila][columna] == posicion_actual[numero_jugador]) {
                        analizarSubidas(posicion_actual[numero_jugador], numero_jugador);
                        analizarBajones(posicion_actual[numero_jugador], numero_jugador);
                    }
                    if (posiciones_tablero[fila][columna] == posicion_anterior[numero_jugador] && posicion_anterior[numero_jugador] != posicion_actual[numero_jugador]) {
                        mostrar_tablero[fila][columna] = " ";
                    }
                    for (i = 0; i < posicion_subida.length; i++) {
                        if (posiciones_tablero[fila][columna] == posicion_subida[i]) {
                            mostrar_tablero[fila][columna] = "+";
                        }
                        if (posiciones_tablero[fila][columna] == posicion_bajones[i]) {
                            mostrar_tablero[fila][columna] = "-";
                        }
                    }
                }
            }
        }
        if (posicion_actual[numero_jugador] >= (this.filas * this.columnas)) {
            finalizar(this.simbolo[numero_jugador]);
        }
    }

    public void finalizar(String ganador) {
        int fila, columna;

        System.out.println("\nEl jugador ganador es: " + ganador);
        for (fila = 0; fila < this.filas; fila++) {
            for (columna = 0; columna < this.columnas; columnas++) {
                mostrar_tablero[fila][columna] = " ";
            }
        }
        System.out.println("");
        new Menu().mostrarMenuPrincipal();
    }
}
