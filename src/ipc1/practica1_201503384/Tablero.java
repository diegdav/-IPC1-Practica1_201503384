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
    private char[][] tablero;

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

    public void mostrarTablero() {
        tablero = new char[getFilas()][getColumnas()];
        
        int fila, columna;
        for (fila = 0; fila < filas; fila++) {
            for (columna = 0; columna < columnas; columna++) {
                System.out.print("|_");
                tablero[fila][columna] = ' ';
                System.out.print(tablero[fila][columna]);
                System.out.print("_");
            }
            System.out.println("|");
        }
        System.out.println("");
    }
}
