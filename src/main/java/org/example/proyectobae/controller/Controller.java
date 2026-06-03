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

    @PostMapping("/{juego}")
    public ResponseEntity<String> insertar(@PathVariable String juego, @RequestBody Jugador jugador) {
        servicio.registrarPuntuacion(juego, jugador.getNombre(), jugador.getPuntuacion());
        return ResponseEntity.ok("Puntuacion registrada correctamente en " + juego);
    }

    @GetMapping("/todos")
    public ResponseEntity<Set<String>> listarTodosLosJugadores() {
        Set<String> todos = servicio.obtenerTodosLosJugadoresDelSistema();

        if (todos.isEmpty()) {
            return ResponseEntity.noContent().build(); // Devuelve 204 si está vacío
        }

        return ResponseEntity.ok(todos); // Devuelve 200 con la lista completa
    }
    @PutMapping("/{juego}")
    public ResponseEntity<String> actualizarPuntuacion(
            @PathVariable String juego,
            @RequestBody Jugador jugador) {

        // Reutilizamos la función existente del servicio
        servicio.registrarPuntuacion(juego, jugador.getNombre(), jugador.getPuntuacion());

        return ResponseEntity.ok("Puntuación actualizada correctamente en " + juego);
    }

    @DeleteMapping("/{juego}/{jugador}")
    public ResponseEntity<String> eliminarJugador(@PathVariable String juego, @PathVariable String jugador){
        servicio.eliminarJugador(juego, jugador);

        return ResponseEntity.ok("Jugador eliminado correctamente " + juego);
    }

}
