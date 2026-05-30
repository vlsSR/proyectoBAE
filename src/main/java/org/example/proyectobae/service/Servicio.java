package org.example.proyectobae.service;


import org.example.proyectobae.model.Jugador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class Servicio {
    @Autowired
    private StringRedisTemplate redisTemplate;

    /*public void registrarPuntuacion(String idJuego, String nombreJugador, double puntos) {
        redisTemplate.opsForZSet().add("ranking:" + idJuego, nombreJugador, puntos);
    }*/

    public List<Jugador> obtenerRanking(String idJuego) {
        Set<ZSetOperations.TypedTuple<String>> ranking = redisTemplate.opsForZSet()
                .reverseRangeWithScores("ranking:" + idJuego, 0, -1);

        if (ranking == null) return Collections.emptyList();

        return ranking.stream()
                .map(tuple -> new Jugador(tuple.getValue(), tuple.getScore()))
                .toList();
    }

    public Set<String> obtenerTodosLosJuegos() {
        // Busca todas las llaves que coinciden con el patrón
        Set<String> keys = redisTemplate.keys("ranking:*");

        // Limpia el prefijo "ranking:" para devolver solo los nombres de los juegos
        return keys.stream()
                .map(key -> key.replace("ranking:", ""))
                .collect(Collectors.toSet());
    }

    public Jugador obtenerPuntuacionJugador(String juego, String nombre) {
        Double puntuacion = redisTemplate.opsForZSet().score("ranking:" + juego, nombre);

        if (puntuacion == null) {
            return null;
        }

        return new Jugador(nombre, puntuacion);
    }

    public Map<String, Double> obtenerPuntuacionesGlobal(String nombreJugador) {
        Map<String, Double> resultados = new HashMap<>();
        Set<String> keys = redisTemplate.keys("ranking:*");

        for (String key : keys) {
            Double puntuacion = redisTemplate.opsForZSet().score(key, nombreJugador);
            if (puntuacion != null) {
                String juego = key.replace("ranking:", "");
                resultados.put(juego, puntuacion);
            }
        }
        return resultados;
    }
}
