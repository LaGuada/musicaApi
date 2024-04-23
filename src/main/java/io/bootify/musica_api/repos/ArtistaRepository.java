package io.bootify.musica_api.repos;

import io.bootify.musica_api.domain.Artista;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ArtistaRepository extends JpaRepository<Artista, Integer> {
}
