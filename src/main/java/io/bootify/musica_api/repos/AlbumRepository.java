package io.bootify.musica_api.repos;

import io.bootify.musica_api.domain.Album;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface AlbumRepository extends JpaRepository<Album, Integer> {
    List<Album> findByArtistaId(Integer artistId);
}
