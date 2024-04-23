package io.bootify.musica_api.service;

import io.bootify.musica_api.domain.Album;
import io.bootify.musica_api.model.AlbumDTO;
import io.bootify.musica_api.repos.AlbumRepository;
import io.bootify.musica_api.util.NotFoundException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class AlbumService {

    private final AlbumRepository albumRepository;

    public AlbumService(final AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    public List<AlbumDTO> findAll() {
        final List<Album> albums = albumRepository.findAll(Sort.by("id"));
        return albums.stream()
                .map(album -> mapToDTO(album, new AlbumDTO()))
                .toList();
    }

    public AlbumDTO get(final Integer id) {
        return albumRepository.findById(id)
                .map(album -> mapToDTO(album, new AlbumDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(final AlbumDTO albumDTO) {
        final Album album = new Album();
        mapToEntity(albumDTO, album);
        return albumRepository.save(album).getId();
    }

    public void update(final Integer id, final AlbumDTO albumDTO) {
        final Album album = albumRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(albumDTO, album);
        albumRepository.save(album);
    }

    public void delete(final Integer id) {
        albumRepository.deleteById(id);
    }

    private AlbumDTO mapToDTO(final Album album, final AlbumDTO albumDTO) {
        albumDTO.setId(album.getId());
        albumDTO.setTitulo(album.getTitulo());
        albumDTO.setFecha_lanzamiento(album.getFecha_lanzamiento());
        if (album.getArtista() != null) {
            albumDTO.setArtista_id(album.getArtista().getId());  // Usando el objeto relacionado para obtener el ID
        }        return albumDTO;
    }

    private Album mapToEntity(final AlbumDTO albumDTO, final Album album) {
        album.setTitulo(albumDTO.getTitulo());
        album.setFecha_lanzamiento(albumDTO.getFecha_lanzamiento());
        if (album.getArtista() != null) {
            albumDTO.setArtista_id(album.getArtista().getId());  // Usando el objeto relacionado para obtener el ID
        }        return album;
    }

    public List<AlbumDTO> findAllByArtistId(Integer artistId) {
        List<Album> albums = albumRepository.findByArtistaId(artistId);
        return albums.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    private AlbumDTO mapToDTO(final Album album) {
        AlbumDTO albumDTO = new AlbumDTO();
        albumDTO.setId(album.getId());
        albumDTO.setTitulo(album.getTitulo());
        albumDTO.setFecha_lanzamiento(album.getFecha_lanzamiento());
        if (album.getArtista() != null) {
            albumDTO.setArtista_id(album.getArtista().getId());  // Usando el objeto relacionado para obtener el ID
        }        return albumDTO;
    }
}
