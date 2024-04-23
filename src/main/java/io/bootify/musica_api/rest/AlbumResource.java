package io.bootify.musica_api.rest;

import io.bootify.musica_api.model.AlbumDTO;
import io.bootify.musica_api.service.AlbumService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/albums", produces = MediaType.APPLICATION_JSON_VALUE)
public class AlbumResource {

    private final AlbumService albumService;

    public AlbumResource(final AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping
    public ResponseEntity<List<AlbumDTO>> getAllAlbums() {
        return ResponseEntity.ok(albumService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlbumDTO> getAlbum(@PathVariable(name = "id") final Integer id) {
        return ResponseEntity.ok(albumService.get(id));
    }

    @PostMapping
    public ResponseEntity<Integer> createAlbum(@RequestBody @Valid final AlbumDTO albumDTO) {
        final Integer createdId = albumService.create(albumDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Integer> updateAlbum(@PathVariable(name = "id") final Integer id,
            @RequestBody @Valid final AlbumDTO albumDTO) {
        albumService.update(id, albumDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlbum(@PathVariable(name = "id") final Integer id) {
        albumService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-artist/{artistId}")
    public ResponseEntity<List<AlbumDTO>> getAlbumsByArtistId(@PathVariable Integer artistId) {
        List<AlbumDTO> albums = albumService.findAllByArtistId(artistId);
        return ResponseEntity.ok(albums);
    }


}
