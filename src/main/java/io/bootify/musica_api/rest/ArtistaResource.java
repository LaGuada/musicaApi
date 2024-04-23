package io.bootify.musica_api.rest;

import io.bootify.musica_api.model.AlbumDTO;
import io.bootify.musica_api.model.ArtistaDTO;
import io.bootify.musica_api.service.ArtistaService;
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
@RequestMapping(value = "/api/artistas", produces = MediaType.APPLICATION_JSON_VALUE)
public class ArtistaResource {

    private final ArtistaService artistaService;

    public ArtistaResource(final ArtistaService artistaService) {
        this.artistaService = artistaService;
    }

    @GetMapping
    public ResponseEntity<List<ArtistaDTO>> getAllArtistas() {
        return ResponseEntity.ok(artistaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArtistaDTO> getArtista(@PathVariable(name = "id") final Integer id) {
        return ResponseEntity.ok(artistaService.get(id));
    }

    @PostMapping
    public ResponseEntity<Integer> createArtista(@RequestBody @Valid final ArtistaDTO artistaDTO) {
        final Integer createdId = artistaService.create(artistaDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Integer> updateArtista(@PathVariable(name = "id") final Integer id,
            @RequestBody @Valid final ArtistaDTO artistaDTO) {
        artistaService.update(id, artistaDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArtista(@PathVariable(name = "id") final Integer id) {
        artistaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/albums")
    public ResponseEntity<List<AlbumDTO>> getArtistAlbums(@PathVariable Integer id) {
        List<AlbumDTO> albums = artistaService.findAlbumsByArtistId(id);
        return ResponseEntity.ok(albums);
    }


}
