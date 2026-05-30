package org.example.proyectobae.model;

import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash
@Getter
public class Jugador implements Serializable {

    private String juego;
    private String nombre;
    private double puntuacion;

    public Jugador() {
    }

    public Jugador(String juego, String nombre, double puntuacion) {
        this.juego = juego;
        this.nombre = nombre;
        this.puntuacion = puntuacion;
    }

    @Override
    public String toString() {
        return "Juego: "  + juego + " | Nombre: " + nombre + " | Puntuacion: "+ puntuacion;
    }
}
