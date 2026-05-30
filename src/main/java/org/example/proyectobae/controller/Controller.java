package org.example.proyectobae.controller;

import org.example.proyectobae.model.Jugador;
import org.example.proyectobae.service.Servicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;


@RestController
@RequestMapping("/api/ranking")
public class Controller {

    @Autowired
    private Servicio servicio;

    @GetMapping("/{juego}")
    public ResponseEntity<List<Jugador>> listarRanking(@PathVariable String juego) {
        List<Jugador> jugadores = servicio.obtenerRanking(juego);

        if (jugadores.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(jugadores);
    }

    @GetMapping("/juegos")
    public ResponseEntity<Set<String>> listarJuegos() {
        Set<String> listaJuegos = servicio.obtenerTodosLosJuegos();
        if (listaJuegos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(listaJuegos);
    }

    @GetMapping("/{juego}/{nombre}")
    public ResponseEntity<Jugador> obtenerJugadorJuego(@PathVariable String juego, @PathVariable String nombre) {
        Jugador jugador = servicio.obtenerPuntuacionJugador(juego, nombre);

        if (jugador == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(jugador);
    }

    @GetMapping("/jugador/{nombre}")
    public ResponseEntity<Map<String, Double>> obtenerRankingGlobal(@PathVariable String nombre) {
        Map<String, Double> historial = servicio.obtenerPuntuacionesGlobal(nombre);

        if (historial.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(historial);
    }

    /*@PostMapping("/{juego}")
    public ResponseEntity<String> insertar(@PathVariable String juego, @RequestBody Jugador jugador) {
        servicio.registrarPuntuacion(juego, jugador.getNombre(), jugador.getPuntuacion());
        return ResponseEntity.ok("Puntuacion registrada correctamente en " + juego);
    }*/

}
