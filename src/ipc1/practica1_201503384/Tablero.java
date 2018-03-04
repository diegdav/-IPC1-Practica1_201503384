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
    private String[] simbolo;

    public Tablero() {
        contador = 1;
        posicion_actual = new int[4];
        simbolo = new String[4];
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
    }

    public void agregarSubidas(int ix, int iy, int fx, int fy) {
        mostrar_tablero[iy][ix] = "+";
    }

    public void agregarBajones(int ix, int iy, int fx, int fy) {
        mostrar_tablero[iy][ix] = "-";
    }

    public void crearTablero() {
        int fila, columna;
        for (fila = 0; fila < this.filas; fila++) {
            for (columna = 0; columna < this.columnas; columna++) {
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
        contador = 1;
    }

    public void agregarJugador(String simbolo, int posiciones, int numero_jugador) {
        int fila, columna;
        System.out.println(numero_jugador);
        if (this.simbolo[numero_jugador] == null) {
            this.simbolo[numero_jugador] = simbolo;
        }

        posicion_actual[numero_jugador] = posiciones + posicion_actual[numero_jugador];
        for (fila = 0; fila < this.filas; fila++) {
            for (columna = 0; columna < this.columnas; columna++) {
                if (posiciones_tablero[fila][columna] == posicion_actual[numero_jugador]) {
                    mostrar_tablero[fila][columna] = this.simbolo[numero_jugador];
                }
            }
        }
    }
}
