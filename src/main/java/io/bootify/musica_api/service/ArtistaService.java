package io.bootify.musica_api.service;

import io.bootify.musica_api.domain.Album;
import io.bootify.musica_api.domain.Artista;
import io.bootify.musica_api.model.AlbumDTO;
import io.bootify.musica_api.model.ArtistaDTO;
import io.bootify.musica_api.repos.AlbumRepository;
import io.bootify.musica_api.repos.ArtistaRepository;
import io.bootify.musica_api.util.NotFoundException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class ArtistaService {

    private final ArtistaRepository artistaRepository;
    private final AlbumRepository albumRepository;

    public ArtistaService(final ArtistaRepository artistaRepository, final AlbumRepository albumRepository) {
        this.artistaRepository = artistaRepository;
        this.albumRepository = albumRepository;
    }
    public List<ArtistaDTO> findAll() {
        final List<Artista> artistas = artistaRepository.findAll(Sort.by("id"));
        return artistas.stream()
                .map(artista -> mapToDTO(artista, new ArtistaDTO()))
                .toList();
    }

    public ArtistaDTO get(final Integer id) {
        return artistaRepository.findById(id)
                .map(artista -> mapToDTO(artista, new ArtistaDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(final ArtistaDTO artistaDTO) {
        final Artista artista = new Artista();
        mapToEntity(artistaDTO, artista);
        return artistaRepository.save(artista).getId();
    }

    public void update(final Integer id, final ArtistaDTO artistaDTO) {
        final Artista artista = artistaRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(artistaDTO, artista);
        artistaRepository.save(artista);
    }

    public void delete(final Integer id) {
        artistaRepository.deleteById(id);
    }

    private ArtistaDTO mapToDTO(final Artista artista, final ArtistaDTO artistaDTO) {
        artistaDTO.setId(artista.getId());
        artistaDTO.setNombre(artista.getNombre());
        artistaDTO.setGenero(artista.getGenero());
        return artistaDTO;
    }

    private Artista mapToEntity(final ArtistaDTO artistaDTO, final Artista artista) {
        artista.setNombre(artistaDTO.getNombre());
        artista.setGenero(artistaDTO.getGenero());
        return artista;
    }

    public List<AlbumDTO> findAlbumsByArtistId(Integer artistId) {
        return albumRepository.findByArtistaId(artistId).stream()
                .map(this::mapToAlbumDTO)
                .collect(Collectors.toList());
    }

    private AlbumDTO mapToAlbumDTO(final Album album) {
        AlbumDTO albumDTO = new AlbumDTO();
        albumDTO.setId(album.getId());
        albumDTO.setTitulo(album.getTitulo());
        albumDTO.setFecha_lanzamiento(album.getFecha_lanzamiento());
        if (album.getArtista() != null) {
            albumDTO.setArtista_id(album.getArtista().getId());  // Usando el objeto relacionado para obtener el ID
        }        return albumDTO;
    }
}
