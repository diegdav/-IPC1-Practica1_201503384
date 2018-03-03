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
    Subidas[] subida;
    private String[][] mostrar_tablero;

    public Tablero() {
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
        int fila, columna;
        for (fila = this.filas - 1; fila >= 0; fila--) {
            for (columna = 0; columna < this.columnas; columna++) {
                mostrar_tablero[fila][columna] = " ";
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
        for (fila = this.filas - 1; fila >= 0; fila--) {
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
    }
}
