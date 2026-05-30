package org.example.proyectobae.controller;

import org.example.proyectobae.model.Jugador;
import org.example.proyectobae.service.Servicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/ranking")
public class Controller {

    @Autowired
    private Servicio servicio;

    @GetMapping("/{juego}")
    public List<Jugador> listar(@PathVariable String juego) {
        return servicio.obtenerRanking(juego);
    }

    @PostMapping("/{juego}")
    public ResponseEntity<String> insertar(@PathVariable String juego, @RequestBody Jugador jugador) {
        servicio.registrarPuntuacion(juego, jugador.getNombre(), jugador.getPuntuacion());
        return ResponseEntity.ok("Puntuacion registrada correctamente en " + juego);
    }

}
