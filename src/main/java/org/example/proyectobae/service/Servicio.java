package org.example.proyectobae.service;


import org.example.proyectobae.model.Jugador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
public class Servicio {
    @Autowired
    private StringRedisTemplate redisTemplate;

    public void registrarPuntuacion(String idJuego, String nombreJugador, double puntos) {
        redisTemplate.opsForZSet().add("ranking:" + idJuego, nombreJugador, puntos);
    }

    public List<Jugador> obtenerRanking(String idJuego) {
        Set<ZSetOperations.TypedTuple<String>> ranking = redisTemplate.opsForZSet()
                .reverseRangeWithScores("ranking:" + idJuego, 0, -1);

        if (ranking == null) return Collections.emptyList();

        return ranking.stream()
                .map(tuple -> new Jugador(tuple.getValue(), tuple.getScore()))
                .toList();
    }

}
