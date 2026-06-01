package org.example.proyectobae.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@Getter
public class Jugador implements Serializable {

    private String nombre;
    private double puntuacion;

    public Jugador() {
    }

    public Jugador(String nombre, double puntuacion) {
        this.nombre = nombre;
        this.puntuacion = puntuacion;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + " | Puntuacion: "+ puntuacion;
    }
}
