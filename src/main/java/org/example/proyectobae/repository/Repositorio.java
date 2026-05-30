package org.example.proyectobae.repository;

import org.example.proyectobae.model.Jugador;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Repositorio extends CrudRepository<Jugador,String> {

}
