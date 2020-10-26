package com.example.persona.repositories;


import com.example.persona.entities.Autor;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepository extends  BaseRepository<Autor, Long> { // Pasamos autor como tipo de entidad y Long como tipo de ID
}
