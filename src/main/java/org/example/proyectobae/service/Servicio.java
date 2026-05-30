package org.example.proyectobae.service;

import org.example.proyectobae.model.Jugador;
import org.example.proyectobae.repository.Repositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Servicio {
    @Autowired
    private Repositorio repo;

    public List<Jugador> getAllJugadores() {
        List<Jugador> todosJugadores = new ArrayList<>();
        repo.findAll().forEach(todosJugadores::add);
        return todosJugadores;
    }

}
