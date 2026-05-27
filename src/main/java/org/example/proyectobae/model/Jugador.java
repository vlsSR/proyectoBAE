package org.example.proyectobae.model;

public class Jugador {

    private String nombre;
    private double puntuacion;
    private int nivel;

    public Jugador(String nombre, double puntuacion, int nivel) {
        this.nombre = nombre;
        this.puntuacion = puntuacion;
        this.nivel = nivel;
    }

    public String getNombre() { return nombre; }
    public double getPuntuacion() { return puntuacion; }
    public int getNivel() { return nivel; }

    @Override
    public String toString() {
        return nombre + " | Nivel: " + nivel + " | Puntos: " + puntuacion;
    }
}
