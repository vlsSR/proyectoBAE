package org.example.proyectobae.model;

import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash
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
        return nombre + " | Nivel: " + " | " + " Puntos: " + puntuacion;
    }
}
